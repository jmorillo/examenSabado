package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.thread.IThreadWorkerFactory;
import utilities.GetProperties;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class claseInicial {
    private WebDriver driver;

    @Test
    public void inicializador() throws InterruptedException {
        GetProperties properties = new GetProperties();
        String chromeURL = properties.getString("CHROMEDRIVER_PATH");
        System.setProperty("webdriver.chrome.driver", chromeURL);
        driver = new ChromeDriver();
        driver.get("https://www.netflix.com/es/");
        System.out.println("Esperamos");
        Thread.sleep(1000);
        System.out.println("Maximizamos");
        driver.manage().window().setSize(new Dimension(1980, 1080));
        System.out.println("Comprobamos que existen las cookies");
        WebElement cookies = driver.findElement(By.xpath("//div[@id='cookie-disclosure']/div/button"));
        if(cookies.isDisplayed()){
            System.out.println("cerramos las cookies");
            cookies.click();
        }

    }

    @Test
    public void validarTituloTest() throws InterruptedException {
        inicializador();
        String tituloPagina = driver.getTitle();
        System.out.println("El titulo de la pagina es " + tituloPagina);
        assertEquals("Netflix España - Ver series en línea, ver películas en línea", tituloPagina);
    }

    @Test
    public void iniciarSesionPageTest() throws InterruptedException {
        inicializador();
        System.out.println("Hacemos click en inicio sesion");
        driver.findElement(By.xpath("//a[contains(text(),'Iniciar sesión')]")).click();
        String tituloPaginaInicioSesion = driver.getTitle();
        System.out.println("El titulo de la pagina es " + tituloPaginaInicioSesion);
        assertEquals("Netflix", tituloPaginaInicioSesion);
        Thread.sleep(1000);
        boolean existeH1 = driver.findElement(By.xpath("//h1[contains(text(),'Iniciar sesión')]")).isDisplayed();
        System.out.println("Existe el texto h1 Iniciar sesion " + existeH1);
        Thread.sleep(1000);
        System.out.println("validamos que existe iniciar sesion de facebook ");
        boolean existeFB = driver.findElement(By.xpath("//*[contains(text(),'Iniciar sesión con Facebook')]")).isDisplayed();
        assertTrue(existeFB);
    }

    @Test
    public void loginToNetflixErrorTest() throws InterruptedException {
        inicializador();
        System.out.println("Hacemos click en inicio sesion");
        driver.findElement(By.xpath("//a[contains(text(),'Iniciar sesión')]")).click();
        driver.findElement(By.cssSelector(".nfEmailPhoneControls .placeLabel")).click();
        driver.findElement(By.xpath("//input[@id=\'id_userLoginId\']")).sendKeys("xxx");
        {
            WebElement element = driver.findElement(By.cssSelector(".nfPasswordControls .placeLabel"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).clickAndHold().perform();
        }
        {
            WebElement element = driver.findElement(By.xpath("//input[@id=\'id_password\']"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).release().perform();
        }
        driver.findElement(By.cssSelector(".nfPasswordControls > .input_id")).click();
        driver.findElement(By.xpath("//input[@id=\'id_password\']")).sendKeys("holamundo");
        driver.findElement(By.cssSelector(".ui-binary-input > label")).click();
        driver.findElement(By.cssSelector(".login-button")).click();

    }

}

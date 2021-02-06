package tests;


import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.thread.IThreadWorkerFactory;
import utilities.GetProperties;


import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
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
        cierraNavegador();
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
        cierraNavegador();
    }

    @Test
    public void loginToNetflixErrorTest() throws InterruptedException {
        inicializador();
        System.out.println("Hacemos click en inicio sesion");
        driver.findElement(By.xpath("//a[contains(text(),'Iniciar sesión')]")).click();
        System.out.println("Hacemos click en email");
        driver.findElement(By.cssSelector(".nfEmailPhoneControls .placeLabel")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//input[@id=\'id_userLoginId\']")).sendKeys("test@test.com");
        Thread.sleep(500);
        driver.findElement(By.cssSelector(".nfPasswordControls > .input_id")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//input[@id=\'id_password\']")).sendKeys("holamundo");
        Thread.sleep(500);
        driver.findElement(By.cssSelector(".ui-binary-input > label")).click();
        Thread.sleep(500);
        driver.findElement(By.cssSelector(".login-button")).click();
        Thread.sleep(2500);
        boolean botonRecuerda = driver.findElement(By.cssSelector(".ui-binary-input > label")).isDisplayed();
        System.out.println("verificamos la recuerda " + botonRecuerda);
        assertTrue(botonRecuerda);
        System.out.println("verificamos la pass");
        //boolean passIncorrecta = driver.findElement(By.xpath("//*[contains(text(),'Contraseña incorrecta')]")).isDisplayed();
        //A mi no me sale el contraseña incorrecta por lo que he puesto el mensaje de error directamente
        boolean passIncorrecta = driver.findElement(By.xpath("//*[@data-uia='error-message-container']")).isDisplayed();
        assertTrue(passIncorrecta);
        cierraNavegador();
    }

    @Test
    public void fakeEmailTest() throws InterruptedException {
        inicializador();
        driver.findElement(By.xpath("//input[@id=\'id_email_hero_fuji\']")).click();
        driver.findElement(By.xpath("//input[@id=\'id_email_hero_fuji\']")).sendKeys("test@test.com");
        driver.findElement(By.cssSelector(".our-story-card-text:nth-child(2) .cta-btn-txt")).click();
        Thread.sleep(2500);
        String url = driver.getCurrentUrl();
        System.out.println("La url es " + url + " y verificamos si contiene la palabra signup");
        assertThat((url), CoreMatchers.containsString("signup"));
        cierraNavegador();
    }

    @Test
    public void dataProviderEmailTest() throws InterruptedException {

    }



    @After
    public void cierraNavegador() throws InterruptedException {
        driver.close();
    }



    }

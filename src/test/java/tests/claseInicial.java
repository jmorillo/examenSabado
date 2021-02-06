package tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.thread.IThreadWorkerFactory;
import utilities.GetProperties;

public class claseInicial {
    private WebDriver driver;

    @Test
    public void inicializador() throws InterruptedException {
        GetProperties properties = new GetProperties();
        String chromeURL = properties.getString("CHROMEDRIVER_PATH");
        System.setProperty("webdriver.chrome.driver", chromeURL);
        driver = new ChromeDriver();
        driver.get("https://www.netflix.com/");
        driver.manage().window().setSize(new Dimension(1980, 1200));
        //esperamos
        Thread.sleep(2500);
    }

    @Test
    public void validarTituloTest() {
                
    }
    

}

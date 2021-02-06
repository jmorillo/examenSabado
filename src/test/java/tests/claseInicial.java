package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import utilities.GetProperties;

public class claseInicial {
    private WebDriver driver;

    @Test
    public void inicializador(){
        GetProperties properties = new GetProperties();
        String chromeURL = properties.getString("CHROMEDRIVER_PATH");
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\juan.morillo\\Desktop\\programacion\\clase9\\drivers\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chromeURL);
        driver = new ChromeDriver();
        //driver.get("http://www.google.com");
    }

    @Test
    public void ejercicio1(){
        inicializador();
        driver.get("http://www.google.com");
    }

}

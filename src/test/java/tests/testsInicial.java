package tests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.GetProperties;

public class testsInicial {
    @Test
    public void ejercicio1(){
        GetProperties properties = new GetProperties();
        String chromeURL = properties.getString("CHROMEDRIVER_PATH");
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\juan.morillo\\Desktop\\Proyecto\\Selenium\\driver\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chromeURL);

        WebDriver driver = new ChromeDriver();

        driver.get("http://www.google.com");

    }

}
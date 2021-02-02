package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import utilities.GetProperties;

public class claseInicial {
    @Test
    public void ejercicio1(){
        GetProperties properties = new GetProperties();
        String chromeURL = properties.getString("CHROMEDRIVER_PATH");
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\juan.morillo\\Desktop\\programacion\\clase9\\drivers\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chromeURL);

        WebDriver driver = new ChromeDriver();

        driver.get("http://www.google.com");

    }

}

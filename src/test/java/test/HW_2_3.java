package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedCondition.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class HW_2_3 {
    private WebDriver driver;
    private WebDriverWait wait;
    @Before
    public void start(){
        driver=new OperaDriver();
        wait = new WebDriverWait(driver,10);
    }
    @Test
    public void Test_Open_Window(){

        driver.navigate().to(" http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
        System.out.println("HW_2_3");

    }

    @After
    public void stop(){
        driver.quit();
        driver=null;
    }

}

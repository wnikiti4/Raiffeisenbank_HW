package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HW_2_3 {
    private WebDriver driver;
    private WebDriverWait wait;
    private OperaDriver opDriver;

    @Before
    public void start() {
        opDriver = new OperaDriver();

    }

    @Test
    public void Test_ie() {

        opDriver.navigate().to(" http://localhost/litecart/admin/");
        System.out.println("Открытие браузера");
        opDriver.findElement(By.name("username")).sendKeys("admin");
        opDriver.findElement(By.name("password")).sendKeys("admin");
        opDriver.findElement(By.name("login")).click();
        System.out.println("Вошли в админ");

        List<WebElement> listElement = opDriver.findElements(By.cssSelector("li#app- > a"));

        for (int i= 0 ; i<listElement.size();i++){
            List<WebElement> findElements = opDriver.findElements(By.cssSelector("li#app- > a"));
            findElements.get(i).click();
            List<WebElement> subListElemt = opDriver.findElements(By.cssSelector("li#app-  li a"));
            for(int j= 0 ; j<subListElemt.size();j++){
                List<WebElement> newSubListElemt = opDriver.findElements(By.cssSelector("li#app-  li a"));
                newSubListElemt.get(j).click();
                if (areElementsPresent(opDriver,By.cssSelector("h1"))){
                    System.out.println("На страницк есть H1");
                } else {
                    System.out.println("На странице нет H1");
                }
            }
        }

        opDriver.close();
        System.out.println("Закрытие браузера");

    }

    boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    @After
    public void stop() {
        //driver.quit();
        driver = null;
    }

}

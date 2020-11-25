package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;


public class HW_2_8 {
    private OperaDriver opDriver;

    @Before
    public void start() {
        opDriver = new OperaDriver();

    }

    @Test
    public void Test_1() {
        WebDriverWait wait = new WebDriverWait(opDriver, 10);
        loginToAdmin();
        opDriver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        opDriver.findElement(By.cssSelector("td[style=\"text-align: right;\"] a[href*=\"AF\"]")).click();
        for(int i = 0; i < opDriver.findElements(By.cssSelector("form  [target=\"_blank\"]")).size();i++){
            String mainWindow = opDriver.getWindowHandle();
            Set<String> oldWindows = opDriver.getWindowHandles();
            opDriver.findElements(By.cssSelector("form  [target=\"_blank\"]")).get(i).click();
            wait.until(numberOfWindowsToBe(2));
            Set<String> newWindows = opDriver.getWindowHandles();
            // Получили XOR по 2 коллекциям
            newWindows.removeAll(oldWindows);
            // Получили ID новой страници
            String idWindowNew = newWindows.iterator().next();
            opDriver.switchTo().window(idWindowNew);
            opDriver.close();
            opDriver.switchTo().window(mainWindow);
        }
        opDriver.close();
    }

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }
    private void sortCheck(String href, String s, String attribute) {
        opDriver.navigate().to(href);
        List<WebElement> listCountry = opDriver.findElements(By.cssSelector(s));
        List<String> sortCountry = new ArrayList<>();
        List<String> country = new ArrayList<>();
        for (WebElement var : listCountry) {
            country.add(var.getAttribute(attribute));
            sortCountry.add(var.getAttribute(attribute));
        }
        Collections.sort(sortCountry);
        if (sortCountry.equals(country)) {
            System.out.println("Done");
        } else {
            System.out.println("Не совпадают");
        }

    }

    boolean listCheck(List<String> oneList, List<String> twoList) {
        return true;
    }

    static class failTest extends Exception {
        public failTest(String message) {
            super(message);
        }
    }

    void loginToAdmin() {
        opDriver.navigate().to(" http://localhost/litecart/admin/");
        System.out.println("Открытие браузера");
        opDriver.findElement(By.name("username")).sendKeys("admin");
        opDriver.findElement(By.name("password")).sendKeys("admin");
        opDriver.findElement(By.name("login")).click();
    }

    boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    @After
    public void stop() {
        opDriver.quit();
        opDriver = null;
    }

}

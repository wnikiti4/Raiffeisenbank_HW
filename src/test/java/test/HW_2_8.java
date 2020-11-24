package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HW_2_8 {
    private OperaDriver opDriver;

    @Before
    public void start() {
        opDriver = new OperaDriver();

    }

    @Test
    public void Test_1() {
        String maskName = "b";
        String maskCode = "00002";
        String phone = '+'+ maskCode + maskCode;
        String email = maskName+'@'+maskName+'.'+maskName;

 //       loginToAdmin();
        String href = "http://localhost/litecart/en/create_account";
        opDriver.navigate().to(href);
       opDriver.findElement(By.cssSelector("input[name=\"firstname\"]")).sendKeys(maskName);
        opDriver.findElement(By.cssSelector("input[name=\"lastname\"]")).sendKeys(maskName);
        opDriver.findElement(By.cssSelector("input[name=\"address1\"]")).sendKeys(maskName);
        opDriver.findElement(By.cssSelector("input[name=\"postcode\"]")).sendKeys(maskCode);
        opDriver.findElement(By.cssSelector("input[name=\"city\"]")).sendKeys(maskCode);
        opDriver.findElement(By.cssSelector(".select2-selection__arrow")).click();
        opDriver.findElement(By.cssSelector("li[id*=\"US\"]")).click();
        opDriver.findElement(By.cssSelector("input[name=\"email\"]")).sendKeys(email);
        opDriver.findElement(By.cssSelector("input[name=\"phone\"]")).sendKeys(phone);
        opDriver.findElement(By.cssSelector("input[name=\"password\"]")).sendKeys(phone);
        opDriver.findElement(By.cssSelector("input[name=\"confirmed_password\"]")).sendKeys(phone);
        opDriver.findElement(By.cssSelector("[name=\"create_account\"]")).click();
        System.out.println("Пользователь зарегестрирован");
        opDriver.findElement(By.cssSelector("a[href=\"http://localhost/litecart/en/logout\"]")).click();
        System.out.println("Вышли");
        opDriver.findElement(By.cssSelector("[name=\"email\"]")).sendKeys(email);
        opDriver.findElement(By.cssSelector("[name=\"password\"]")).sendKeys(phone);
        opDriver.findElement(By.cssSelector("[name=\"login\"]")).click();
        System.out.println("Зашили");
        opDriver.findElement(By.cssSelector("a[href=\"http://localhost/litecart/en/logout\"]")).click();
        System.out.println("Вышли");
        opDriver.close();
        System.out.println("Закрытие браузера");
    }

    @Test
    public void Test_2(){
        loginToAdmin();

    }


    private void sortCheck(String href, String s, String attribute) {
        opDriver.navigate().to(href);
        List<WebElement> listCountry = opDriver.findElements(By.cssSelector(s));
        List<String> sortCountry = new ArrayList<String>();
        List<String> country = new ArrayList<String>();
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

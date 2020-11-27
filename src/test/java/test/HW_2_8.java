package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class HW_2_8 {
    private OperaDriver opDriver;

    @Before
    public void start() {
        opDriver = new OperaDriver();

    }

    @Test
    public void Test_1() {
        WebDriverWait wait = new WebDriverWait(opDriver, 10);
        for (int i = 1; i <= 2; i++) {
            opDriver.navigate().to("http://Localhost/litecart");
            opDriver.findElements(By.cssSelector(".product")).get(0).click();
            if (areElementsPresent(opDriver, By.cssSelector("[name=\"options[Size]\"]"))) {
                Select select = new Select(opDriver.findElements(By.cssSelector("[name=\"options[Size]\"]")).get(0));
                select.selectByIndex(1);
            }
            opDriver.findElement(By.cssSelector("[name=\"add_cart_product\"]")).click();
            if (wait.until(attributeToBe(opDriver.findElement(By.cssSelector("span.quantity")),
                    "textContent",
                    Integer.toString(i)))) {
                System.out.println("Товар добавлен");
            } else {
                System.out.println("Чет не добавилось");
                opDriver.close();
            }
        }
        opDriver.findElement(By.cssSelector("a.link[href=\"http://localhost/litecart/en/checkout\"]")).click();

        int size = opDriver.findElements(By.cssSelector("td.item")).size();
        for (int j = size; j >= 1; j--) {
            if(areElementsPresent(opDriver,By.cssSelector("a.inact"))){
                opDriver.findElement(By.cssSelector("a.inact")).click();
            }
            opDriver.findElement(By.cssSelector("[name=\"remove_cart_item\"]")).click();
            // мне не нравиться как это выглядит, есть ли более элегнтный способ ?
            if (wait.until(numberOfElementsToBeLessThan(By.cssSelector("td.item"), j)).size() != -1) {
                System.out.println("Товар удален");
            } else {
                System.out.println("Все сломалось");
                opDriver.close();
            }
        }
        opDriver.close();
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
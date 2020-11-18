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
        loginToAdmin();
        String href = "http://localhost/litecart/admin/?app=countries&doc=countries";
        String selector = "tr.row td:nth-of-type(5)";
        String attrebut = "innerText";
        sortCheck(href, selector, attrebut);
        System.out.println("Первая проверка завершена");
        opDriver.navigate().to(href);
        List<WebElement> listZonesOtherThatZero = opDriver.findElements(By.cssSelector("tr.row td:nth-of-type(6)"));
        for (int i = 0; i < listZonesOtherThatZero.size(); i++) {
            if (!listZonesOtherThatZero.get(i).getAttribute("innerText").equals("0")) {
                int i2 = i + 2;
                String hrefB = opDriver.findElement(By.cssSelector("tr.row:nth-of-type(" + i2 + ") td:nth-of-type(5) a")).getAttribute("href");
                System.out.println("Упал в страну под номером:" + i2 + " с именем " + opDriver.findElement(By.cssSelector("tr.row:nth-of-type(" + i2 + ") td:nth-of-type(5)")).getAttribute("innerText"));
                sortCheck(hrefB, "table.dataTable td:nth-of-type(3) input[type=\"hidden\"]", "value");
                opDriver.navigate().to(href);
                listZonesOtherThatZero = opDriver.findElements(By.cssSelector("tr.row td:nth-of-type(6)"));
            }
        }
        System.out.println();
        opDriver.close();
        System.out.println("Закрытие браузера");
    }

    @Test
    void Test_2(){
        loginToAdmin();
        opDriver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement> listElement = opDriver.findElements(By.cssSelector("tr.row td:nth-of-type(3) a"));
        listElement.get(1).click();
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

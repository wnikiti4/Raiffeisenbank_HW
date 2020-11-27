package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class HW_2_8 {
    private OperaDriver opDriver;
    private List<WebElement> listEl;
    private List<WebElement> listProd;
    private boolean flag =false;

    @Before
    public void start() {
        opDriver = new OperaDriver();

    }
    @Test
    public void Test_1() {
        loginToAdmin();
        WebDriverWait wait = new WebDriverWait(opDriver, 10);
        opDriver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=0");
        wait.until(presenceOfElementLocated(By.cssSelector(".fa-folder-open")));
        int j = 0;
        int  passedElements = 0;
        while (areElementsPresent(opDriver, By.cssSelector(".fa.fa-folder"))) {
            listEl = opDriver.findElements(By.cssSelector(" i.fa.fa-folder + a"));
            String currentHref = listEl.get(0).getAttribute("href");
            listEl.get(0).click();
            int size = opDriver.findElements(By.cssSelector("tr.row td:nth-of-type(3) [href *= \"product\"]")).size();
            while ( j  != (size-passedElements)){
                listProd = opDriver.findElements(By.cssSelector("tr.row td:nth-of-type(3) [href *= \"product\"]"));
                listProd.get(j).click();
                j++;
                CheckLogs(opDriver);
                wait.until(presenceOfElementLocated(By.cssSelector("td#content")));
                opDriver.navigate().to(currentHref);
            }
            passedElements = j;
            j = 0;
        }
        if (flag) {
            System.out.println("Логи были");
        } else {
            System.out.println("Логов нет");
        }
        opDriver.close();
    }

    private void CheckLogs(WebDriver driver) {
        for (LogEntry l : driver.manage().logs().get("browser").getAll()) {
            System.out.println(l);
            flag = true;
        }
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

    static class failTest extends Exception {
        public failTest(String message) {
            super(message);
        }
    }

    void loginToAdmin() {
        opDriver.navigate().to(" http://localhost/litecart/admin/");
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
/*1) зайти в админку
2) открыть каталог, категорию, которая содержит товары (страница http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1)
3) последовательно открывать страницы товаров и проверять, не появляются ли в логе браузера сообщения (любого уровня)*/
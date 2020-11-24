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

public class HW_2_8 {
    private OperaDriver opDriver;

    @Before
    public void start() {
        opDriver = new OperaDriver();

    }

    @Test
    public void Test_1() {
        String maskText = "aaaaa";
        String maskCode = "0001";
        WebDriverWait wait= new WebDriverWait(opDriver,10);
 //       loginToAdmin();
        String href = "http://localhost/litecart/admin/?app=catalog&doc=catalog";
        loginToAdmin();
        opDriver.navigate().to(href);
        int Amount =opDriver.findElements(By.cssSelector("tr.row")).size();
        WebElement button = wait.until((WebDriver d) -> d.findElement(By.cssSelector(".button:nth-of-type(2)")));
        button.click();
        opDriver.findElement(By.cssSelector("[value=\"1\"]")).click();
        opDriver.findElement(By.cssSelector("[name = \"name[en]\"]")).sendKeys(maskText);
        opDriver.findElement(By.cssSelector("[name = \"code\"]")).sendKeys(maskCode);
        opDriver.findElement(By.cssSelector("[value = \"1-3\"]")).click();
        opDriver.findElement(By.cssSelector("[name = \"quantity\"]")).sendKeys("1");
        String path = System.getProperty("user.dir");
        opDriver.findElement(By.cssSelector("[name = \"new_images[]\"]")).sendKeys(path+"\\src\\test\\java\\test\\Resources\\Im.jpg");
        opDriver.findElement(By.cssSelector("[name = \"date_valid_from\"]")).sendKeys("20111990");
        opDriver.findElement(By.cssSelector("[name = \"date_valid_to\"]")).sendKeys("20112022");
        System.out.println("General заполнена");
        opDriver.findElement(By.cssSelector("[href = \"#tab-information\"]")).click();
        Select select = new Select(opDriver.findElement(By.cssSelector("[name = \"manufacturer_id\"]")));
        select.selectByIndex(1);
        opDriver.findElement(By.cssSelector("[name = \"keywords\"]")).sendKeys(maskText);
        opDriver.findElement(By.cssSelector("[name = \"short_description[en]\"]")).sendKeys(maskText);
        opDriver.findElement(By.cssSelector("[name = \"description[en]\"]")).sendKeys(maskText+maskText);
        opDriver.findElement(By.cssSelector("[name = \"head_title[en]\"]")).sendKeys(maskText);
        opDriver.findElement(By.cssSelector("[name = \"meta_description[en]\"]")).sendKeys(maskText);
        opDriver.findElement(By.cssSelector("[href=\"#tab-prices\"]")).click();
        opDriver.findElement(By.cssSelector("[name=\"purchase_price\"]")).sendKeys("1");
        Select select1 = new Select(opDriver.findElement(By.cssSelector("[name = \"purchase_price_currency_code\"]")));
        select1.selectByValue("USD");
        opDriver.findElement(By.cssSelector("[name = \"gross_prices[USD]\"")).sendKeys("1");
        opDriver.findElement(By.cssSelector("[name = \"gross_prices[EUR]\"")).sendKeys("1");
        opDriver.findElement(By.cssSelector("[name=\"save\"]")).click();
        // Не очень понял какую именно проверку вы хотите, так что сделал 2
        if(areElementsPresent(opDriver,By.cssSelector("[href*=\"id=7\"]"))){
            if(Amount < opDriver.findElements(By.cssSelector("tr.row")).size()){
                System.out.println("Test done");
            }
        }else {
            System.out.println("Чет пошло не так");
            new failTest("Товар не смог сохраниться");
        }
        opDriver.close();
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

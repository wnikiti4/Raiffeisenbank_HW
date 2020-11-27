package test;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBeLessThan;

@RunWith(DataProviderRunner.class)
public class DataHrefTests {
    //11:00
    @Test
    @UseDataProvider(value = "dataProvider", location = DP.class)
    public void canRegisterCustomer(DataHref href) {
        for (int i = 0; i < 3; i++) {
            chooseItems();
            addItem();
        }
        loginInShopingCart();
        removingItems();
    }

    WebDriver opDriver;
    WebDriverWait wait;

    @After
    public void ini(){
        opDriver = new OperaDriver();
        wait = new WebDriverWait(opDriver, 10);
    }
    private void addItem() {
        opDriver.navigate().to("http://Localhost/litecart");
        opDriver.findElements(By.cssSelector(".product")).get(0).click();
    }

    private void loginInShopingCart() {
        opDriver.findElement(By.cssSelector("a.link[href=\"http://localhost/litecart/en/checkout\"]")).click();
    }

    private void chooseItems() {
            if (areElementsPresent(opDriver, By.cssSelector("[name=\"options[Size]\"]"))) {
                Select select = new Select(opDriver.findElements(By.cssSelector("[name=\"options[Size]\"]")).get(0));
                select.selectByIndex(1);
            }

            int oldNumberItem = Integer.parseInt(opDriver.findElement(By.cssSelector("span.quantity")).getAttribute("textContent"));
            opDriver.findElement(By.cssSelector("[name=\"add_cart_product\"]")).click();
            if (wait.until(attributeToBe(opDriver.findElement(By.cssSelector("span.quantity")),
                    "textContent",
                    Integer.toString(oldNumberItem+1)))) {
                System.out.println("Товар добавлен");
            } else {
                System.out.println("Чет не добавилось");
                opDriver.close();
            }
    }

    private void removingItems() {
        int size = opDriver.findElements(By.cssSelector("td.item")).size();
        for (int j = size; j >= 1; j--) {
            ClickingOnTheImage();
            opDriver.findElement(By.cssSelector("[name=\"remove_cart_item\"]")).click();
            // мне не нравиться как это выглядит, есть ли более элегнтный способ ?
            if (wait.until(numberOfElementsToBeLessThan(By.cssSelector("td.item"), j)).size() != -1) {
                System.out.println("Товар удален");
            } else {
                System.out.println("Все сломалось");
                new failTest("Не нашелся элемент по селектору td.item");
            }
        }
    }

    private void ClickingOnTheImage() {
        if(areElementsPresent(opDriver,By.cssSelector("a.inact"))){
            opDriver.findElement(By.cssSelector("a.inact")).click();
        }
    }

    static class failTest extends Throwable {
        public failTest(String message) {
            super(message);
        }
    }

    boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    @After
    public void end(){
        opDriver.quit();
        opDriver=null;
    }
}
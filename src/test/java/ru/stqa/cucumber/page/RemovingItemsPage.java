package ru.stqa.cucumber.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBeLessThan;

public class RemovingItemsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public RemovingItemsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public WebElement product() {
        return driver.findElement(By.cssSelector("a.link[href=\"http://localhost/litecart/en/checkout\"]"));
    }



    public WebElement buttonRemove() {
        return driver.findElement(By.cssSelector("[name=\"remove_cart_item\"]"));
    }

    public List<WebElement> items() {
        return driver.findElements(By.cssSelector("td.item"));
    }

    public By byItems() {
        return By.cssSelector("td.item");
    }

    public List<WebElement> listImageItems() {
        return driver.findElements(By.cssSelector("a.inact"));
    }
    public void ClickingOnTheImage(RemovingItemsPage removingItemsPage) {
        if (areElementsPresent(removingItemsPage.listImageItems())) {
            removingItemsPage.listImageItems().get(0).click();
        }
    }

    private boolean areElementsPresent(List<WebElement> elements) {
        return elements.size() > 0;
    }

    public void loginInShopingCart() {
        RemovingItemsPage removingItemsPage = new RemovingItemsPage(driver, wait);
        removingItemsPage.product().click();
    }

    public boolean checkDelate(RemovingItemsPage removingItemsPage, int j,boolean deleteCheck) {
        if (wait.until(numberOfElementsToBeLessThan(removingItemsPage.byItems(), j)).size() != -1) {
            deleteCheck = deleteCheck&true;
        } else {
            new failTest("Не нашелся элемент по селектору td.item");
            deleteCheck = deleteCheck&false;
        }
        return deleteCheck;
    }


    private static class failTest extends Throwable {
        public failTest(String message) {
            super(message);
        }
    }
}

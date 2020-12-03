package ru.stqa.cucumber.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeToBe;

public class ChooseItemPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ChooseItemPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public WebElement select() {
        return driver.findElements(By.cssSelector("[name=\"options[Size]\"]")).get(0);
    }

    public WebElement buttonAddItem() {
        return driver.findElement(By.cssSelector("[name=\"add_cart_product\"]"));
    }

    public WebElement counter() {
        return driver.findElement(By.cssSelector("span.quantity"));
    }

    public String attributeItemСounter() {
        return driver.findElement(By.cssSelector("span.quantity")).getAttribute("textContent");
    }

    public List<WebElement> selects() {
        return driver.findElements(By.cssSelector("[name=\"options[Size]\"]"));
    }

    public void selectSizeByIndex(int index) {
        if (areElementsPresent(selects())) {
            Select select = new Select(select());
            select.selectByIndex(index);
        }

    }

    public boolean addCheck(ChooseItemPage chooseItemPage, int oldNumberItem) {
        if (wait.until(attributeToBe(chooseItemPage.counter(),
                "textContent",
                Integer.toString(oldNumberItem + 1)))) {
            return true;
        } else {
            return false;
        }
    }

    private boolean areElementsPresent(List<WebElement> elements) {
        return elements.size() > 0;
    }
}

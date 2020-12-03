package ru.stqa.cucumber.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage {
    WebDriver driver;
    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public List<WebElement> listProduct() {return driver.findElements(By.cssSelector(".product"));}

    public void navigateMainPage() {
        driver.navigate().to("http://Localhost/litecart");
    }

    public void clickOnProduct() {
        listProduct().get(0).click();
    }
}

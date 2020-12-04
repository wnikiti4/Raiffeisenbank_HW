package ru.stqa.cucumber.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.cucumber.page.ChooseItemPage;
import ru.stqa.cucumber.page.MainPage;
import ru.stqa.cucumber.page.RemovingItemsPage;

public class App {

    private WebDriver opDriver;
    private final WebDriverWait wait;
    private boolean chooseItemChek;
    private boolean deleteItemChek = true;
    public App() {
        opDriver = new OperaDriver();
        wait = new WebDriverWait(opDriver, 10);
    }

    public WebDriver getDriver() {
        return opDriver;
    }

    public void setDriver(WebDriver driver) {
        opDriver = driver;
    }

    public void addItem() {
        MainPage mainPage = new MainPage(opDriver);
        mainPage.navigateMainPage();
        mainPage.clickOnProduct();
    }


    public void chooseItem() {
        ChooseItemPage chooseItemPage = new ChooseItemPage(opDriver, wait);
        chooseItemPage.selectSizeByIndex(1);
        int oldNumberItem = Integer.parseInt(chooseItemPage.attributeItemСounter());
        chooseItemPage.buttonAddItem().click();
        chooseItemChek =chooseItemPage.addCheck(chooseItemPage, oldNumberItem);

    }
    public boolean ChekItem(){
        return chooseItemChek;
    }
    public boolean ChekItemDelete(){
        return deleteItemChek;
    }

    public void removingItems() {
        RemovingItemsPage removingItemsPage = new RemovingItemsPage(opDriver, wait);
        removingItemsPage.loginInShopingCart();
        int size = removingItemsPage.items().size();
        for (int j = size; j >= 1; j--) {
            removingItemsPage.ClickingOnTheImage(removingItemsPage);
            removingItemsPage.buttonRemove().click();
            deleteItemChek = removingItemsPage.checkDelate(removingItemsPage, j, deleteItemChek);
        }
    }
    public void driverQuit(){
        opDriver.quit();
        opDriver = null;
    }

}

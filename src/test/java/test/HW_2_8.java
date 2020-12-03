package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.StringTokenizer;

public class HW_2_8 {
    private OperaDriver opDriver;
    private ChromeDriver goDriver;
    private FirefoxDriver fiDriver;
    private InternetExplorerDriver ieDriver;

    @Before
    public void start() {
        //opDriver = new OperaDriver();
        //fiDriver = new FirefoxDriver();
        //ieDriver = new InternetExplorerDriver();
    }

    @Test
    public void Test_1() {
        System.out.println("Проверка для InternetExplorer");
        ieDriver = new InternetExplorerDriver();
        TestDriver(ieDriver);
        ieDriver.quit();
        ieDriver = null;
        System.out.println("Проверка для GoogleChrome");
        goDriver = new ChromeDriver();
        TestDriver(goDriver);
        goDriver.quit();
        goDriver = null;
        System.out.println("Проверка для Firefox");
        fiDriver = new FirefoxDriver();
        TestDriver(fiDriver);
        fiDriver.quit();
        fiDriver = null;
    }

    private void TestDriver(WebDriver goDriver) {
        goDriver.navigate().to("http://localhost/litecart/en/");
        WebElement campaignPriceMainPage = goDriver.findElement(By.cssSelector("#box-campaigns .campaign-price"));
        String nameMainPage = goDriver.findElement(By.cssSelector("#box-campaigns .name")).getAttribute("textContent");
        String textCampaignPriceMainPage = campaignPriceMainPage.getAttribute("textContent");
        String colorCampaignPriceMainPage = campaignPriceMainPage.getCssValue("color");
        String fontWeightCampaignPriceMainPage = campaignPriceMainPage.getCssValue("font-weight");
        String fontSizeCampaignPriceMainPage = campaignPriceMainPage.getCssValue("font-size");
        WebElement regularPriceMainPage = goDriver.findElement(By.cssSelector("#box-campaigns .regular-price"));
        String textRegularPriceMainPage = regularPriceMainPage.getAttribute("textContent");
        String colorRegularPriceMainPage = regularPriceMainPage.getCssValue("color");
        String textDecorationRegularPriceMainPage = regularPriceMainPage.getCssValue("text-decoration-line");
        if(goDriver.getClass().toGenericString().indexOf("Explorer") >0) {textDecorationRegularPriceMainPage = regularPriceMainPage.getCssValue("text-decoration");};
        String fontSizeRegularPriceMainPage = regularPriceMainPage.getCssValue("font-size");
        goDriver.findElement(By.cssSelector("#box-campaigns a.link")).click();
        WebElement campaignPriceItemPage = goDriver.findElement(By.cssSelector(".campaign-price"));
        String nameItemPage = goDriver.findElement(By.cssSelector("#box-product .title")).getAttribute("textContent");
        String textCampaignPriceItemPage = campaignPriceItemPage.getAttribute("textContent");
        String colorCampaignPriceItemPage = campaignPriceItemPage.getCssValue("color");
        String fontWeightCampaignPriceItemPage = campaignPriceItemPage.getCssValue("font-weight");
        String fontSizeCampaignPriceItemPage = campaignPriceItemPage.getCssValue("font-size");
        WebElement regularPriceItemPage = goDriver.findElement(By.cssSelector(".regular-price"));
        String textRegularPriceItemPage = regularPriceItemPage.getAttribute("textContent");
        String colorRegularPriceItemPage = regularPriceItemPage.getCssValue("color");
        String textDecorationPriceItemPage = regularPriceItemPage.getCssValue("text-decoration-line");
        if(goDriver.getClass().toGenericString().indexOf("Explorer") >0) {textDecorationPriceItemPage = regularPriceItemPage.getCssValue("text-decoration");};
        String fontSizeRegularPriceItemPage = regularPriceItemPage.getCssValue("font-size");
        int[] RGBcolorRegularMainPage = parseRGBa(colorRegularPriceMainPage,goDriver);
        int[] RGBcolorRegularPriceItemPage = parseRGBa(colorRegularPriceItemPage,goDriver);
        int[] RGBcolorCampaignPriceMainPage = parseRGBa(colorCampaignPriceMainPage,goDriver);
        int[] RGBcolorCampaignPriceItemPage = parseRGBa(colorCampaignPriceItemPage,goDriver);
        if (nameItemPage.equals(nameMainPage)){
            System.out.println("1) Имена совпадают");
        } else {
            System.out.println("Имена не совпадают");
        }
        if (textCampaignPriceItemPage.equals(textCampaignPriceMainPage)) {
            System.out.println("2) Цена акции совпадает");
        } else {
            System.out.println("Цена акции не совпадает");
        }
        if (textRegularPriceItemPage.equals(textRegularPriceMainPage)){
            System.out.println("3) Обычная цена совпадает");
        } else {
            System.out.println("Обычая цена не совпадает");
        }

        if((textDecorationRegularPriceMainPage.equals("line-through"))&&(RGBcolorRegularMainPage[0]== RGBcolorRegularMainPage[1])&&(RGBcolorRegularMainPage[1]== RGBcolorRegularMainPage[2])){
            System.out.println("4) На главной странице цена зачеркнута и сера");
        }
        if((textDecorationPriceItemPage.equals("line-through"))
                &&(RGBcolorRegularPriceItemPage[0]== RGBcolorRegularPriceItemPage[1])
                &&(RGBcolorRegularPriceItemPage[1]== RGBcolorRegularPriceItemPage[2])){
            System.out.println("5) На страницу товара цена зачеркнута и сера");
        }
        if((fontWeightCampaignPriceMainPage.equals("700")
                ||(fontWeightCampaignPriceMainPage.equals("900")))
                &&(RGBcolorCampaignPriceMainPage[0] > 1)
                &&(RGBcolorCampaignPriceMainPage[1] == 0)&&(RGBcolorCampaignPriceMainPage[2] == 0)){
            System.out.println("6) На главной странице цена красная и жирная");
        }
        if((fontWeightCampaignPriceItemPage.equals("700"))
                ||(fontWeightCampaignPriceMainPage.equals("900"))
                &&(RGBcolorCampaignPriceItemPage[0] > 1)
                &&(RGBcolorCampaignPriceItemPage[1] == 0)&&(RGBcolorCampaignPriceItemPage[2] == 0)){
            System.out.println("7) На странице товара красная и жирная");
        }
        if(parsefontSize(fontSizeCampaignPriceMainPage) > parsefontSize(fontSizeRegularPriceMainPage)){
            System.out.println("8) Размер цена со скидкой больше на галвной странице");
        }
        if(parsefontSize(fontSizeCampaignPriceItemPage) > parsefontSize(fontSizeRegularPriceItemPage)){
            System.out.println("9) Размер цена со скидкой больше на странице товара");
        }
    }

    public float parsefontSize(String fontSize){
        fontSize=fontSize.replace("px","");
        return Float.parseFloat(fontSize);
    }
    public int[] parseRGBa(String color, WebDriver webDriver){
        String s1 = "";
        String nameWebDriver=webDriver.getClass().toGenericString();
        if(webDriver.getClass().toGenericString().indexOf("Firefox") >0 ){s1 = color.substring(4);}else{
            s1 =color.substring(5);
        }

        color = s1.replace(')', ' ');
        StringTokenizer st = new StringTokenizer(color);
        int r = Integer.parseInt(st.nextToken(",").trim());
        int g = Integer.parseInt(st.nextToken(",").trim());
        int b = Integer.parseInt(st.nextToken(",").trim());
        int RGB[]={r,g,b};
        return RGB;
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
    }
}

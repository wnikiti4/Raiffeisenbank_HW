package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;

import java.util.List;

public class HW_2_8 {
    private OperaDriver opDriver;

    @Before
    public void start() {
        opDriver = new OperaDriver();

    }


    @Test
    public void Test_ie() {

        opDriver.navigate().to("http://localhost/litecart/en/");
        System.out.println("Открытие браузера");
        // Я не смог сделать нормально, и пришось делать отдельно для каждого div
        checkStickersBySelector("box-most-popular");
        checkStickersBySelector("box-campaigns");
        checkStickersBySelector("box-latest-products");
        opDriver.close();
        System.out.println("Закрытие браузера");

    }

    static class failTest extends Exception {
        public failTest(String message) {
            super(message);
        }
    }

    boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    //box-latest-products
    void checkStickersBySelector(String idDiv) {
        System.out.println("Для " + idDiv);
        for (int i = 0; i < opDriver.findElements(By.cssSelector("div#" + idDiv + " li.product.column")).size(); i++) {
            int a = i+1; // ТК list начианается с 0 а для селектора это 1 ииии я не очень понимаю почему
            List<WebElement> stickers = opDriver.findElements(By.cssSelector("div#" + idDiv + " li.product.column:nth-of-type(" + a + ") div.sticker"));
            if (stickers.size() != 1) {
                try {
                    throw new failTest("Product has more than 2 stickers");
                } catch (HW_2_8.failTest failTest) {
                    failTest.printStackTrace();
                }
            }
            System.out.println("У " + a + " элемента только " + stickers.size() + " стикер");
        }

    }

    @After
    public void stop() {
        opDriver.quit();
        opDriver = null;
    }

}
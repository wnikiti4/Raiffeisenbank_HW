package test.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class TestCloud {
    public static final String USERNAME = "bsuser750215555631";
    public static final String AUTOMATE_KEY = "8HsbVZyPi7U3aoz3RvDp";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    public static void main(String[] args) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "7");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "79");

        caps.setCapability("name", "bsuser750215555631's First Test");

        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
        driver.get("https://www.w3schools.com");
        driver.findElements(By.cssSelector(".w3-button.w3-dark-grey:nth-child(1)")).get(0).click();

        System.out.println(driver.getTitle());
        driver.quit();
    }
}
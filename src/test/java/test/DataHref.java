package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DataHref {

    private String startHref;
    WebDriver opDriver;
    WebDriverWait wait;

    public DataHref(String startHref){
        this.startHref = startHref;
    }
    public String getStartHref() {
        return startHref;
    }
}

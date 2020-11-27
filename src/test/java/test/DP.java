package test;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class DP {
    @DataProvider
    public static Object[][] dataProvider() {
        return new Object[][]{{new DataHref("https://www.google.com")}};
    }
}
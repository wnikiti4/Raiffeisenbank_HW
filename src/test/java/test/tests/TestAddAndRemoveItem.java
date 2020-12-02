package test.tests;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import test.model.BaseTest;


@RunWith(DataProviderRunner.class)
public class TestAddAndRemoveItem extends BaseTest {
    @Test
    @UseDataProvider(value = "dataProvider", location = DP.class)
    public void canRegisterCustomer(DataHref href) {
        for (int i = 0; i < 3; i++) {
            app.addItem();
            app.chooseItem();
        }
        app.removingItems();
    }

}
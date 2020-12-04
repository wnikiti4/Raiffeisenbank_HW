package ru.stqa.cucumber;


import io.cucumber.java8.En;
import org.junit.Assert;
import ru.stqa.cucumber.app.*;

public class StringStepdefs  implements En {
    App app;
    public StringStepdefs() {
        Given("Open site",()->{
            app = new App();
        });
        When("adding {string} item to cart",(String step)->{
            for (int i = 0; i<Integer.parseInt(step); i++ ) {
                app.addItem();
                app.chooseItem();
            }
        });

        Then("item counter has changed",() ->{
           Assert.assertEquals(app.ChekItem(),true);
        });
        Then("removing items to cart",()->{
            app.removingItems();
        });
        Then("items removed from the cart",()->{
            Assert.assertEquals(app.ChekItemDelete(),true);
        });
    }
}

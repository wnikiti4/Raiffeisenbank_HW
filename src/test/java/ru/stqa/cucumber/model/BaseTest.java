package ru.stqa.cucumber.model;

import org.junit.After;
import org.junit.Before;
import ru.stqa.cucumber.app.App;

public class BaseTest {
    public App app;

    @Before
    public void start() {
        app = new App();
    }
    @After
    public void end(){
        app.getDriver().quit();
        app.setDriver(null);
    }
}

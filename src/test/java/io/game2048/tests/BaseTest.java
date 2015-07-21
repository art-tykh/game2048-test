package io.game2048.tests;

import framework.CustomWebDriver;
import framework.DataProvider;
import org.testng.annotations.AfterClass;

import java.util.Properties;

/**
 * Created by mbp on 7/21/15.
 */
public class BaseTest {

    private Properties properties;

    @AfterClass
    public void tearDownTest() {
        CustomWebDriver.getInstance().terminate();
    }

    public void setBrowser() {
        properties = DataProvider.getInstance().getProperties();
        System.setProperty("test.browser", properties.getProperty("default_browser"));
    }
}

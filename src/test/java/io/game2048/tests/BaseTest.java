package io.game2048.tests;

import framework.CustomWebDriver;
import framework.DataProvider;
import org.testng.annotations.AfterClass;

import java.util.Properties;

/**
 * This class contains general methods for all future tests
 */
public class BaseTest {

    private Properties properties;

    @AfterClass
    public void tearDownTest() {
        CustomWebDriver.getInstance().terminate(); // close browser window after test run
    }

    /**
     * Set target browser via project.properties file
     */
    public void setBrowser() {
        properties = DataProvider.getInstance().getProperties();
        System.setProperty("test.browser", properties.getProperty("default_browser"));
    }
}

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
        // close browser window after test is finished
        CustomWebDriver.getInstance().terminate();
    }

    /**
     * Set target browser via project.properties file
     */
    public void setBrowser() {
        properties = DataProvider.getInstance().getProperties();
        if (System.getProperty("test.browser") == null) {
            System.setProperty("test.browser", properties.getProperty("default_browser"));
        }
    }
}

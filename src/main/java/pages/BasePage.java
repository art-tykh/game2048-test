package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Level;

/**
 * This class represents base page for all future pages
 *
 * @author Artem Tykhonov
 */
public class BasePage {

    public WebDriver driver;

    public BasePage() {
        driver = new FirefoxDriver();
        PageFactory.initElements(driver, this);
    }

}

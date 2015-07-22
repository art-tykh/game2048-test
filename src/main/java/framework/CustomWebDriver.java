package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by mbp on 7/21/15.
 */
public class CustomWebDriver {

    private static final String FIREFOX = "firefox";
    private static final String CHROME = "chrome";
    private static final String SAFARI = "safari";

    private static final String WINDOWS = "windows";
    private static final String MAC = "mac";

    private WebDriver driver;
    private static String browser;
    private static volatile CustomWebDriver instance;

    private CustomWebDriver() {
        initProperties();
        driver = initDriver();
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public static CustomWebDriver getInstance() {
        Lock lock;
        if (instance == null) {
            lock = new ReentrantLock();
            lock.lock();

            if (instance == null) {
                instance = new CustomWebDriver();
            }

            lock.unlock();
        }
        return instance;
    }

    public String getBrowser() {
        return browser;
    }

    public WebDriver initDriver() {
        driver = null;
        if (browser.equals(FIREFOX)) {
            driver = new FirefoxDriver();
        } else if (browser.equals(SAFARI)) {
            DesiredCapabilities capabilities = DesiredCapabilities.safari();
            capabilities.setJavascriptEnabled(true);
            driver = new SafariDriver(capabilities);
        } else if (browser.equals(CHROME)) {
            initChrome();
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        return driver;
    }

    public void terminate() {
        driver.quit();
        driver = null;
    }

    private void initChrome() {
        String os = System.getProperty("os.name").toLowerCase();
        String userDir = System.getProperty("user.dir");
        String path = "";

        if (os.contains(MAC)) {
            path = userDir + "/drivers/chromedriver_mac32";
        } else if (os.contains(WINDOWS)) {
            path = userDir + "\\drivers\\chromedriver_win32";
        }
        System.out.println(path);
        System.setProperty("webdriver.chrome.driver", path);
    }

    private void initProperties() {
        if (System.getProperty("test.browser") == null) {
            System.setProperty("test.browser", FIREFOX);
        }
        browser = System.getProperty("test.browser");
    }
}

package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The {@code WaitUtils} class provides additional functionality to
 * standard WebDriver
 *
 * @author Artem Tykhonov
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

    /**
     *
     * @return
     *      the WebDriver instance
     */
    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    /**
     *
     * @return
     *      the instance of CustomWebDriver
     */
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

    /**
     * Factory that creates different browser instance, depends on the
     * type of the browser
     *
     * @return
     *      the WebDriver instance
     */
    public WebDriver initDriver() {
        driver = null;
        if (browser.equals(FIREFOX)) {
            driver = new FirefoxDriver();
        } else if (browser.equals(SAFARI)) {
            driver = new SafariDriver();
        } else if (browser.equals(CHROME)) {
            initChrome();
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * Quites the driver
     */
    public void terminate() {
        driver.quit();
        driver = null;
    }

    /**
     * Provides correct path for Chrome driver binaries depends on
     * the OS
     */
    private void initChrome() {
        String os = System.getProperty("os.name").toLowerCase();
        String userDir = System.getProperty("user.dir");
        String path = "";

        if (os.contains(MAC)) {
            path = userDir + "/drivers/chromedriver_mac32";
        } else if (os.contains(WINDOWS)) {
            path = userDir + "\\drivers\\chromedriver_win32.exe";
        }
        System.out.println(path);
        System.setProperty("webdriver.chrome.driver", path);
    }

    /**
     * Initializes default properties
     * Default browser will be FIREFOX, unless other specified
     */
    private void initProperties() {
        if (System.getProperty("test.browser") == null) {
            System.setProperty("test.browser", FIREFOX);
        }
        browser = System.getProperty("test.browser");
    }
}

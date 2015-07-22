package utils;

import framework.CustomWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The {@code WaitUtils} class stores different customized methods that allows to work with sleeps and
 * waits more flexible
 *
 * @author Artem Tykhonov
 */
public class WaitUtils {

    /**
     * Causes the currently executing thread to sleep for
     * for the specified number of milliseconds
     *
     * @param seconds
     *        the length of time to sleep in seconds
     */
    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Causes the currently executing thread to wait for
     * the specified element no longer than specified number of seconds
     *
     * @param element
     *          the element to wait for
     * @param maxSleep
     *          the maximum length of time to wait in seconds
     */
    public static void waitingForElementDisplayed(final WebElement element, int maxSleep) {
        new WebDriverWait(CustomWebDriver.getInstance().getDriver(), maxSleep).
                until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver webDriver) {
                        return element.isDisplayed();
                    }
                });
    }
}

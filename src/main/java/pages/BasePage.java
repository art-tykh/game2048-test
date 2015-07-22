package pages;

import framework.CustomWebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;

/**
 * This class represents base page for all future pages
 *
 * @author Artem Tykhonov
 */
public class BasePage {

    public WebDriver driver;

    public BasePage() {
        driver = CustomWebDriver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    /**
     *
     * @return
     *      the current driver instance
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Workaround for Safari sendKeys() issue.
     * Uses JS script for emulating Keys event
     *
     * @param move
     *          the Keys event to execute
     */
    public void sendSafariKeyEvent(Keys move) {
        String [] keyMatcher = move.name().split("_"); // Change Keys move to JS format
        String script = "fireKey(arguments[0]);\n"
                + "function fireKey(arrow)\n"
                + "{\n"
                + "    var key;\n"
                + "    switch (arrow.toLowerCase())\n"
                + "    {\n"
                + "        case \"left\":\n"
                + "            key = 37;\n"
                + "            break;\n"
                + "        case \"right\":\n"
                + "            key = 39;\n"
                + "            break;\n"
                + "        case \"up\":\n"
                + "            key = 38;\n"
                + "            break;\n"
                + "        case \"down\":\n"
                + "            key = 40;\n"
                + "            break;\n"
                + "    }\n"
                + "    if (document.createEventObject)\n"
                + "    {\n"
                + "        var eventObj = document.createEventObject();\n"
                + "        eventObj.keyCode = key;\n"
                + "        document.documentElement.fireEvent(\"onkeydown\", eventObj);\n"
                + "    } else if (document.createEvent)\n"
                + "    {\n"
                + "        var eventObj = document.createEvent(\"Events\");\n"
                + "        eventObj.initEvent(\"keydown\", true, true);\n"
                + "        eventObj.which = key;\n"
                + "        document.documentElement.dispatchEvent(eventObj);\n"
                + "    }\n"
                + "} ";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script, keyMatcher[1].toLowerCase());
    }

}

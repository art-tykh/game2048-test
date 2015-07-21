package pages.game2048;

import framework.RandomGameBot;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import utils.WaitUtils;

import java.util.Date;
import java.util.List;

/**
 * This class represents http://gabrielecirulli.github.io/2048/ main page
 *
 * @author Artem Tykhonov
 */
public class Game2048MainPage extends BasePage {

    @FindBy(className = "grid-container")
    private WebElement gridContainer;

    @FindBy(className = "game-over")
    private List<WebElement> gameOverMessage;

    public Game2048MainPage() {
        driver.get("http://gabrielecirulli.github.io/2048/");
    }

    /**
     * Play simple game. Bot is doing the random actions
     */
    public void playGame() {
        RandomGameBot randomGameBot = new RandomGameBot();
        do {
            Keys move = randomGameBot.makeMove();
            gridContainer.sendKeys(move);

        } while (gameOverMessage.isEmpty());
        WaitUtils.sleep(5);
    }

    /**
     * Analize game grid
     * @return information about current game state: tiles position, game score etc.
     */
    public void getGameState() {
        Object jsResult = ((JavascriptExecutor) driver).executeScript("return localStorage.gameState");
        System.out.println(jsResult.toString());
        //return jsResult;
    }

}

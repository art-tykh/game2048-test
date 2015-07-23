package pages.game2048;

import framework.RandomGameBot;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import utils.LogUtils;
import utils.WaitUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * This class represents http://gabrielecirulli.github.io/2048/ main page
 *
 * @author Artem Tykhonov
 */
public class Game2048MainPage extends BasePage {

    @FindBy(className = "grid-container")
    private WebElement gridContainer;

    @FindBy(className = "restart-button")
    private WebElement newGameButton;

    @FindBy(className = "game-over")
    private List<WebElement> gameOverMessage;

    @FindBy(className = "score-container")
    private WebElement gameScore;

    @FindBy(className = "tile")
    private List<WebElement> tiles;

    @FindBy(className = "mailing-list-subscribe-button")
    private WebElement mailingListSubscribeButton;

    public Game2048MainPage() {
        getDriver().get("http://gabrielecirulli.github.io/2048/");
    }

    /**
     * Play simple game. Bot is doing random actions
     */
    public void playGame() {
        // Initialize new Bot to play the game
        RandomGameBot randomGameBot = new RandomGameBot();
        // move number
        long i = 0;
        // Start new game
        newGameButton.click();
        LogUtils.initLogFile("************* Game 2048 log *************\nInitial state\n");
        printCurrentBoard();
        // The Bot will play the game till the "Game over" message appears
        while (gameOverMessage.isEmpty()) {
            LogUtils.addToLogFile("Move #" + ++i + "\n");
            Keys move = randomGameBot.getMove();
            makeMove(gridContainer,move);
            printCurrentBoard();
        }
        String[] currentScore = gameScore.getText().split("\n");
        LogUtils.addToLogFile("Your total score is: " + currentScore[0] + "\nNice game!");
        WaitUtils.sleep(1);
    }

    /**
     * Analyzes current game grid and
     * prints current game board.
     *
     * getGameState() returns JSONObject like this:
     * {"size":4,"cells":[[null,null,null,null],[null,null,null,{"position":{"x":1,"y":3},"value":4}],
     * [null,null,{"position":{"x":2,"y":2},"value":2},null],[null,null,null,null]]}
     *
     * printCurrentBoard() parses this JSONObject and create matrix with current tiles values.
     * This matrix represents game board.
     *
     */
    private void printCurrentBoard() {
        long [][] tilesMatrix;
        try {
            if (getGameState() != null) {
                JSONObject grid = getGameState();
                JSONArray cells = grid.getJSONArray("cells");
                int size = grid.getInt("size");
                tilesMatrix = new long[size][size];
                for (int i = 0; i < size; i++) {
                    JSONArray cell = cells.getJSONArray(i);
                    for (int j = 0; j < size; j++) {
                        long value = 0;
                        if (cell.get(j) instanceof JSONObject) {
                            JSONObject tile = new JSONObject(cell.get(j).toString());
                            value = tile.getInt("value");
                        }
                        tilesMatrix[j][i] = value;
                    }
                }
                printBoard(tilesMatrix, size);
            } else {
                printEndBoardState();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return grid JSONObject - if board is available for game; null - if board is NOT available for game
     */
    private JSONObject getGameState() {
        Object jsResult = ((JavascriptExecutor) driver).executeScript("return localStorage.gameState");
        try {
            JSONObject grid = new JSONObject(jsResult.toString()).getJSONObject("grid");
            return grid;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Make one game move depends on browser type
     *
     * @param element
     *          the WebElement to interact with
     * @param move
     *          the Keys event to execute
     */
    private void makeMove(WebElement element, Keys move){
        if (getDriver() instanceof FirefoxDriver) {
            element.sendKeys(move);
        } else if (getDriver() instanceof SafariDriver) {
            sendSafariKeyEvent(move);
        } else {
            Actions actions = new Actions(getDriver());
            actions.moveToElement(element).click();
            actions.sendKeys(move).perform();
        }
    }

    /**
     * Prints end  board state (board state on the last move)
     *
     * At the last move we cannot get board state via getGameState(),
     * so we need to analyze each tile element and put it value onto matrix
     *
     * This matrix represents game board final state.
     */
    private void printEndBoardState() {
        long [][] tilesMatrix = new long[4][4];
        int i = 0;
        int j = 0;
        WaitUtils.waitingForElementDisplayed(mailingListSubscribeButton,5);
        for (WebElement tile : tiles) {
            tilesMatrix[i][j] = Long.valueOf(tile.getText());
            i++;
            if (i > 3) {
                i = 0;
                j++;
                if (j > 3) {
                    j = 0;
                }
            }
        }
        printBoard(tilesMatrix, 4);
    }

    /**
     * Prints game board
     * @param board - board to print
     * @param size - size of the board
     */
    private void printBoard(long[][] board, int size) {
        for (int k = 0; k < size; k++) {
            LogUtils.addToLogFile(" | ");
            for (int l = 0; l < size; l++) {
                LogUtils.addToLogFile(board[k][l] + " | ");
            }
            LogUtils.addToLogFile("\n");
        }
    }
}

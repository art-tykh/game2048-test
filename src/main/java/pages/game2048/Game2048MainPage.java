package pages.game2048;

import framework.RandomGameBot;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import utils.WaitUtils;

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

    @FindBy(className = "score-container")
    private WebElement gameScore;

    @FindBy(className = "tile")
    private List<WebElement> tiles;

    public Game2048MainPage() {
        driver.get("http://gabrielecirulli.github.io/2048/");
    }

    /**
     * Play simple game. Bot is doing random actions
     */
    public void playGame() {
        RandomGameBot randomGameBot = new RandomGameBot();
        long i = 1;
        System.out.println("************* Game 2048 log *************\nInitial state");
        printCurrentBoard();
        //while (i<3) {
        while (gameOverMessage.isEmpty()) {
            System.out.println("Move #" + i);
            Keys move = randomGameBot.makeMove();
            gridContainer.sendKeys(move);
            printCurrentBoard();
            i++;
        }
        String[] currentScore = gameScore.getText().split("\n");
        System.out.println("Your total score is: " + currentScore[0] + "\nNice game!");
        WaitUtils.sleep(5);
    }

    /**
     * Analize current game grid and
     * prints current game board.
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

    private void printEndBoardState() {
        long [][] tilesMatrix = new long[4][4];
        int i = 0;
        int j = 0;
        for (WebElement tile : tiles) {
            tilesMatrix[i][j] = Long.valueOf(tile.getText()).longValue();
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

    private void printBoard(long[][] board, int size) {
        for (int k = 0; k < size; k++) {
            System.out.print(" | ");
            for (int l = 0; l < size; l++) {
                System.out.print(board[k][l] + " | ");
            }
            System.out.print("\n");
        }
    }
}

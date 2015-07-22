package io.game2048.tests;

import org.testng.annotations.Test;
import pages.game2048.Game2048MainPage;

/**
 * This class contains tests for http://gabrielecirulli.github.io/2048/ web site
 */
public class Game2048MainPageTest extends BaseTest{

    private Game2048MainPage game2048MainPage;

    @Test
    public void playGame2048() {
        setBrowser();
        game2048MainPage = new Game2048MainPage();
        game2048MainPage.playGame();
    }

}

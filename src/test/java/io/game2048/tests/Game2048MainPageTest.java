package io.game2048.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.game2048.Game2048MainPage;

/**
 * Created by mbp on 7/21/15.
 */
public class Game2048MainPageTest {

    private Game2048MainPage game2048MainPage;

    @BeforeMethod
    public void setUpTest() {
        game2048MainPage = new Game2048MainPage();
    }

    @Test
    public void playGame2048() {
        //UserInteractionUtils.setBrowser();
        //game2048MainPage.getGameState();
        game2048MainPage.playGame();
        //Assert.assertEquals("ww", a);
    }

}

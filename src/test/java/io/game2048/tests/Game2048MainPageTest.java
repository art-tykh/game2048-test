package io.game2048.tests;

import framework.CustomWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.game2048.Game2048MainPage;

/**
 * Created by mbp on 7/21/15.
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

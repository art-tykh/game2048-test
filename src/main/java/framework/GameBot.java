package framework;

import org.openqa.selenium.Keys;

import java.util.List;

/**
 * Created by mbp on 7/21/15.
 */
public interface GameBot {

    public final Keys[] MOVES = new Keys[] {Keys.ARROW_DOWN, Keys.ARROW_UP, Keys.ARROW_LEFT, Keys.ARROW_RIGHT};

    Keys getMove();

}

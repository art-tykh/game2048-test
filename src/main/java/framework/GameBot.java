package framework;

import org.openqa.selenium.Keys;

/**
 * Represents GameBot interface.
 * Allows to create different game bots: random, smart(with prefered set of moves), reallySmart(that implements MiniMax algorithm), etc.
 *
 * @author Artem Tykhonov
 */
public interface GameBot {

    public final Keys[] MOVES = new Keys[] {Keys.ARROW_DOWN, Keys.ARROW_UP, Keys.ARROW_LEFT, Keys.ARROW_RIGHT};

    Keys getMove();

}

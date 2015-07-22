package framework;

import org.openqa.selenium.Keys;

import java.util.Random;

/**
 * Implements GameBot with random move strategy.
 * Bot selects randomly from the following set of moves:
 * Keys.ARROW_DOWN, Keys.ARROW_UP, Keys.ARROW_LEFT, Keys.ARROW_RIGHT
 */
public class RandomGameBot implements GameBot {

    private static final int SIZE = MOVES.length;
    private static final Random RANDOM = new Random();

    /**
     *
     * @return
     *       random Keys event
     */
    public Keys getMove() {
        return MOVES[RANDOM.nextInt(SIZE)];
    }
}

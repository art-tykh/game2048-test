package framework;

import org.openqa.selenium.Keys;

import java.util.Random;

/**
 * Created by mbp on 7/21/15.
 */
public class RandomGameBot implements GameBot {

    private static final int SIZE = MOVES.length;
    private static final Random RANDOM = new Random();

    public Keys getMove() {
        return MOVES[RANDOM.nextInt(SIZE)];
    }
}

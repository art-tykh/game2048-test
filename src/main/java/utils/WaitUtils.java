package utils;

/**
 * Created by mbp on 7/21/15.
 */
public class WaitUtils {

    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

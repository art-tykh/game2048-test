package utils;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The {@code LogUtils} class represents prototype of Logging framework.
 * For now logs are written to simple txt file.
 *
 * @author Artem Tykhonov
 */
public class LogUtils {

    private static BufferedWriter writer = null;

    /**
     * Creates new log file and writes some logs to it
     *
     * @param text
     *          the message to be logged
     */
    public static void initLogFile(String text) {
        writeToFile(text, false);
    }

    /**
     * Adds logs to already existing log file
     *
     * @param text
     *          the message to be logged
     */
    public static void addToLogFile(String text) {
        writeToFile(text, true);
    }

    /**
     * Constructs a FileWriter object.
     *
     * @param text
     *         the message to be logged
     * @param isFileExists
     *         if <code>true</code>, then data will be written
     *         to the end of the file rather than the beginning.
     */
    private static void writeToFile(String text, boolean isFileExists) {
        try {
            System.out.print(text);
            FileWriter fstream = new FileWriter("test-output/game2048_log.txt", isFileExists);
            writer = new BufferedWriter(fstream);
            writer.write(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

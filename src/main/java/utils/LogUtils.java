package utils;

import java.io.*;

/**
 * Created by mbp on 7/21/15.
 */
public class LogUtils {

    private static BufferedWriter writer = null;

    public static void initLogFile(String text) {
        writeToFile(text, false);
    }

    public static void addToLogFile(String text) {
        writeToFile(text, true);
    }

    private static void writeToFile(String text, boolean isFileExists) {
        try {
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

package framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by mbp on 7/21/15.
 */
public class DataProvider  {
    private static DataProvider instance;
    private Properties properties;

    public DataProvider() {
        String propertiesFileName = "project.properties";
        try {
            properties = new Properties();
            properties.load(new FileInputStream(new File(ClassLoader.getSystemResource(propertiesFileName).toURI())));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static DataProvider getInstance() {
        Lock lock;
        if (instance == null) {
            lock = new ReentrantLock();
            lock.lock();

            if (instance == null) {
                instance = new DataProvider();
            }

            lock.unlock();
        }
        return instance;
    }

    public Properties getProperties() {
        return properties;
    }
}

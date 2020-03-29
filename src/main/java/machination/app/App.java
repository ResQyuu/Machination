/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package machination.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import machination.util.ConfigReader;
import machination.util.ConfigReader.InvalidConfigurationValueException;
import machination.view.Window;
import machination.view.Window.WindowInitializationError;
import machination.view.window.DisplayResolution;

public class App implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String APP_TITLE = "Machination";

    public static Window window;

    public static void main(String[] args) {
        new App().start();
    }

    public void start() {
        Thread appThread = new Thread(this, APP_TITLE.toLowerCase());
        appThread.start();
    }

    private static void init() {
        LOGGER.info("Starting Machination");
        try {
            window = createAppWindow();
            window.create();
        } catch (WindowInitializationError e) {
            e.printStackTrace();
        }
    }

    public void run() {
        init();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {

        }
    }

    private static Window createAppWindow() throws WindowInitializationError {
        DisplayResolution resolution = null;
        try {
            int windowWidth = ConfigReader.readIntValue("windowWidth");
            int windowHeight = ConfigReader.readIntValue("windowHeight");
            resolution = new DisplayResolution(windowWidth, windowHeight);
        } catch (InvalidConfigurationValueException e) {
            LOGGER.error(e.getMessage());
        }
        return new Window(resolution, APP_TITLE);
    }
}
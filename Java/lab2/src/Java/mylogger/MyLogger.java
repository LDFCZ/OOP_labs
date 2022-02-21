package Java.mylogger;

import Java.constspace.CONSTSPACE;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class MyLogger {

    private static final Logger log = Logger.getLogger(CONSTSPACE.LOG_NAME);
    private final FileHandler fh;

    public MyLogger(String logPath) throws IOException{
        log.setUseParentHandlers(false);
        System.setProperty(CONSTSPACE.LOG_PROPERTY, CONSTSPACE.LOG_FORMAT);
        fh = new FileHandler(logPath);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        log.addHandler(fh);

        log.info(CONSTSPACE.LOG_INFO);
    }

    public void log(Level level, String className, String message) {
        log.log(level, CONSTSPACE.OPEN_BRACKET + className + CONSTSPACE.CLOSE_BRACKET + message);
    }

    public void close() {
        fh.close();
    }

}

package Java;

import Java.constspace.CONSTSPACE;
import Java.executors.Executor;
import Java.executors.FileExecutor;
import Java.executors.SISExecutor;
import Java.mylogger.MyLogger;


import java.io.IOException;
import java.util.logging.Level;

public class Main {

    private static final String className = Main.class.getSimpleName();

    public static void main(String[] args) {
        MyLogger log = null;

        try {
             log = new MyLogger(CONSTSPACE.PATH_TO_LOG_FILE);
        }
        catch (IOException ex) {
            System.out.print(CONSTSPACE.LOGGER_EXCEPTION);
            ex.printStackTrace();
            return;
        }

        log.log(Level.FINE, className,CONSTSPACE.START_MESSAGE);
        Executor executor = null;
        if (args.length > CONSTSPACE.MAX_INPUT_ARGS_COUNT) {
            System.out.print(CONSTSPACE.LOGGER_EXCEPTION);
            log.log(Level.SEVERE , className, CONSTSPACE.ARGS_COUNT_EXCEPTION);
            log.close();
            return;
        }
        else if (args.length == CONSTSPACE.MAX_INPUT_ARGS_COUNT) {
            try {
                executor = new FileExecutor(log, args[CONSTSPACE.FILE_NAME_ARG]);

            } catch (IOException ex) {
                log.log(Level.SEVERE , className, CONSTSPACE.FILE_EXCEPTION);
                ex.printStackTrace();
                log.close();
                return;
            }
        }
        else {
            executor = new SISExecutor(log);
        }

        executor.execute();
        log.close();
    }
}

package lopatkin.lab2;


import lopatkin.lab2.constspace.ConstSpace;
import lopatkin.lab2.executors.Executor;
import lopatkin.lab2.executors.FileExecutor;
import lopatkin.lab2.executors.SISExecutor;

import java.io.IOException;


import org.apache.log4j.Logger;


public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        log.info(ConstSpace.START_MESSAGE);
        Executor executor = null;
        if (args.length > ConstSpace.MAX_INPUT_ARGS_COUNT) {
            System.out.print(ConstSpace.ARGS_COUNT_EXCEPTION);
            log.error(ConstSpace.ARGS_COUNT_EXCEPTION);
            return;
        }
        else if (args.length == ConstSpace.MAX_INPUT_ARGS_COUNT) {
            try {
                executor = new FileExecutor(args[ConstSpace.FILE_NAME_ARG]);

            } catch (IOException ex) {
                log.error(ConstSpace.FILE_EXCEPTION);
                ex.printStackTrace();
                return;
            }
        }
        else {
            executor = new SISExecutor();
        }
        executor.execute();
        log.info(ConstSpace.END_MESSAGE);
    }
}

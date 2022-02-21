package Java.executors;

import Java.commands.Command;
import Java.constspace.CONSTSPACE;
import Java.contexts.CommandContext;
import Java.exceptions.CommandNotFoundException;
import Java.exceptions.CommandRunTimeException;
import Java.factory.CommandFactory;
import Java.mylogger.MyLogger;

import java.lang.reflect.InvocationTargetException;

import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;

public class SISExecutor implements Executor {

    private final MyLogger log;


    public SISExecutor(MyLogger log) {
        this.log = log;

        this.log.log(Level.INFO, this.getClass().getSimpleName(), CONSTSPACE.START_MESSAGE_SIS_EXECUTOR);
    }

    @Override
    public void execute() {
        boolean isCalculating = true;

        CommandContext context = new CommandContext();

        Scanner sc = new Scanner(System.in);
        System.out.print(CONSTSPACE.WELCOME_STRING + System.lineSeparator());
        
        while (true) {
            String line = sc.nextLine();
            if (line.equals(CONSTSPACE.EXIT_COMMAND)) {
                sc.close();
                return;
            }

            List<String> args = Arrays.asList(line.split(CONSTSPACE.SPACE));
            String commandName = line.split(CONSTSPACE.SPACE)[CONSTSPACE.COMMAND_NAME_ARG];
            log.log(Level.INFO, this.getClass().getSimpleName(), CONSTSPACE.LOG_READ_INFO_1 + commandName + CONSTSPACE.LOG_READ_INFO_2);
            Command command = null;
            try {
                command = CommandFactory.getInstance().getCommand(commandName, args);
            }
            catch (CommandNotFoundException ex) {
                log.log(Level.SEVERE, this.getClass().getSimpleName(), CONSTSPACE.LOG_CREATE_SEVERE_1 + commandName + CONSTSPACE.LOG_CREATE_SEVERE_2 );
                System.out.print(ex.getMessage() + System.lineSeparator());
                continue;
            }
            catch (InvocationTargetException ex) {
                log.log(Level.SEVERE, this.getClass().getSimpleName(), CONSTSPACE.ARGS_ERROR + commandName);
                System.out.print(CONSTSPACE.ARGS_ERROR + commandName + System.lineSeparator());
                continue;
            }
            catch (Exception  ex) {
                log.log(Level.SEVERE, this.getClass().getSimpleName(), CONSTSPACE.OTHER_ERRORS);
                System.out.print(ex.getMessage() + System.lineSeparator());
                ex.printStackTrace();
                return;
            }

            try {
               String res = command.execute(context);
               if (res != null)
                    System.out.print(res + System.lineSeparator());
            }
            catch (CommandRunTimeException ex) {
                log.log(Level.SEVERE, this.getClass().getSimpleName(), CONSTSPACE.EXECUTION_ERROR + commandName);
                System.out.print(ex.getMessage() + System.lineSeparator());
            }
        }

    }
}

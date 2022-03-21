package lopatkin.lab2.executors;

import lopatkin.lab2.commands.Command;
import lopatkin.lab2.constspace.ConstSpace;
import lopatkin.lab2.contexts.CommandContext;
import lopatkin.lab2.exceptions.CommandNotFoundException;
import lopatkin.lab2.exceptions.CommandRunTimeException;
import lopatkin.lab2.factory.CommandFactory;
import org.apache.log4j.Logger;


import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class SISExecutor implements Executor {

    private static final Logger log = Logger.getLogger(SISExecutor.class);

    {
        log.info(ConstSpace.START_MESSAGE_SIS_EXECUTOR);
    }

    @Override
    public void execute() {
        CommandContext context = new CommandContext();

        Scanner sc = new Scanner(System.in);
        System.out.print(ConstSpace.WELCOME_STRING + System.lineSeparator());
        
        while (true) {
            String line = sc.nextLine();
            if (line.equals(ConstSpace.EXIT_COMMAND)) {
                sc.close();
                return;
            }

            List<String> args = Arrays.asList(line.split(ConstSpace.SPACE));
            String commandName = line.split(ConstSpace.SPACE)[ConstSpace.COMMAND_NAME_ARG];
            log.info(ConstSpace.LOG_READ_INFO_1 + commandName + ConstSpace.LOG_READ_INFO_2);
            try {
                Command command = CommandFactory.getInstance().getCommand(commandName, args);
                String res = command.execute(context);
                if (res != null)
                    System.out.print(res + System.lineSeparator());
            }
            catch (CommandNotFoundException ex) {
                log.error(ConstSpace.LOG_CREATE_SEVERE_1 + commandName + ConstSpace.LOG_CREATE_SEVERE_2 );
                System.out.print(ex.getMessage() + System.lineSeparator());
            }
            catch (InvocationTargetException ex) {
                log.error(ConstSpace.ARGS_ERROR + commandName);
                System.out.print(ConstSpace.ARGS_ERROR + commandName + System.lineSeparator());
            }
            catch (CommandRunTimeException ex) {
                log.error(ConstSpace.EXECUTION_ERROR + commandName);
                System.out.print(ex.getMessage() + System.lineSeparator());
            }
            catch (Exception  ex) {
                log.error(ConstSpace.OTHER_ERRORS);
                System.out.print(ex.getMessage() + System.lineSeparator());
                ex.printStackTrace();
                return;
            }
        }

    }
}

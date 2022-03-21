package lopatkin.lab2.executors;


import lopatkin.lab2.commands.Command;
import lopatkin.lab2.constspace.ConstSpace;
import lopatkin.lab2.contexts.CommandContext;
import lopatkin.lab2.exceptions.CommandNotFoundException;
import lopatkin.lab2.exceptions.CommandRunTimeException;
import lopatkin.lab2.exceptions.ParseExeption;
import lopatkin.lab2.factory.CommandFactory;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FileExecutor implements Executor{

    private final BufferedReader buffer;
    private final FileInputStream fileStream;

    private final List<Command> commandList;


    private static final Logger log = Logger.getLogger(SISExecutor.class);

    public FileExecutor(String fileName) throws IOException {

        this.fileStream = new FileInputStream(fileName);
        this.buffer = new BufferedReader(new InputStreamReader(fileStream));

        this.commandList = new ArrayList<>();

        log.info(ConstSpace.START_MESSAGE_FILE_EXECUTOR);
    }

    @Override
    public void execute() {

        CommandContext context = new CommandContext();
        log.info(ConstSpace.LOG_READ_INFO_3);
        try {
            this.parseCommands();
        }
        catch (ParseExeption ex) {
            System.out.print(ex.getMessage());
            ex.printStackTrace();
            return;
        }
        log.info(ConstSpace.LOG_EXECUTE_INFO_1);
        for (Command command : commandList) {
            try {
                log.info(ConstSpace.LOG_EXECUTE_INFO_2 + command.getCommandName());
                String res = command.execute(context);
                if (res != null)
                    System.out.print(res + System.lineSeparator());
            } catch (CommandRunTimeException ex) {
                log.error(ConstSpace.EXECUTION_ERROR + command.getCommandName());
                System.out.print(ex.getMessage());
                ex.printStackTrace();
                return;
            }
        }
    }

    private void parseCommands() throws ParseExeption {
        String line;
        int lineCount = 0;
        try {
            while ((line = buffer.readLine()) != null) {
                lineCount++;
                if(line.charAt(0) == ConstSpace.COMMENT) continue;
                List<String> args = Arrays.asList(line.split(ConstSpace.SPACE));
                String commandName = line.split(ConstSpace.SPACE)[ConstSpace.COMMAND_NAME_ARG];
                log.info(ConstSpace.LOG_READ_INFO_1 + commandName + ConstSpace.LOG_READ_INFO_2);
                commandList.add(CommandFactory.getInstance().getCommand(commandName, args));
            }
            close();
        }
        catch (IOException ex) {
            log.error(ConstSpace.PROPERTIES_ERROR);
            System.out.print(ex.getMessage());
            ex.printStackTrace();
            throw new ParseExeption(ConstSpace.FILE_ERROR);
        } catch (CommandNotFoundException ex) {
            log.error(ConstSpace.NO_COMMAND_ERROR + System.lineSeparator() +ex.getMessage());
            System.out.print(ex.getMessage() + ConstSpace.LINE + Integer.toString(lineCount));
            ex.printStackTrace();
            throw new ParseExeption(ConstSpace.NO_COMMAND_ERROR);
        } catch (InvocationTargetException ex) {
            log.error(ConstSpace.FILE_ARGS_ERROR);
            System.out.print(ex.getMessage() + ConstSpace.LINE + Integer.toString(lineCount));
            ex.printStackTrace();
            throw new ParseExeption(ConstSpace.FILE_ARGS_ERROR);
        } catch (Exception ex) {
            log.error(ConstSpace.UNKNOWN_ERRORS);
            System.out.print(ex.getMessage() + ConstSpace.LINE + Integer.toString(lineCount));
            ex.printStackTrace();
            throw new ParseExeption(ConstSpace.UNKNOWN_ERRORS);
        }

    }

    private void close() throws IOException {
        buffer.close();
        fileStream.close();
    }
}

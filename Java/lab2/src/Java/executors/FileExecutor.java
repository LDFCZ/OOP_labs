package Java.executors;

import Java.commands.Command;
import Java.constspace.CONSTSPACE;
import Java.contexts.CommandContext;
import Java.exceptions.CommandNotFoundException;
import Java.exceptions.CommandRunTimeException;
import Java.exceptions.ParseExeption;
import Java.factory.CommandFactory;
import Java.mylogger.MyLogger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.ArrayList;

import java.util.List;

import java.util.logging.Level;

public class FileExecutor implements Executor{

    private final MyLogger log;

    private final BufferedReader buffer;
    private final FileInputStream fileStream;

    private final List<Command> commandList;


    public FileExecutor(MyLogger log, String fileName) throws IOException {
        this.log = log;

        this.fileStream = new FileInputStream(fileName);
        this.buffer = new BufferedReader(new InputStreamReader(fileStream));

        this.commandList = new ArrayList<>();

        this.log.log(Level.INFO, this.getClass().getSimpleName(), CONSTSPACE.START_MESSAGE_FILE_EXECUTOR);
    }

    @Override
    public void execute() {

        CommandContext context = new CommandContext();
        log.log(Level.INFO, this.getClass().getSimpleName(), CONSTSPACE.LOG_READ_INFO_3);
        try {
            this.parseCommands();
        }
        catch (ParseExeption ex) {
            System.out.print(ex.getMessage());
            ex.printStackTrace();
            return;
        }
        log.log(Level.INFO, this.getClass().getSimpleName(), CONSTSPACE.LOG_EXECUTE_INFO_1);
        for (Command command : commandList) {
            try {
                log.log(Level.INFO, this.getClass().getSimpleName(), CONSTSPACE.LOG_EXECUTE_INFO_2 + command.getCommandName());
                command.execute(context);
            } catch (CommandRunTimeException ex) {
                log.log(Level.SEVERE, this.getClass().getSimpleName(), CONSTSPACE.EXECUTION_ERROR + command.getCommandName());
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
                if(line.charAt(0) == CONSTSPACE.COMMENT) continue;
                List<String> args = Arrays.asList(line.split(CONSTSPACE.SPACE));
                String commandName = line.split(CONSTSPACE.SPACE)[CONSTSPACE.COMMAND_NAME_ARG];
                log.log(Level.INFO, this.getClass().getSimpleName(), CONSTSPACE.LOG_READ_INFO_1 + commandName + CONSTSPACE.LOG_READ_INFO_2);
                commandList.add(CommandFactory.getInstance().getCommand(commandName, args));
            }
        }
        catch (IOException ex) {
            log.log(Level.SEVERE, this.getClass().getSimpleName(), CONSTSPACE.PROPERTIES_ERROR);
            System.out.print(ex.getMessage());
            ex.printStackTrace();
            throw new ParseExeption(CONSTSPACE.FILE_ERROR);
        } catch (CommandNotFoundException ex) {
            log.log(Level.SEVERE, this.getClass().getSimpleName(), CONSTSPACE.NO_COMMAND_ERROR + System.lineSeparator() +ex.getMessage());
            System.out.print(ex.getMessage() + CONSTSPACE.LINE + Integer.toString(lineCount));
            ex.printStackTrace();
            throw new ParseExeption(CONSTSPACE.NO_COMMAND_ERROR);
        } catch (InvocationTargetException ex) {
            log.log(Level.SEVERE, this.getClass().getSimpleName(), CONSTSPACE.FILE_ARGS_ERROR);
            System.out.print(ex.getMessage() + CONSTSPACE.LINE + Integer.toString(lineCount));
            ex.printStackTrace();
            throw new ParseExeption(CONSTSPACE.FILE_ARGS_ERROR);
        } catch (Exception ex) {
            log.log(Level.SEVERE, this.getClass().getSimpleName(), CONSTSPACE.UNKNOWN_ERRORS);
            System.out.print(ex.getMessage() + CONSTSPACE.LINE + Integer.toString(lineCount));
            ex.printStackTrace();
            throw new ParseExeption(CONSTSPACE.UNKNOWN_ERRORS);
        }
    }

    private void close() throws IOException {
        buffer.close();
        fileStream.close();
    }
}

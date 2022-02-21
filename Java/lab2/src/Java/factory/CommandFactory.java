package Java.factory;

import Java.commands.Command;
import Java.constspace.CONSTSPACE;
import Java.exceptions.CommandNotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

public class CommandFactory {

    private static volatile CommandFactory commandFactory;
    private final Properties commandProperties = new Properties();

    public CommandFactory() throws IOException {
        commandProperties.load(CommandFactory.class.getClassLoader().getResourceAsStream(CONSTSPACE.COMMAND_PATH));
    }

    public static CommandFactory getInstance() throws IOException {
        //Double-check with synchronized block for thread saving
        if (commandFactory == null) {
            synchronized (CommandFactory.class) {
                if (commandFactory == null) {
                    commandFactory = new CommandFactory();
                }
            }
        }
        return commandFactory;
    }

    public Command getCommand(String commandName, List<String> args)
            throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, CommandNotFoundException{

        Properties config = new Properties();
        config.load(CommandFactory.class.getClassLoader().getResourceAsStream(CONSTSPACE.CONFIG_PATH));

        if (!commandProperties.containsKey(commandName)) {
            throw new CommandNotFoundException(CONSTSPACE.COMMAND_NAME_ERROR_1 + commandName + CONSTSPACE.COMMAND_NAME_ERROR_2);

        }
        return (Command) Class.forName(config.getProperty(CONSTSPACE.COMMAND_CLASS_PATH)
                + commandProperties.get(commandName)).getConstructor(new Class[]{List.class}).newInstance(args);
    }
}

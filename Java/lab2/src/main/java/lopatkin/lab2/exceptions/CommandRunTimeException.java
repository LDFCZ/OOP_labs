package lopatkin.lab2.exceptions;


import lopatkin.lab2.constspace.ConstSpace;

public class CommandRunTimeException extends RuntimeException {
    public CommandRunTimeException (String commandName, String message) {
        super(commandName + ConstSpace.SPACE + message);
    }
}

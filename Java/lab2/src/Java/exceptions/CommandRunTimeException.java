package Java.exceptions;

import Java.constspace.CONSTSPACE;

public class CommandRunTimeException extends RuntimeException {
    public CommandRunTimeException (String commandName, String message) {
        super(commandName + CONSTSPACE.SPACE + message);
    }
}

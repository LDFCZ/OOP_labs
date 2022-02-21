package Java.exceptions;

import Java.constspace.CONSTSPACE;

public class CommandRunTimeException extends Exception {
    public CommandRunTimeException (String commandName, String message) {
        super(commandName + CONSTSPACE.SPACE + message);
    }
}

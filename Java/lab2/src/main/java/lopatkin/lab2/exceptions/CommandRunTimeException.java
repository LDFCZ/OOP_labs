package lopatkin.lab2.exceptions;


import lopatkin.lab2.constspace.ConstSpace;

//This is Exception! Not RunTime! It is just a name. There are very much differences between runtime and simple Exceptions!
public class CommandRunTimeException extends Exception {
    public CommandRunTimeException (String commandName, String message) {
        super(commandName + ConstSpace.SPACE + message);
    }
}

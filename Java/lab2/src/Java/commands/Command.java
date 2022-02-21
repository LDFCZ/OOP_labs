package Java.commands;

import Java.constspace.CONSTSPACE;
import Java.contexts.Context;
import Java.exceptions.BadArgsException;
import Java.exceptions.CommandRunTimeException;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public abstract class Command {
    private final List<String> args;

    public Command(List<String> args) throws BadArgsException {
        this.args = args;
    }

    public String getCommandName() {
        return args.get(CONSTSPACE.COMMAND_NAME_ARG);
    }

    protected List<String> getArgs() {
        return args;
    }

    public abstract String execute(Context<Double> context) throws CommandRunTimeException;

}

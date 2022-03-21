package lopatkin.lab2.commands;

import lopatkin.lab2.constspace.ConstSpace;
import lopatkin.lab2.contexts.Context;
import lopatkin.lab2.exceptions.BadArgsException;
import lopatkin.lab2.exceptions.CommandRunTimeException;

import java.util.List;

public abstract class Command {
    private final List<String> args;

    public Command(List<String> args) throws BadArgsException {
        this.args = args;
    }

    public String getCommandName() {
        return args.get(ConstSpace.COMMAND_NAME_ARG);
    }

    protected List<String> getArgs() {
        return args;
    }

    public abstract String execute(Context<Double> context) throws CommandRunTimeException;

}

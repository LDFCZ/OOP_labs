package Java.commands;

import Java.constspace.CONSTSPACE;
import Java.contexts.Context;
import Java.exceptions.BadArgsException;
import Java.exceptions.CommandRunTimeException;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class PrintCommand extends Command {
    public PrintCommand(List<String> args) throws BadArgsException {
        super(args);
        if (args.size() != CONSTSPACE.COMMON_COMMANDS_ARGS_COUNT) throw new BadArgsException(CONSTSPACE.ARGS_ERROR);
    }

    @Override
    public String execute(Context<Double> context) throws CommandRunTimeException {
        try {
            return context.viewTopElement().toString();
        }
        catch (EmptyStackException ex) {
            throw new CommandRunTimeException(this.getClass().getName(), CONSTSPACE.STACK_ERROR);
        }
    }
}

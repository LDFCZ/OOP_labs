package Java.commands;

import Java.constspace.CONSTSPACE;
import Java.contexts.Context;
import Java.exceptions.BadArgsException;
import Java.exceptions.CommandRunTimeException;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static java.lang.Math.sqrt;

public class SqrtCommand extends Command {
    public SqrtCommand(List<String> args) throws BadArgsException {
        super(args);
        if (args.size() != CONSTSPACE.COMMON_COMMANDS_ARGS_COUNT) throw new BadArgsException(CONSTSPACE.ARGS_ERROR);
    }

    @Override
    public String execute(Context<Double> context) throws CommandRunTimeException {
        double a;
        try {
            a = context.getTopElement();
        }
        catch (EmptyStackException ex) {
            throw new CommandRunTimeException(this.getClass().getName(), CONSTSPACE.STACK_ERROR);
        }
        if (a < 0) {
            context.addElementToTop(a);
            throw new CommandRunTimeException(this.getClass().getName(), CONSTSPACE.NEGATIVE_NUM_ERROR);
        }
        context.addElementToTop(sqrt(a));

        return null;
    }
}

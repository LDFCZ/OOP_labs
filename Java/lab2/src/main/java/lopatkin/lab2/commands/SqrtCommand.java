package lopatkin.lab2.commands;


import lopatkin.lab2.constspace.ConstSpace;
import lopatkin.lab2.contexts.Context;
import lopatkin.lab2.exceptions.BadArgsException;
import lopatkin.lab2.exceptions.CommandRunTimeException;

import java.util.EmptyStackException;
import java.util.List;

import static java.lang.Math.sqrt;

public class SqrtCommand extends Command {
    public SqrtCommand(List<String> args) throws BadArgsException {
        super(args);
        if (args.size() != ConstSpace.COMMON_COMMANDS_ARGS_COUNT) throw new BadArgsException(ConstSpace.ARGS_ERROR);
    }

    @Override
    public String execute(Context<Double> context) throws CommandRunTimeException {
        double a;
        try {
            a = context.getTopElement();
        }
        catch (EmptyStackException ex) {
            throw new CommandRunTimeException(this.getClass().getName(), ConstSpace.STACK_ERROR);
        }
        if (a < 0) {
            context.addElementToTop(a);
            throw new CommandRunTimeException(this.getClass().getName(), ConstSpace.NEGATIVE_NUM_ERROR);
        }
        context.addElementToTop(sqrt(a));

        return null;
    }
}

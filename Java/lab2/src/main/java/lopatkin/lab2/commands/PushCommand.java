package lopatkin.lab2.commands;


import lopatkin.lab2.constspace.ConstSpace;
import lopatkin.lab2.contexts.Context;
import lopatkin.lab2.exceptions.BadArgsException;
import lopatkin.lab2.exceptions.BadVariableException;
import lopatkin.lab2.exceptions.CommandRunTimeException;

import java.util.List;
import java.util.Scanner;

public class PushCommand extends Command {
    public PushCommand(List<String> args) throws BadArgsException {
        super(args);
        if (args.size() != ConstSpace.PUSH_COMMAND_ARGS_COUNT) throw new BadArgsException(ConstSpace.ARGS_ERROR);
    }

    @Override
    public String execute(Context<Double> context) throws CommandRunTimeException {
        double number;
        if (new Scanner(this.getArgs().get(ConstSpace.PUSH_ARG)).hasNextDouble()) {
            number = Double.parseDouble(this.getArgs().get(ConstSpace.PUSH_ARG));
            context.addElementToTop(number);
            return null;
        }
        try {
            context.addElementToTop(context.getVariableValue(this.getArgs().get(ConstSpace.PUSH_ARG)));
        } catch (BadVariableException ex) {
            throw new CommandRunTimeException(this.getClass().getSimpleName(), this.getArgs().get(ConstSpace.PUSH_ARG) + ConstSpace.NAN_OR_NAV_ERROR);
        }
        return null;
    }
}

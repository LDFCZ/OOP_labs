package Java.commands;

import Java.constspace.CONSTSPACE;
import Java.contexts.Context;
import Java.exceptions.BadArgsException;
import Java.exceptions.BadVariableException;
import Java.exceptions.CommandRunTimeException;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class PushCommand extends Command {
    public PushCommand(List<String> args) throws BadArgsException {
        super(args);
        if (args.size() != CONSTSPACE.PUSH_COMMAND_ARGS_COUNT) throw new BadArgsException(CONSTSPACE.ARGS_ERROR);
    }

    @Override
    public String execute(Context<Double> context) throws CommandRunTimeException {
        double number;
        if (new Scanner(this.getArgs().get(CONSTSPACE.PUSH_ARG)).hasNextDouble()) {
            number = Double.parseDouble(this.getArgs().get(CONSTSPACE.PUSH_ARG));
            context.addElementToTop(number);
            return null;
        }
        try {
            context.addElementToTop(context.getVariableValue(this.getArgs().get(CONSTSPACE.PUSH_ARG)));
        } catch (BadVariableException ex) {
            throw new CommandRunTimeException(this.getClass().getSimpleName(), this.getArgs().get(CONSTSPACE.PUSH_ARG) + CONSTSPACE.NAN_OR_NAV_ERROR);
        }
        return null;
    }
}

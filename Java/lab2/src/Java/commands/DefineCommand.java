package Java.commands;

import Java.constspace.CONSTSPACE;
import Java.contexts.Context;
import Java.exceptions.BadArgsException;
import Java.exceptions.BadVariableException;
import Java.exceptions.CommandRunTimeException;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefineCommand extends Command {
    public DefineCommand(List<String> args) throws BadArgsException {
        super(args);
        if (args.size() != CONSTSPACE.DEFINE_COMMAND_ARGS_COUNT) throw new BadArgsException(CONSTSPACE.ARGS_ERROR);
    }

    @Override
    public String execute(Context<Double> context) throws CommandRunTimeException {

        double number;

        try {
            number = Double.parseDouble(this.getArgs().get(CONSTSPACE.NUM_NAME_ARG));
            context.addVariable(this.getArgs().get(CONSTSPACE.VAR_NAME_ARG), number);
        } catch (NumberFormatException ex) {
            throw new CommandRunTimeException(this.getClass().getSimpleName(), this.getArgs().get(CONSTSPACE.NUM_NAME_ARG) + CONSTSPACE.NAN_ERROR);
        } catch (BadVariableException ex) {
            throw new CommandRunTimeException(this.getClass().getSimpleName(), this.getArgs().get(CONSTSPACE.VAR_NAME_ARG) + CONSTSPACE.BAD_NAME_ERROR);
        }
        
        return null;
    }
}

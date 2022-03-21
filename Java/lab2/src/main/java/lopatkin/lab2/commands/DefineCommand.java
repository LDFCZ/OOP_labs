package lopatkin.lab2.commands;


import lopatkin.lab2.constspace.ConstSpace;
import lopatkin.lab2.contexts.Context;
import lopatkin.lab2.exceptions.BadArgsException;
import lopatkin.lab2.exceptions.BadVariableException;
import lopatkin.lab2.exceptions.CommandRunTimeException;

import java.util.List;

public class DefineCommand extends Command {
    public DefineCommand(List<String> args) throws BadArgsException {
        super(args);
        if (args.size() != ConstSpace.DEFINE_COMMAND_ARGS_COUNT) throw new BadArgsException(ConstSpace.ARGS_ERROR);
    }

    @Override
    public String execute(Context<Double> context) throws CommandRunTimeException {

        double number;

        try {
            number = Double.parseDouble(this.getArgs().get(ConstSpace.NUM_NAME_ARG));
            context.addVariable(this.getArgs().get(ConstSpace.VAR_NAME_ARG), number);
        } catch (NumberFormatException ex) {
            try {
                number = context.getVariableValue(this.getArgs().get(ConstSpace.NUM_NAME_ARG));
                context.addVariable(this.getArgs().get(ConstSpace.VAR_NAME_ARG), number);
            } catch (BadVariableException e) {
                throw new CommandRunTimeException(this.getClass().getSimpleName(), this.getArgs().get(ConstSpace.NUM_NAME_ARG) + ConstSpace.NAN_OR_NAV_ERROR);
            }
        } catch (BadVariableException ex) {
            throw new CommandRunTimeException(this.getClass().getSimpleName(), this.getArgs().get(ConstSpace.VAR_NAME_ARG) + ConstSpace.BAD_NAME_ERROR);
        }
        
        return null;
    }
}

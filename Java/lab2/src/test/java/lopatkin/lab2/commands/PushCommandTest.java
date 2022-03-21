package lopatkin.lab2.commands;

import lopatkin.lab2.contexts.CommandContext;
import lopatkin.lab2.exceptions.BadArgsException;
import lopatkin.lab2.exceptions.BadVariableException;
import lopatkin.lab2.exceptions.CommandRunTimeException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


class PushCommandTest {


    @Test
    void executeSimpleTestNum() throws CommandRunTimeException, BadArgsException {
        PushCommand pushCommand = new PushCommand(new ArrayList<String>(Arrays.asList("PUSH 9".split(" "))));
        CommandContext context = new CommandContext();

        pushCommand.execute(context);

        assertEquals(9, context.getTopElement());
    }

    @Test
    void executeExceptionTestNoSuchVar() throws CommandRunTimeException, BadArgsException {
        PushCommand pushCommand = new PushCommand(new ArrayList<String>(Arrays.asList("PUSH a".split(" "))));
        CommandContext context = new CommandContext();


        Exception exception = assertThrows(CommandRunTimeException.class, () -> {
            pushCommand.execute(context);
        });

        String expectedMessage = "a - Not a number or variable";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void executeSimpleTestVar() throws CommandRunTimeException, BadArgsException, BadVariableException {
        PushCommand pushCommand = new PushCommand(new ArrayList<String>(Arrays.asList("PUSH a".split(" "))));
        CommandContext context = new CommandContext();

        context.addVariable("a", 9.0);

        pushCommand.execute(context);

        assertEquals(9, context.getTopElement());
    }
}

package Java.tests;

import Java.commands.PushCommand;
import Java.contexts.CommandContext;
import Java.exceptions.BadArgsException;
import Java.exceptions.BadVariableException;
import Java.exceptions.CommandRunTimeException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


class PushCommandTest {


    @Test
    void executeSimpleTest1() throws CommandRunTimeException, BadArgsException {
        PushCommand pushCommand = new PushCommand(new ArrayList<String>(Arrays.asList("PUSH 9".split(" "))));
        CommandContext context = new CommandContext();

        pushCommand.execute(context);

        assertEquals(9, context.getTopElement());
    }

    @Test
    void executeExceptionTest1() throws CommandRunTimeException, BadArgsException {
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
    void executeSimpleTest2() throws CommandRunTimeException, BadArgsException, BadVariableException {
        PushCommand pushCommand = new PushCommand(new ArrayList<String>(Arrays.asList("PUSH a".split(" "))));
        CommandContext context = new CommandContext();

        context.addVariable("a", 9.0);

        pushCommand.execute(context);

        assertEquals(9, context.getTopElement());
    }
}
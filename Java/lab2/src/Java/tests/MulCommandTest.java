package Java.tests;

import Java.commands.MulCommand;
import Java.contexts.CommandContext;
import Java.exceptions.BadArgsException;
import Java.exceptions.CommandRunTimeException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MulCommandTest {
    private final MulCommand mullCommand = new MulCommand(new ArrayList<String>(Arrays.asList("*".split(" "))));


    MulCommandTest() throws BadArgsException {
    }

    @Test
    void executeSimpleTest() throws CommandRunTimeException {
        CommandContext context = new CommandContext();

        context.addElementToTop(5.0);
        context.addElementToTop(6.0);

        mullCommand.execute(context);

        assertEquals(6.0*5.0, context.getTopElement());
    }

    @Test
    void executeExceptionTest1() throws CommandRunTimeException {
        CommandContext context = new CommandContext();

        Exception exception = assertThrows(CommandRunTimeException.class, () -> {
            mullCommand.execute(context);
        });

        String expectedMessage = "Stack is empty!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void executeExceptionTest2() throws CommandRunTimeException {
        CommandContext context = new CommandContext();
        context.addElementToTop(5.0);

        Exception exception = assertThrows(CommandRunTimeException.class, () -> {
            mullCommand.execute(context);
        });

        String expectedMessage = "Stack is empty!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        assertEquals(5, context.getTopElement());
    }
}
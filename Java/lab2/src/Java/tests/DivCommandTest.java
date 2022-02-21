package Java.tests;


import Java.commands.DivCommand;
import Java.contexts.CommandContext;
import Java.exceptions.BadArgsException;
import Java.exceptions.CommandRunTimeException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


class DivCommandTest {
    private final DivCommand divCommand = new DivCommand(new ArrayList<String>(Arrays.asList("/".split(" "))));


    DivCommandTest() throws BadArgsException {
    }

    @Test
    void executeSimpleTest() throws CommandRunTimeException {
        CommandContext context = new CommandContext();

        context.addElementToTop(5.0);
        context.addElementToTop(6.0);

        divCommand.execute(context);

        assertEquals(6.0/5.0, context.getTopElement());
    }

    @Test
    void executeExceptionTest1() throws CommandRunTimeException {
        CommandContext context = new CommandContext();

        Exception exception = assertThrows(CommandRunTimeException.class, () -> {
            divCommand.execute(context);
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
            divCommand.execute(context);
        });

        String expectedMessage = "Stack is empty!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        assertEquals(5, context.getTopElement());
    }

    @Test
    void executeExceptionTest3() throws CommandRunTimeException {
        CommandContext context = new CommandContext();
        context.addElementToTop(0.0);
        context.addElementToTop(5.0);

        Exception exception = assertThrows(CommandRunTimeException.class, () -> {
            divCommand.execute(context);
        });

        String expectedMessage = "zero division!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        assertEquals(5, context.getTopElement());
        assertEquals(0, context.getTopElement());
    }
}
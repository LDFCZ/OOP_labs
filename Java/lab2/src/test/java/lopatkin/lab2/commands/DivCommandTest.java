package lopatkin.lab2.commands;


import lopatkin.lab2.contexts.CommandContext;
import lopatkin.lab2.exceptions.BadArgsException;
import lopatkin.lab2.exceptions.CommandRunTimeException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


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
    void executeExceptionTestFullEmptyStack() throws CommandRunTimeException {
        CommandContext context = new CommandContext();

        Exception exception = assertThrows(CommandRunTimeException.class, () -> {
            divCommand.execute(context);
        });

        String expectedMessage = "Stack is empty!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void executeExceptionTestNotFullEmptyStack() throws CommandRunTimeException {
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
    void executeExceptionTestZeroDivision() throws CommandRunTimeException {
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

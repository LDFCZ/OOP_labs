package lopatkin.lab2.commands;

import lopatkin.lab2.contexts.CommandContext;
import lopatkin.lab2.exceptions.BadArgsException;
import lopatkin.lab2.exceptions.CommandRunTimeException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;


class SqrtCommandTest {
    private final SqrtCommand sqrtCommand = new SqrtCommand(new ArrayList<String>(Arrays.asList("SQRT".split(" "))));


    SqrtCommandTest() throws BadArgsException {
    }

    @Test
    void executeSimpleTest() throws CommandRunTimeException {
        CommandContext context = new CommandContext();

        context.addElementToTop(9.0);

        sqrtCommand.execute(context);

        assertEquals(sqrt(9.0), context.getTopElement());
    }

    @Test
    void executeExceptionTest1() throws CommandRunTimeException {
        CommandContext context = new CommandContext();

        Exception exception = assertThrows(CommandRunTimeException.class, () -> {
            sqrtCommand.execute(context);
        });

        String expectedMessage = "Stack is empty!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void executeExceptionTest2() throws CommandRunTimeException {
        CommandContext context = new CommandContext();
        context.addElementToTop(-3.0);
        Exception exception = assertThrows(CommandRunTimeException.class, () -> {
            sqrtCommand.execute(context);
        });

        String expectedMessage = "negative number in SQRT";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        assertEquals(-3.0, context.getTopElement());
    }
}
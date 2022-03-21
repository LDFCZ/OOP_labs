package lopatkin.lab2.commands;


import lopatkin.lab2.contexts.CommandContext;
import lopatkin.lab2.exceptions.BadArgsException;
import lopatkin.lab2.exceptions.CommandRunTimeException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


class PopCommandTest {
    private final PopCommand popCommand = new PopCommand(new ArrayList<String>(Arrays.asList("POP".split(" "))));

    PopCommandTest() throws BadArgsException {
    }

    @Test
    void executeSimpleTest() throws CommandRunTimeException {
        CommandContext context = new CommandContext();

        context.addElementToTop(9.0);
        context.addElementToTop(8.0);

        popCommand.execute(context);

        assertEquals(9.0, context.viewTopElement());
    }

    @Test
    void executeExceptionTestEmptyStack() throws CommandRunTimeException {
        CommandContext context = new CommandContext();

        Exception exception = assertThrows(CommandRunTimeException.class, () -> {
            popCommand.execute(context);
        });

        String expectedMessage = "Stack is empty!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}

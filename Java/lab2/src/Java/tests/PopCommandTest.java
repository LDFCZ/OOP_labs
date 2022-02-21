package Java.tests;


import Java.commands.PopCommand;
import Java.contexts.CommandContext;
import Java.exceptions.BadArgsException;
import Java.exceptions.CommandRunTimeException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
    void executeExceptionTest1() throws CommandRunTimeException {
        CommandContext context = new CommandContext();

        Exception exception = assertThrows(CommandRunTimeException.class, () -> {
            popCommand.execute(context);
        });

        String expectedMessage = "Stack is empty!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
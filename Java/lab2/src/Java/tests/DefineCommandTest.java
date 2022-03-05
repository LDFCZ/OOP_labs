package Java.tests;


import Java.commands.DefineCommand;
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


class DefineCommandTest {
    @Test
    void executeSimpleTest1() throws CommandRunTimeException, BadArgsException, BadVariableException {
        DefineCommand defineCommand = new DefineCommand(new ArrayList<String>(Arrays.asList("DEFINE A 9".split(" "))));
        CommandContext context = new CommandContext();

        defineCommand.execute(context);

        assertEquals(9.0, context.getVariableValue("A"));
    }

    @Test
    void executeSimpleTest2() throws CommandRunTimeException, BadArgsException, BadVariableException {
        DefineCommand defineCommand = new DefineCommand(new ArrayList<String>(Arrays.asList("DEFINE Aa_9 9".split(" "))));
        CommandContext context = new CommandContext();

        defineCommand.execute(context);

        assertEquals(9, context.getVariableValue("Aa_9"));
    }

    @Test
    void executeSimpleTest3() throws CommandRunTimeException, BadArgsException, BadVariableException {
        DefineCommand defineCommand = new DefineCommand(new ArrayList<String>(Arrays.asList("DEFINE A 9.0".split(" "))));
        CommandContext context = new CommandContext();

        defineCommand.execute(context);

        assertEquals(9.0, context.getVariableValue("A"));
    }

    @Test
    void executeSimpleTest4() throws CommandRunTimeException, BadArgsException, BadVariableException {
        DefineCommand defineCommand = new DefineCommand(new ArrayList<String>(Arrays.asList("DEFINE A B".split(" "))));
        CommandContext context = new CommandContext();
        context.addVariable("B", 9.0);
        defineCommand.execute(context);

        assertEquals(9.0, context.getVariableValue("A"));
    }

    @Test
    void executeExceptionTest1() throws CommandRunTimeException, BadArgsException {
        DefineCommand defineCommand = new DefineCommand(new ArrayList<String>(Arrays.asList("DEFINE #A 9".split(" "))));
        CommandContext context = new CommandContext();

        Exception exception = assertThrows(CommandRunTimeException.class, () -> {
            defineCommand.execute(context);
        });

        String expectedMessage = "#A - Bad variable name";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void executeExceptionTest2() throws CommandRunTimeException, BadArgsException {
        DefineCommand defineCommand = new DefineCommand(new ArrayList<String>(Arrays.asList("DEFINE A A".split(" "))));
        CommandContext context = new CommandContext();

        Exception exception = assertThrows(CommandRunTimeException.class, () -> {
            defineCommand.execute(context);
        });

        String expectedMessage = "A - Not a number or variable";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


}
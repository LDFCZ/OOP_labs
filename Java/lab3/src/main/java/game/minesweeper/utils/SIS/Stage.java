package game.minesweeper.utils.SIS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Stage {

    public static final String ENTER_COMMAND = "\nEnter command!\n";
    public static final String NO_SUCH_COMMAND = "no such command!\n";
    public static final String SPACE = " ";

    private CommandPane pane;

    private boolean isWorking = true;

    public void launch() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (isWorking) {
            pane.printWidgets();
            System.out.print(ENTER_COMMAND);
            List<String> command = Arrays.stream(reader.readLine().split(SPACE)).toList();
            if(!pane.runCommand(command))
                System.out.print(NO_SUCH_COMMAND);

        }
    }

    public void setCommandPane(CommandPane pane) {
        this.pane = pane;
    }

    public void close() {
        isWorking = false;
    }

}

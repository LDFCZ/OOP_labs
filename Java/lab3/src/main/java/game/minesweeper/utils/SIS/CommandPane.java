package game.minesweeper.utils.SIS;

import java.util.ArrayList;
import java.util.List;


public class CommandPane {
    private final ArrayList<Printable> widgets = new ArrayList<>();
    private final ArrayList<Command> commands = new ArrayList<>();

    public void addWidgets(Printable widget) {
        widgets.add(widget);
    }

    public void addCommand(Command command) {
        commands.add(command);
        widgets.add(command);
    }

    public void addAllCommand(Command... command) {
        for (Command c : command) {
            commands.add(c);
            widgets.add(c);
        }
    }

    public Boolean runCommand(List<String> args) {
        for (Command command : commands) {
            if(command.action(args)) return true;
        }
        return false;
    }

    public void printWidgets() {
        for (Printable widget : widgets) {
            widget.print();
            System.out.print(System.lineSeparator());
        }
    }

    public void clear() {
        widgets.clear();
        commands.clear();
    }
}

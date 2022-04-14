package game.minesweeper.utils.SIS;

import java.util.List;

public class  Command implements Printable{

    private EventHandler eventHandler;

    private final String commandName;
    private final int argsCount;



    public Command(String commandName, int argsCount) {
        this.commandName = commandName;
        this.argsCount = argsCount + 1;
    }

    public Command(String commandName) {
        this(commandName, 0);
    }

    public Boolean action(List<String> args) {


        if (!(args.get(0).equals(commandName) && args.size() == argsCount)) return false;
        eventHandler.handle(args);
        return true;
    }

    public void setOnAction(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void print() {
        System.out.print(commandName);
    }
}

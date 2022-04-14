package game.minesweeper.utils.SIS;

public class SimpleWidget implements Printable{
    private String widget;

    public SimpleWidget(String str) {
        this.widget = str;
    }

    public void setText(String str) {
        this.widget = str;
    }

    @Override
    public void print() {
        System.out.print(widget);
    }
}

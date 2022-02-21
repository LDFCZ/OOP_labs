package Java.lab1;

import java.io.IOException;

public class Parser {
    private FileReader reader;
    private Context context;

    static private final int badValue = -1;

    public Parser(FileReader reader, Context context) {
        this.reader = reader;
        this.context = context;
    }

    public void readFile() {
        StringBuilder word = new StringBuilder();
        while (true) {
            int value = badValue;
            try {
                value = reader.getValue();
            } catch (IOException ex) {
                System.out.print(ex.getMessage() + System.lineSeparator());
                ex.printStackTrace();
                return;
            }
            if (value != badValue) {
                if (Character.isLetterOrDigit((char)value)) {
                    word.append((char)value);
                }
                else if (word.length() != 0) {
                   context.addNewWord(word.toString());
                   word.delete(0, word.length());
                }
            }
            else {
              return;
            }
        }
    }
}
package Java;


import Java.lab1.CSVWriter;
import Java.lab1.StatWriter;
import Java.lab1.Context;
import Java.lab1.FileReader;
import Java.lab1.Parser;

import java.io.IOException;

public class Main {
    static private final int argCount = 2;

    static private final String BadArgsException = "invalid number of arguments";

    static private final int InputFileName = 0;
    static private final int OutputFileName = 1;

    public static void main(String[] args) {
        if (args.length != argCount) {
            System.out.print(BadArgsException);
            return;
        }
        Context context = new Context();
        FileReader reader = null;
        CSVWriter writer = null;
        try {
            reader = new FileReader(args[InputFileName]);
            writer = new CSVWriter(args[OutputFileName]);
        } catch (IOException ex) {
            System.out.print(ex.getMessage() + System.lineSeparator());
            ex.printStackTrace();
            return;
        }
        Parser parser = new Parser(reader, context);
        parser.readFile();

        StatWriter statWriter = new StatWriter(writer, context);
        statWriter.writeStat();

        try {
            reader.close();
            writer.close();
        } catch (IOException ex) {
            System.out.print(ex.getMessage() + System.lineSeparator());
            ex.printStackTrace();
        }
    }
}

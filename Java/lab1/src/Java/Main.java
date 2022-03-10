package Java;


import Java.lab1.CSVWriter;
import Java.lab1.StatWriter;
import Java.lab1.Context;
import Java.lab1.FileReader;
import Java.lab1.Parser;

import java.io.IOException;

public class Main {
    static private final int ARG_COUNT = 2;

    static private final String BAD_ARGS_EXCEPTION = "invalid number of arguments";

    static private final int INPUT_FILE_NAME = 0;
    static private final int OUTPUT_FILE_NAME = 1;

    public static void main(String[] args) {
        if (args.length != ARG_COUNT) {
            System.out.print(BAD_ARGS_EXCEPTION);
            return;
        }
        Context context = new Context();
        FileReader reader = null;
        CSVWriter writer = null;
        try {
            reader = new FileReader(args[INPUT_FILE_NAME]);
            writer = new CSVWriter(args[OUTPUT_FILE_NAME]);

        Parser parser = new Parser(reader, context);
        parser.readFile();

        StatWriter statWriter = new StatWriter(writer, context);
        statWriter.writeStat();
        
        } catch (IOException ex) {
            System.out.print(ex.getMessage() + System.lineSeparator());
            ex.printStackTrace();
        }
    }
}

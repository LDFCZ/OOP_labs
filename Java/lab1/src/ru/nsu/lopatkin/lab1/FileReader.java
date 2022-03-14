package ru.nsu.lopatkin.lab1;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileReader implements Closeable{
    private final BufferedReader buffer;
    private final FileInputStream fileStream;

    public FileReader(String fileName) throws IOException {
        fileStream = new FileInputStream(fileName);
        buffer = new BufferedReader(new InputStreamReader(fileStream, StandardCharsets.UTF_8));
    }

    public int getValue() throws IOException{
        return buffer.read();
    }

    @Override
    public void close() throws IOException {
        buffer.close();
        fileStream.close();
    }
}

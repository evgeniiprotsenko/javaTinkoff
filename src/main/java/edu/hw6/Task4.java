package edu.hw6;


import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class Task4 {

    public void writeToOutputFile(Path filePath, String content) {
        try {
            OutputStream fileOutputStream = Files.newOutputStream(filePath, StandardOpenOption.CREATE);

            CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new Adler32());

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);

            OutputStreamWriter outputStreamWriter =
                new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);

            PrintWriter printWriter = new PrintWriter(outputStreamWriter);

            printWriter.println(content);

            printWriter.close();

        } catch (IOException ignore) {
        }
    }
}



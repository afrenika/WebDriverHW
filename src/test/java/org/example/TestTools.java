package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class TestTools {

    public static void writeFile(String text){
        writeFile(text, "task2.txt");
    }

    public static void writeFile(String text, String fileName){
        try(FileWriter writer = new FileWriter(fileName, false))
        {
            writer.write(text);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}

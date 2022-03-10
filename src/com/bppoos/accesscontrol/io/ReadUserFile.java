package com.bppoos.accesscontrol.io;

import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadUserFile {

    /**
     * Reads a set of records from a file and adds them to a list.
     *
     * @param filePath Path to file to be read.
     * @return List of String, one entry per record in the file.
     */
    public List<String> produceListFromFile(String filePath)
    {
        List<String> resultList = new ArrayList<>();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line != null) {
                resultList.add(line);
                System.out.println(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

        return resultList;
    }
}

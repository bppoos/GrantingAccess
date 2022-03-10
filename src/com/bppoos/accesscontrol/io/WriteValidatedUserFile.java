package com.bppoos.accesscontrol.io;

import java.util.List;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriteValidatedUserFile {

    /**
     * Takes a list of validated user records and writes them to a file.
     *
     * @param users List of user records to be written.
     * @param filePath path to the file to be written.
     */
    public void writeUsersToFile(List<String> users, String filePath) {
        File fileOut = new File(filePath);
        try {
            FileOutputStream outputStream = new FileOutputStream(fileOut);

            OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);

            boolean notFirst = false;
            for (String entry : users) {
                //Append a return character if not the first line
                if(notFirst)
                    streamWriter.write("\n");
                streamWriter.write(entry);
                notFirst = true;
            }
            streamWriter.close();
        }
        catch(IOException ioe)
        {
            System.out.println(ioe.getMessage());
        }
    }
}

package com.bppoos.accesscontrol;

import java.util.ArrayList;
import java.util.List;

import com.bppoos.accesscontrol.io.ReadUserFile;
import com.bppoos.accesscontrol.io.WriteValidatedUserFile;
import com.bppoos.accesscontrol.services.AccessControlService;


public class Main {

    /**
     * Take in two String args, first the file to be read from second the file to be written to.
     *
     * Read the file to get user records.
     *
     * Validate the user records.
     *
     * Write validated user records to the output file.
     *
     * @param args input file path and output file path.
     */
    public static void main(String[] args) {

        // If we don't have the right number of arguments, stop processing.
        if(2 != args.length)
        {
            System.out.println("Wrong number of arguments");
            return;
        }

        ReadUserFile readFile = new ReadUserFile();

        List<String> users = readFile.produceListFromFile(args[0]);

        if(users.isEmpty())
        {
            System.out.println("No user records were found.");
            return;
        }

        AccessControlService accessControlService = new AccessControlService();

        List<String> validatedUsers = new ArrayList<>();
        for(String user : users)
        {
            StringBuilder validatedUser = new StringBuilder(user);
            validatedUser.append(",");
            validatedUser.append( accessControlService.analyzeEntry(user, "example.com", "192.168"));
            validatedUsers.add(validatedUser.toString());
        }

        WriteValidatedUserFile writeFile = new WriteValidatedUserFile();
        writeFile.writeUsersToFile(validatedUsers, args[1]);
    }
}

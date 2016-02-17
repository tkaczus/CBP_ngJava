package com.orasi.utils;

import java.io.*;
import java.lang.String;
/**
 * Created by Lukasz.Tkaczyk on 2016-02-17.
 */
public class ReplaceLogForScreenings {

    public static void replaceOutput() {
        try {
            File log = new File(System.getProperty("user.dir")+"\\test-output\\html\\output.html");
            BufferedReader reader = new BufferedReader(new FileReader(log));
            String line = "", oldtext = "";
            while((line = reader.readLine()) != null)
            {
                oldtext += line + "";
            }
            reader.close();
            if(log.delete()){
                System.out.println(log.getName() + " is deleted!");
            }else{
                System.out.println("Delete operation is failed.");
            }
            // replace a word in a file
            //String newtext = oldtext.replaceAll("drink", "Love");

            //To replace a line in a file
            System.out.println(oldtext);
            String newtext = oldtext.replaceAll("]", ">");
            newtext = newtext.replaceAll("\\[", "<");
            newtext = newtext.replaceAll("\\*", "\"");
            System.out.println(newtext);

            //c:\Users\lukasz.tkaczyk\workspace\CBP_ngJava\test-output\html\output.html
            FileWriter writer = new FileWriter(System.getProperty("user.dir")+"\\test-output\\html\\output.html");
            writer.write(newtext);
            writer.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
}
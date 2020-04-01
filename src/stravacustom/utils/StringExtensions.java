package stravacustom.utils;


import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;

public  class StringExtensions {
    public static String parseInputStream(InputStream inputStream)  {
        StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(inputStream, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        var theString = writer.toString();

        return theString;

    }

    public static ArrayList<String> parseStringToList(String input){

        ArrayList<String> list =   new ArrayList<String>( Arrays.asList(input.split("\\r?\\n")));
        return list;

    }
}

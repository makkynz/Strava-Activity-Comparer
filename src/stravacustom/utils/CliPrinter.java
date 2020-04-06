package stravacustom.utils;

public class CliPrinter {
    public static void printLn(String msg){
        if(msg.length() > 150)msg = msg.substring(0, 149) + "...";
        System.out.println(msg  + "\n");
    }
}

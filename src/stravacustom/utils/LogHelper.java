package stravacustom.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogHelper {

    static Logger logger = null;
    public static boolean logEnabled = true;

    public static void log(String msg){

            if (logEnabled) {
                if(logger==null){
                    logger = Logger.getLogger("logs");
                    FileHandler fh = null;
                    try {
                        fh = new FileHandler(ConfigHelper.get("logFile"));
                    } catch (IOException e) {
                       throw new RuntimeException(e);
                    }
                    logger.addHandler(fh);
                    System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %5$s%6$s%n");
                    SimpleFormatter formatter = new SimpleFormatter();
                    fh.setFormatter(formatter);
                }
                if(msg.length() > 500)msg = msg.substring(0,500);
                logger.info(msg);
            }

    }
}

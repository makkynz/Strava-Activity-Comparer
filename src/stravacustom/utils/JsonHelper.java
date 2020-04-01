package stravacustom.utils;

import com.owlike.genson.Genson;

public class JsonHelper {

    public static String serialise(Object obj){
        Genson genson = new Genson();
        String json = genson.serialize(obj);
        return json;
    }
}

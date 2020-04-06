package stravacustom.utils;

import com.owlike.genson.Genson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonHelper {

    public static String serialise(Object obj){
        Genson genson = new Genson();
        String json = genson.serialize(obj);
        return json;
    }

    public static JSONObject parseStringToObject(String stringVal) {
        //parse response to JSON obj
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(stringVal);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonObject;
    }

    public static JSONArray parseStringToArray(String stringVal) {
        //parse response to JSON array
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(stringVal);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonArray;
    }
}

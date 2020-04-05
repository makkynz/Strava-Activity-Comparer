package stravacustom.domain.services;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import stravacustom.utils.ConfigHelper;
import stravacustom.utils.HttpHelper;

import java.util.ArrayList;
import java.util.List;

public class Strava {

    public JSONObject processAuthorizationCode(String authorizationCode) {

        return getAccessTokenResponse(authorizationCode);
    }

    public JSONArray getActivites(String accessToken, String refreshToken) {
        //send http get
        List<NameValuePair> headers = new ArrayList<>(2);
        headers.add(new BasicNameValuePair("Authorization", "Bearer " +accessToken));
        String rawResponse = HttpHelper.Get("https://www.strava.com/api/v3/athlete/activities", headers);
        if(rawResponse.contains("Authorization Error")){
            JSONObject stravaJson  = getNewAccessToken(refreshToken);
            accessToken = stravaJson.getString("access_token");
            refreshToken = stravaJson.getString("refresh_token");
            headers = new ArrayList<>(2);
            headers.add(new BasicNameValuePair("Authorization", "Bearer " +accessToken));
            rawResponse = HttpHelper.Get("https://www.strava.com/api/v3/athlete/activities", headers);
        }

        return parseResponseToJsonArray(rawResponse);
    }

    public JSONObject getNewAccessToken(String refreshToken) {
        //send http get
        List<NameValuePair> params = new ArrayList<>(2);
        params.add(new BasicNameValuePair("client_id", ConfigHelper.get("strava.clientId")));
        params.add(new BasicNameValuePair("client_secret", ConfigHelper.get("strava.clientSecret")));
        params.add(new BasicNameValuePair("refresh_token", refreshToken));
        params.add(new BasicNameValuePair("grant_type", "refresh_token"));
        String rawResponse = HttpHelper.Post("https://www.strava.com/oauth/token",params);

        return parseResponseToJsonObject(rawResponse);
    }

    private JSONObject parseResponseToJsonObject(String rawResponse) {
        //parse response to JSON obj
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(rawResponse);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonObject;
    }

    private JSONArray parseResponseToJsonArray(String rawResponse) {
        //parse response to JSON obj
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(rawResponse);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonArray;
    }

    private JSONObject getAccessTokenResponse(String authorizationCode) {
        //send http post
        List<NameValuePair> params = new ArrayList<>(2);
        params.add(new BasicNameValuePair("client_id", ConfigHelper.get("strava.clientId")));
        params.add(new BasicNameValuePair("client_secret", ConfigHelper.get("strava.clientSecret")));
        params.add(new BasicNameValuePair("code", authorizationCode));
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));
        String rawResponse = HttpHelper.Post("https://www.strava.com/oauth/token",params);

        //parse response to JSON obj
        return parseResponseToJsonObject(rawResponse);
    }
}

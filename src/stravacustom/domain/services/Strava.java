package stravacustom.domain.services;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import stravacustom.utils.ConfigHelper;
import stravacustom.utils.HttpHelper;
import stravacustom.utils.JsonHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Strava {

    public JSONObject processAuthorizationCode(String authorizationCode) {

        return getAccessTokenResponse(authorizationCode);
    }

    public JSONArray getActivities(String accessToken, String refreshToken) {
        Long lockDownStartEpoch = null;
        try {
            lockDownStartEpoch = new SimpleDateFormat("dd/MM/yyyy").parse("25/03/2020").getTime() / 1000;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<NameValuePair> headers = new ArrayList<>(2);
        headers.add(new BasicNameValuePair("Authorization", "Bearer " +accessToken));
        String rawResponse = HttpHelper.Get("https://www.strava.com/api/v3/athlete/activities?after="+lockDownStartEpoch, headers);

        return JsonHelper.parseStringToArray(rawResponse);
    }

    public JSONObject getNewAccessToken(String refreshToken) {
        //send http get
        List<NameValuePair> params = new ArrayList<>(2);
        params.add(new BasicNameValuePair("client_id", ConfigHelper.get("strava.clientId")));
        params.add(new BasicNameValuePair("client_secret", ConfigHelper.get("strava.clientSecret")));
        params.add(new BasicNameValuePair("refresh_token", refreshToken));
        params.add(new BasicNameValuePair("grant_type", "refresh_token"));
        String rawResponse = HttpHelper.Post("https://www.strava.com/oauth/token",params);

        return JsonHelper.parseStringToObject(rawResponse);
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
        return JsonHelper.parseStringToObject(rawResponse);
    }


}

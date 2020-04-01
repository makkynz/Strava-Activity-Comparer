package stravacustom.domain.services;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import stravacustom.utils.ConfigHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class StravaApi {

    public void processAuthorizationCode(String authorizationCode)  {


        JSONObject jsonObject = getAccessTokenResponse(authorizationCode);

        String accessToken = jsonObject.getString("access_token");
        String refreshToken = jsonObject.getString("refresh_token");
        JSONObject athleteObj = jsonObject.getJSONObject("athlete");
        String firstName = athleteObj.getString("firstname");
        String lastName = athleteObj.getString("lastname");
        String stravaId = athleteObj.getBigInteger("id").toString();
        String city = athleteObj.getString("city");
        String profilePicUrl = athleteObj.getString("profile");



        int i = 0;
    }

    private JSONObject getAccessTokenResponse(String authorizationCode) {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://www.strava.com/oauth/token");
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("client_id", ConfigHelper.get("strava.clientId")));
        params.add(new BasicNameValuePair("client_secret", ConfigHelper.get("strava.clientSecret")));
        params.add(new BasicNameValuePair("code", authorizationCode));
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        HttpResponse response = null;
        String rawResponse = null;
        try {
            response = httpclient.execute(httppost);

        HttpEntity entity = response.getEntity();


        if (entity != null) {
            try (InputStream instream = entity.getContent()) {
                rawResponse  = IOUtils.toString(instream);
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(rawResponse);
        }catch (JSONException e){
            throw new RuntimeException(e);
        }
        return jsonObject;
    }
}

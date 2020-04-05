package stravacustom.domain.services;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import stravacustom.data.AthleteRepository;
import stravacustom.domain.entities.Athlete;
import stravacustom.utils.ConfigHelper;
import stravacustom.utils.HttpHelper;

import java.util.ArrayList;
import java.util.List;

public class StravaApi {

    public void processAuthorizationCode(String authorizationCode) {

        JSONObject jsonObject = getAccessTokenResponse(authorizationCode);

        Athlete athlete =  Athlete.getNewOrExisting(jsonObject);


        refreshActivities(athlete);

        AthleteRepository.save(athlete);


    }

    public void refreshActivities(Athlete athlete) {
        //send http get
        List<NameValuePair> headers = new ArrayList<>(2);
        headers.add(new BasicNameValuePair("Authorization", "Bearer " +athlete.getAccessToken()));

        String rawResponse = HttpHelper.Get("https://www.strava.com/api/v3/athlete/activities", headers);

        athlete.setActivitiesJson(rawResponse);


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
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(rawResponse);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonObject;
    }
}

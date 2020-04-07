package stravacustom.domain.entities;

import org.json.JSONArray;
import org.json.JSONObject;
import stravacustom.data.AthleteRepository;
import stravacustom.domain.services.Strava;
import stravacustom.utils.JsonHelper;

import java.util.Date;
import java.util.UUID;


public class Athlete {
    private UUID id;
    private String accessToken ;
    private String refreshToken;
    private String rawJson ;
    private String firstName;
    private String lastName ;
    private String stravaId ;
    private String city ;
    private String profilePic;
    private Date tokenExpiry;
    private Date dateCreated;
    private Date dateUpdated;
    private String activitiesJson;

    public Athlete(){

    }
    public Athlete(JSONObject stravaJson) {
        this.id = java.util.UUID.randomUUID();
        this.accessToken = stravaJson.getString("access_token");
        this.refreshToken = stravaJson.getString("refresh_token");
        this.tokenExpiry = new Date(Long.parseLong(stravaJson.getBigInteger("expires_at").toString())*1000);
        this.rawJson = stravaJson.toString();
        this.firstName = stravaJson.getJSONObject("athlete").getString("firstname");
        this.lastName = stravaJson.getJSONObject("athlete").getString("lastname");
        this.stravaId = stravaJson.getJSONObject("athlete").getBigInteger("id").toString();
        this.city = stravaJson.getJSONObject("athlete").getString("city");
        this.profilePic = stravaJson.getJSONObject("athlete").getString("profile");

        this.dateCreated = new Date();
        this.dateUpdated = this.dateCreated;

    }

    public String getFullName(){
        return String.join(" ", this.firstName, this.lastName);
    }

    public static Athlete getNewOrExisting(JSONObject stravaJson){
        String stravaId = stravaJson.getJSONObject("athlete").getBigInteger("id").toString();
        Athlete existingAthlete = AthleteRepository.getByStravaAthleteId(stravaId);
        if(existingAthlete !=null) return existingAthlete;

        return new Athlete(stravaJson);
    }

    public  void updateActivities() {
        Strava api = new Strava();
        if(this.tokenExpiry.before(new Date())) {
            JSONObject refreshObj = api.getNewAccessToken(this.refreshToken);
            this.accessToken = refreshObj.getString("access_token");
            this.refreshToken = refreshObj.getString("refresh_token");
            this.tokenExpiry = new Date(Long.parseLong(refreshObj.getBigInteger("expires_at").toString()) * 1000);

        }

        JSONArray stravaJson = api.getActivities(this.accessToken, this.refreshToken);
        this.activitiesJson = stravaJson.toString();
        this.dateUpdated = new Date();
        AthleteRepository.save(this);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRawJson() {
        return rawJson;
    }

    public void setRawJson(String rawJson) {
        this.rawJson = rawJson;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStravaId() {
        return stravaId;
    }

    public void setStravaId(String stravaId) {
        this.stravaId = stravaId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public Date getTokenExpiry() {
        return tokenExpiry;
    }

    public void setTokenExpiry(Date tokenExpiry) {
        this.tokenExpiry = tokenExpiry;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getActivitiesJson() {
        return activitiesJson;
    }

    public JSONArray getActivitiesAsJsonArray() {
        return  JsonHelper.parseStringToArray( activitiesJson);
    }

    public JSONObject getSummaryJson() {

        JSONObject jsonAthlete = new JSONObject();
        jsonAthlete.put("stravaId", this.stravaId);
        jsonAthlete.put("name", getFullName());
        jsonAthlete.put("profilePic", this.profilePic);
        JSONArray activities = new JSONArray();
        JSONArray activitiesCache =  getActivitiesAsJsonArray();
        for(int n = 0; n < activitiesCache.length(); n++) {
            JSONObject activity = activitiesCache.getJSONObject(n);
            JSONObject jsonActivity = new JSONObject();
            jsonActivity.put("type", activity.getString("type"));
            jsonActivity.put("startDate", activity.getString("start_date"));
            jsonActivity.put("distance", activity.getDouble("distance"));
            jsonActivity.put("averageSpeed", activity.getDouble("average_speed"));
            jsonActivity.put("movingTime", activity.getDouble("moving_time"));
            jsonActivity.put("activityId", activity.getBigInteger("id"));
            jsonActivity.put("name", activity.getString("name"));

            activities.put(jsonActivity);
        }
        jsonAthlete.put("activities",activities);
        return  jsonAthlete;
    }

    public void setActivitiesJson(String activitiesJson) {
        this.activitiesJson = activitiesJson;
    }
}

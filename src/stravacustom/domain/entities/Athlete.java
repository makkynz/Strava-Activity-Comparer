package stravacustom.domain.entities;

import org.json.JSONArray;
import org.json.JSONObject;
import stravacustom.data.AthleteRepository;
import stravacustom.domain.services.Strava;

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
        this.rawJson = stravaJson.toString();
        this.firstName = stravaJson.getJSONObject("athlete").getString("firstname");
        this.lastName = stravaJson.getJSONObject("athlete").getString("lastname");
        this.stravaId = stravaJson.getJSONObject("athlete").getBigInteger("id").toString();
        this.city = stravaJson.getJSONObject("athlete").getString("city");
        this.profilePic = stravaJson.getJSONObject("athlete").getString("profile");
        this.tokenExpiry = new Date(Long.parseLong(stravaJson.getBigInteger("expires_at").toString()));
        this.dateCreated = new Date();
        this.dateUpdated = this.dateCreated;

    }

    public static Athlete getNewOrExisting(JSONObject stravaJson){
        String stravaId = stravaJson.getJSONObject("athlete").getBigInteger("id").toString();
        Athlete existingAthlete = AthleteRepository.getByStravaAthleteId(stravaId);
        if(existingAthlete !=null) return existingAthlete;

        return new Athlete(stravaJson);
    }

    public  void updateAccessToken() {
        Strava api = new Strava();
        JSONObject stravaJson = api.getNewAccessToken(this.refreshToken);
        this.accessToken = stravaJson.getString("access_token");
        this.refreshToken = stravaJson.getString("refresh_token");
        this.dateUpdated = new Date();
        AthleteRepository.save(this);
    }

    public  void updateActivities() {
        Strava api = new Strava();
        JSONArray stravaJson = api.getActivites(this.accessToken, this.refreshToken);
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

    public void setActivitiesJson(String activitiesJson) {
        this.activitiesJson = activitiesJson;
    }
}

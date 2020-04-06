package stravacustom.data;

import stravacustom.domain.entities.Athlete;
import stravacustom.domain.entities.Athlete;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AthleteRepository implements RepositoryInterface<Athlete> {

    private Connection _connection;

    public AthleteRepository(DbConnectionInterface dbConnection) {
        _connection = dbConnection.getConnection();
    }

    @Override
    public List<Athlete> getAll() {
        var athletes = new ArrayList<Athlete>();
        try (PreparedStatement pStmt = _connection.prepareStatement("select * from athletes", Statement.NO_GENERATED_KEYS)) {

            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {

                var athlete = getAthleteFromResultSet(rs);

                athletes.add(athlete);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return athletes;
    }

    @Override
    public Athlete getById(UUID id) {
        String sql = "select * from athletes where id = :id" ;
        try (NamedParamStatement stmt = new NamedParamStatement(_connection, sql)) {
            stmt.setUUID("id", id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                var athlete = getAthleteFromResultSet(rs);
                return athlete;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void add(Athlete athlete) {

        String sql = "INSERT INTO athletes (id, strava_id, first_name, " +
                "last_name, city, profile_pic, access_token, refresh_token, date_created, date_updated, raw_strava_json, " +
                "token_expiry, activities_json) VALUES (" +
                ":id, :strava_id, :first_name, :last_name, :city, :profile_pic, :access_token, :refresh_token, :date_created, " +
                ":date_updated, :raw_strava_json, :token_expiry, :activities_json)";


        executeNonQuery(athlete, sql);
    }

    @Override
    public void update(Athlete athlete) {
        String sql = "UPDATE public.athletes " +
                " SET  strava_id=:strava_id, first_name=:first_name, last_name=:last_name, city=:city, profile_pic=:profile_pic," +
                " access_token=:access_token, refresh_token=:refresh_token, date_created=:date_created, date_updated=:date_updated," +
                " raw_strava_json=:raw_strava_json, token_expiry=:token_expiry, activities_json=:activities_json " +
                " WHERE id=:id;";


        executeNonQuery(athlete, sql);
    }

    private void executeNonQuery(Athlete athlete, String sql) {
        try (NamedParamStatement stmt = new NamedParamStatement(_connection, sql)) {
            stmt.setUUID("id", athlete.getId());
            stmt.setString("strava_id", athlete.getStravaId());
            stmt.setString("first_name", athlete.getFirstName());
            stmt.setString("last_name", athlete.getLastName());
            stmt.setString("city", athlete.getCity());
            stmt.setString("profile_pic", athlete.getProfilePic());
            stmt.setString("access_token", athlete.getAccessToken());
            stmt.setString("refresh_token", athlete.getRefreshToken());
            stmt.setDate("date_created", athlete.getDateCreated());
            stmt.setDate("date_updated", athlete.getDateUpdated());
            stmt.setString("raw_strava_json", athlete.getRawJson());
            stmt.setDate("token_expiry", athlete.getTokenExpiry());
            stmt.setString("activities_json", athlete.getActivitiesJson());
            stmt.executeNonQuery();
        }
    }

    @Override
    public void removeById(UUID id) {

    }

    @Override
    public void remove(Athlete obj) {

    }

    private Athlete getAthleteFromResultSet(ResultSet rs) throws SQLException {
        var athlete = new Athlete();
        athlete.setId(rs.getObject("id", UUID.class));
        athlete.setStravaId(rs.getString("strava_id"));
        athlete.setFirstName(rs.getString("first_name"));
        athlete.setLastName(rs.getString("last_name"));
        athlete.setCity(rs.getString("city"));
        athlete.setProfilePic(rs.getString("profile_pic"));
        athlete.setAccessToken(rs.getString("access_token"));
        athlete.setRefreshToken(rs.getString("refresh_token"));
        athlete.setDateCreated(rs.getTimestamp("date_created"));
        athlete.setDateUpdated(rs.getTimestamp("date_updated"));
        athlete.setRawJson(rs.getString("raw_strava_json"));
        athlete.setTokenExpiry(rs.getTimestamp("token_expiry"));
        athlete.setActivitiesJson(rs.getString("activities_json"));

        return athlete;
    }

    public static void save(Athlete athlete){

        try (var dbConnection = new DbConnection()) {
            var repo = new AthleteRepository(dbConnection);
            var existingAthlete = repo.getById(athlete.getId());
            if(existingAthlete==null){
                repo.add(athlete);
            }else{
                repo.update(athlete);
            }

        }

    }

    public static Athlete getByStravaAthleteId(String stravaAtheleteId) {
        try (var dbConnection = new DbConnection()) {
            var repo = new AthleteRepository(dbConnection);
            String sql = "select * from athletes where strava_id = :id";
            try (NamedParamStatement stmt = new NamedParamStatement(dbConnection.getConnection(), sql)) {
                stmt.setString("id", stravaAtheleteId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {

                    var athlete = repo.getAthleteFromResultSet(rs);
                    return athlete;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public static List<Athlete> getAllAthletes() {
        try (var dbConnection = new DbConnection()) {
            var repo = new AthleteRepository(dbConnection);
            return repo.getAll();
        }
    }


}

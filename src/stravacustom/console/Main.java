package stravacustom.console;

import stravacustom.data.AthleteRepository;
import stravacustom.domain.entities.Athlete;
import stravacustom.domain.services.Strava;

import java.util.List;

public class Main {

    public static void main(java.lang.String[] args) {
        System.err.println("Query athletes from DB \n");
        List<Athlete> athletes = AthleteRepository.getAllAthletes();
        Strava api = new Strava();

        for (Athlete athlete : athletes) {
            System.err.println("Get activites from  Strava Api for "+ athlete.getFirstName()+ " " + athlete.getLastName() + "\n");
            athlete.updateActivities();
            System.err.println("Save activites to DB \n");
            AthleteRepository.save(athlete);
        }

        System.err.println("Finished");

    }
}

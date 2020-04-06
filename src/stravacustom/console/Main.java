package stravacustom.console;

import stravacustom.data.AthleteRepository;
import stravacustom.domain.entities.Athlete;
import stravacustom.domain.services.Strava;
import stravacustom.utils.CliPrinter;

import java.util.List;

public class Main {

    public static void main(java.lang.String[] args) {
        CliPrinter.printLn("Query athletes from DB");
        List<Athlete> athletes = AthleteRepository.getAllAthletes();
        Strava api = new Strava();

        for (Athlete athlete : athletes) {
            CliPrinter.printLn("Get activites from  Strava Api for "+ athlete.getFirstName()+ " " + athlete.getLastName() + "\n");
            athlete.updateActivities();
            CliPrinter.printLn("Save activites to DB \n");
            AthleteRepository.save(athlete);
        }

        CliPrinter.printLn("Finished");

    }
}

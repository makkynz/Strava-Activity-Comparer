package stravacustom.servlet;

import org.json.JSONObject;
import stravacustom.data.AthleteRepository;
import stravacustom.domain.entities.Athlete;
import stravacustom.domain.services.Strava;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class StravaCallback extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if(req.getParameter("scope") == null  || !req.getParameter("scope").equals("read,activity:read")){
            resp.sendRedirect("/?result=noauth");
            return;
        }
        JSONObject jsonObject = new Strava().processAuthorizationCode(req.getParameter("code"));

        Athlete athlete =  Athlete.getNewOrExisting(jsonObject);
        AthleteRepository.save(athlete);
        athlete.updateActivities();


        resp.sendRedirect("/");
    }
}

package stravacustom.servlet;

import stravacustom.domain.services.StravaApi;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class StravaCallback extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        new StravaApi().processAuthorizationCode(req.getParameter("code"));
        resp.sendRedirect("/index.jsp");
    }
}

package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Daniel.Diaconu on 17/07/18.
 */
public class ZeroToHeroServlet extends HttpServlet{

    private String handleRequest (HttpServletRequest req){

        String firstName = req.getParameter("fistName");
        String lastName = req.getParameter("lastName");
        String response = "Hello <b>" + firstName+ " " + lastName + "</b>hello";
        return response;

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       // req.setContentType("text/html");
       // resp.getWriter().write();

        super.doPost(req, resp);
    }
}

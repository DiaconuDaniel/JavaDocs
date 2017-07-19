package ro.teamnet.zth.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Daniel.Diaconu on 17/07/19.
 */
public class HelloWorldServletForward extends HttpServlet {
    /**
     * This method is used to map a GET request from the client side
     * @param request the HttpServletRequest instance
     * @param response the HttpServletResponse instance
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the response type
        response.setContentType("text/html");


        // TODO: Complete the steps from RequestDispatcher Workshop
        request.getAttribute("textAttribute");
        response.getWriter().write("Hello <b>" + request.getParameter("user") + " " + "<b> from the Forward Servlet!" + request.getAttribute("textAttribute"));

    }

}

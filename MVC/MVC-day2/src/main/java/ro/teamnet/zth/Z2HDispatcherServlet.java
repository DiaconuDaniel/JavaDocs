package ro.teamnet.zth;

import org.codehaus.jackson.map.ObjectMapper;
import ro.teamnet.zth.fmk.MethodAttributes;
import ro.teamnet.zth.fmk.domain.HttpMethod;
import ro.teamnet.zth.utils.BeanDeserializator;
import ro.teamnet.zth.utils.ControllerScanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Z2HDispatcherServlet extends HttpServlet {


    private ControllerScanner controllerScanner;

    @Override
    public void init() throws ServletException {

        controllerScanner = new ControllerScanner("ro.teamnet.zth.appl.controller");
        controllerScanner.scan();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req, resp, HttpMethod.GET);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req, resp, HttpMethod.POST);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req, resp, HttpMethod.DELETE);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req, resp, HttpMethod.PUT);
    }

    private void dispatchReply(HttpServletRequest req, HttpServletResponse resp, HttpMethod methodType) {
        try {
            Object resultToDisplay = dispatch(req, methodType);
            reply(resp, resultToDisplay);
        } catch (Exception e) {
            try {
                sendExceptionError(e, resp);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    private void sendExceptionError(Exception e, HttpServletResponse resp) throws IOException {
        resp.getWriter().print(e.getMessage());
    }

    private void reply(HttpServletResponse resp, Object resultToDisplay) throws IOException {
        //todo serialize the output(resultToDisplay = controller.invokeMethod(parameters))
        // todo into JSON using into ObjectMapper (jackson)

        ObjectMapper objectMapper = new ObjectMapper();

        final String responseAsString = objectMapper.writeValueAsString(resultToDisplay);

        resp.getWriter().print(responseAsString);

    }

    private Object dispatch(HttpServletRequest req, HttpMethod methodType) {
        //TODO to invke controller method for the current request and return the controller output



        String url = req.getPathInfo();
        MethodAttributes methodAttributes = controllerScanner.getMetaData(url, methodType);


        try {
            Object obj = methodAttributes.getControllerClass().newInstance();
            Method method = methodAttributes.getMethod();

            BeanDeserializator beanDeserializator = new BeanDeserializator();
            Object list []  = BeanDeserializator.getMethodParams(method,req).toArray();
            return method.invoke(obj,list);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

}

package ro.teamnet.zth.web;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel.Diaconu on 17/07/20.
 */
public class MyDispatcherServlet extends HttpServlet {

    Map<String, MethodAttributes> allowedMethods;

    public void init() {
        Iterable<Class> iterableClasses = null;
        allowedMethods = new HashMap<>();
        try {
            iterableClasses = AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.controller");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(iterableClasses != null) {
            for(Class cl : iterableClasses) {
                MyController myController = (MyController) cl.getDeclaredAnnotation(MyController.class);
                String urlController = myController.urlPath();
                Method methods[] = cl.getDeclaredMethods();
                for(Method method : methods) {
                    MyRequestMethod myRequestMethod = (MyRequestMethod) method.getDeclaredAnnotation(MyRequestMethod.class);
                    String urlMethod =  "/app/mvc" + urlController +"" + myRequestMethod.urlPath();
                    urlMethod += myRequestMethod.methodType();
                    MethodAttributes methodAttributes = new MethodAttributes();
                    methodAttributes.setControllerClass(myController.getClass().getSimpleName());
                    methodAttributes.setMethodName(myRequestMethod.getClass().getSimpleName());
                    methodAttributes.setMethodType(myRequestMethod.getClass().getTypeName());
                    allowedMethods.put(urlMethod, methodAttributes);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            dispatcheReplay(request,response, "POST");
            //response.getWriter().write("mesaj");
    }

    private void dispatcheReplay(HttpServletRequest request, HttpServletResponse response, String post) {
        try {
            Object resultToDisplay = dispatch(request);
            reply(response);
        } catch (Exception e) {
                sendExceptionError();
        }
    }

    private void sendExceptionError() {
    }

    private Object dispatch(HttpServletRequest request) {
        return null;
    }

    private void reply(HttpServletResponse response) {
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatcheReplay(request, response, "GET");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        dispatcheReplay(request,response,"DELETE");
    }

    private void dispatcheReplay(HttpServletRequest request, HttpServletResponse response) {
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        dispatcheReplay(request,response,"PUT");
    }


}

package ro.teamnet.zth.appl.controller;


import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;

/**
 * Created by Daniel.Diaconu on 17/07/20.
 */

@MyController(urlPath = "/employees")
public class EmployeeController {

    @MyRequestMethod(urlPath = "/all",  methodType = "GET")
    public String  getAllEmployees(){
        return "allEmployees";
    }

    @MyRequestMethod(urlPath = "/one",methodType = "GET")
    public String getOneEmployee(){
        return "oneRandomEmployee";
    }


}

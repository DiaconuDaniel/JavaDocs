package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;

/**
 * Created by Daniel.Diaconu on 17/07/20.
 */

@MyController(urlPath = "/department")
public class DepartmentController {


    @MyRequestMethod(urlPath = "/all", methodType = "GET")
    public String getAllDepartments(){
        return "allDepartments";
    }


}

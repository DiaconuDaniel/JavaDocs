package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;

/**
 * Created by Daniel.Diaconu on 17/07/20.
 */
@MyController(urlPath = "/jobs")
public class JobController {

    @MyRequestMethod(urlPath = "/all", methodType = "GET")
    public String getAllJobs(){
        return "allJobs";
    }

    @MyRequestMethod(urlPath = "/ane", methodType = "GET")
    public String getOneJob(){
        return "oneJobs";
    }
}

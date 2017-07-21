package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;

/**
 * Created by Daniel.Diaconu on 17/07/20.
 */

@MyController(urlPath = "/location")
public class LocationController {

    @MyRequestMethod(urlPath = "/all", methodType = "GET")
    public String getAllLocations(){
        return  "allLocation";
    }

    @MyRequestMethod(urlPath = "/one" , methodType = "GET")
    public String getOneLocation(){
        return "oneLocation";
    }
}

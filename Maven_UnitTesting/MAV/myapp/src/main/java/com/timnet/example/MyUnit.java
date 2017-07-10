package com.timnet.example;

/**
 * Created by Daniel.Diaconu on 17/07/10.
 */
public class MyUnit {
    public String concatenate(String one, String two){
        if(one == null){
            return two;
        }else if (two == null){
            return one;
        }
        return one + two;
    }
}

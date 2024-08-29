package com.colak.springtutorial.actuator;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@WebEndpoint(id="custom")
@Component
public class CustomEndpoint {

    List<String> list = new LinkedList<>();

    // http://localhost:8080/actuator/custom
    @ReadOperation
    public int getCount(){
        return list.size();
    }

    // @Selector makes sure that we send that parameter as pathVariable.
    // http://localhost:8080/actuator/custom/Sky
    @ReadOperation
    public String getString(@Selector String name){
        if(list.contains(name)){
            return name;
        } else {
            return "Name Not Found";
        }
    }

    // Post to http://localhost:8080/actuator/custom
    @WriteOperation
    public String addString(String name1, String name2){
        list.add(name1);
        list.add(name2);
        return "Both names added successfully";
    }

    // Delete to http://localhost:8080/actuator/custom?name=Sky
    @DeleteOperation
    public String deleteString(List<String> name){
        list.removeAll(name);
        return "Name Deleted";
    }
}

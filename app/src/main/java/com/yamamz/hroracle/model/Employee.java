package com.yamamz.hroracle.model;

/**
 * Created by Raymundo T. Melecio on 11/30/2016.
 */
//This model is for populating only in Recyclerview
public class Employee {

    private String name,position,empID;


    public Employee(){

    }

    public Employee(String name,String position,String empID){
        this.empID=empID;
        this.name=name;
        this.position=position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

}

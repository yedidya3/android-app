package com.example.myapplication.viewmodel;

import com.example.myapplication.model.Model;

public class viewmodel {
    //The view model has functions that communicate with the model
    com.example.myapplication.model.Model model;
    public viewmodel(){
        this.model = new com.example.myapplication.model.Model();
    }

    public void setAileron(String V){
        model.changeAileron(V);
    }
    public void setElevator(String V){
        model.changeElevator(V);
    }
    public void setRudder(String V){
        model.changeRudder(V);
    }
    public void setThrottle(String V){
        model.changeThrottle(V);
    }
    public void connect(String IP , String port){

        try {
            this.model.startConnection(IP, Integer.parseInt(port));
        }
        catch (NumberFormatException ex){

        }

    }

}

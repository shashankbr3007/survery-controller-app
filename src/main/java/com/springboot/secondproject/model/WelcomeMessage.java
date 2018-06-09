package com.springboot.secondproject.model;

import javafx.beans.binding.ObjectExpression;

import java.util.Date;

public class WelcomeMessage {

    public String Message;
    public String Date;


    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @Override
    public String toString(){
        return this.Message;
    }
}

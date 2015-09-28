package com.endava.model;

import org.jongo.marshall.jackson.oid.MongoObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by avas on 9/28/2015.
 */
@Component
@Scope("prototype")
public class Student {

    @MongoObjectId
    private String _id;
    private String name;
    private String address;
    private String date = new SimpleDateFormat("yyyy:MM:dd_HH:mm:ss").format(Calendar.getInstance().getTime());
    private String fileName;

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "{\n\"name\": \"" + name + "\", \n\"address\": \"" + address + "\", \n\"date\": \"" + date + "\", \n\"fileName\": \"" + fileName + "\"\n}";
    }
}



package com.endava.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jongo.marshall.jackson.oid.MongoObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@Component
@Scope("prototype")
public class StudentNew implements Serializable {

    @MongoObjectId
    private String _id;
    private String name;
    private String CNP;
    private String address;
    private Student email;
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

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }


    public Student getEmail() {
        return email;
    }

    public void setEmail(Student email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "{\n\"name\": \"" + name + "\", \n\"address\": \"" + address + "\", \n\"date\": \"" + date + "\", \n\"fileName\": \"" + fileName + "\"\n}";
    }
}



package com.endava.util;


import com.endava.model.Student;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        /*DB db = new MongoClient().getDB("university");

        Jongo jongo = new Jongo(db);
        MongoCollection coll = jongo.getCollection("students");

        Student doc2 = new Student();
        doc2.setName("test");

        coll.insert(doc2);
        System.out.println("I have inserted: " + doc2.get_id());

        MongoCursor<Student> all = coll.find().as(Student.class);
        while (all.hasNext()) {
             System.out.println(all.next());*/
        validate();

    }

    public static void validate() {
        List<String> emails = new ArrayList<String>();
        emails.add("user@domain.com");
        emails.add("user@domain.co.in");
        emails.add("user1@domain.com");
        emails.add("user.name@domain.com");

        //Invalid emails
        emails.add("user#@domain.co.in");
        emails.add("");
        emails.add("user@domaincom");
        emails.add("user#domain.com");
        emails.add("@yahoo.com");

        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(regex);

        for (String s : emails) {
            Matcher matcher = pattern.matcher(s);
            System.out.println(s + " : " + matcher.matches());
        }
    }
}




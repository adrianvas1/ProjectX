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

public class Utils {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        DB db = new MongoClient().getDB("university");

        Jongo jongo = new Jongo(db);
        MongoCollection coll = jongo.getCollection("students");

        Student doc2 = new Student();
        doc2.setName("test");

        coll.insert(doc2);
        System.out.println("I have inserted: " + doc2.get_id());

        MongoCursor<Student> all = coll.find().as(Student.class);
        while (all.hasNext()) {
             System.out.println(all.next());
        }
    }

}


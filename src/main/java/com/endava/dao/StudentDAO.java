package com.endava.dao;

import com.endava.model.Student;
import com.mongodb.DB;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDAO {

    @Autowired
    private Jongo jongo;

    @Autowired
    private DB db;

    @Autowired
    private MongoCollection mongoCollection;

    @Autowired
    private Student student;

    private OutputStream os;

    // READ - Get All
    public List<Student> readAll() {
        List<Student> collection = new ArrayList<Student>();
        MongoCursor<Student> cursor = mongoCollection.find().as(Student.class);
        while (cursor.hasNext()) {
            collection.add(cursor.next());
        }
        try {
            cursor.close();
        } catch (IOException e) {

        }
        return collection;
    }

    // READ - Get one
    public String read(String id) {
        if (mongoCollection.findOne(new ObjectId(id)).as(Student.class) != null) {
            return mongoCollection.findOne(new ObjectId(id)).as(Student.class).toString();
        } else return "Student with id: " + id + " does not exist in the database!";
    }

    // CREATE - POST
    public String create(String name, String address, String filename) {
        student.setName(name);
        student.setAddress(address);
        student.setFileName(filename);
        mongoCollection.save(student);
        return student.get_id();
    }

    // UPDATE - PATCH
    public boolean update(String id, String name) {
        return (mongoCollection.update(new ObjectId(id)).with("{$set: {'name': '" + name + "'}}") != null);
    }

    // DELETE
    public boolean delete(String id) {
        return (mongoCollection.remove(new ObjectId(id)) != null);
    }

    // UPLOAD FILE
    public boolean writeToFile(String uploadedFileLocation, String upDocument, String fileName)
            throws IOException {
        if (upDocument.matches("(.*)\"name\"(.*)")) {

            JSONObject jsonObj = new JSONObject(upDocument);
            student.setName((String) jsonObj.get("name"));
            student.setFileName(fileName);
            try {
                student.setAddress((String) jsonObj.get("address"));
            } catch (Exception e) {

            }

            JSONObject outputStream = new JSONObject(student);
            FileWriter file = new FileWriter(uploadedFileLocation);
            file.write(String.valueOf(outputStream));
            file.flush();
            file.close();
            return true;
        } else {
            return false;
        }
    }

}

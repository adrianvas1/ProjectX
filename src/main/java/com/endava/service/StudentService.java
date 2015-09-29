package com.endava.service;

import com.endava.dao.StudentDAO;
import com.endava.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created by avas on 9/28/2015.
 */
@Component
public class StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    public StudentService(StudentDAO documentDAO) {
        this.studentDAO = documentDAO;
    }

    // READ - Get All
    public List<Student> readAll() {
        return studentDAO.readAll();
    }

    // READ - Get one
    public String read(String id) {
        return studentDAO.read(id);
    }

    // CREATE - POST
    public String create(String name, String address, String filename) {
        return studentDAO.create(name, address, filename);
    }

    // UPDATE - PATCH
    public boolean update(String id, String name) {
        return studentDAO.update(id, name);

    }

    // DELETE - DELETE
    public boolean delete(String id) {
        return studentDAO.delete(id);
    }

    // UPLOAD FILE
    public boolean writeToFile(String uploadedFileLocation, String upDocument, String fileName)
            throws IOException {
        return studentDAO.writeToFile(uploadedFileLocation, upDocument, fileName);
    }

}

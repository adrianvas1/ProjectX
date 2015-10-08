package com.endava.service;

import com.endava.dao.StudentDAO;
import com.endava.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    public StudentService(StudentDAO documentDAO) {
        this.studentDAO = documentDAO;
    }

    Pattern pattern;
    Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,5})$";
    private static final String ADDRESS = "^(.+(Street,).+)(No|No\\.|Number).+$";
    private static final String CNP_PATTERN = "^[12]\\d{2}(0[1-9]|1[0-2])(0[0-9]|[12][0-9]|3[01])\\d{6}$";

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

    public String validate(String student, String cnp, String email, String address) {
        String result = "";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);

        if (matcher.matches() == true) {
            result += "Valid email";
        } else {
            result += "Invalid email; email must have the following format: example@provider.com(org, ro, ...)";
        }

        pattern = Pattern.compile(CNP_PATTERN);
        matcher = pattern.matcher(cnp);

        if (matcher.matches() == true) {
            result += " \nValid CNP";
        } else if (cnp.length() != 13) {
            result += "\nInvalid CNP; CNP must be 13 digits long.";
        } else {
            result += "\nInvalid CNP";
        }

        pattern = Pattern.compile(ADDRESS);
        matcher = pattern.matcher(address);

        if (matcher.matches() == true) {
            result += " \nValid address";
        } else {
            result += "\nInvalid address; address must have the following format: StreetName Street, Number|No.|No 123example";
        }
        return result;
    }
}

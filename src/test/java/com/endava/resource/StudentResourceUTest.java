package com.endava.resource;

import com.endava.config.ServiceConfiguration;
import com.endava.dto.StudentDTO;
import com.endava.service.StudentService;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.sun.jersey.core.header.FormDataContentDisposition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.get;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class StudentResourceUTest {

    @Mock
    StudentService studentService;
    @Mock
    ServiceConfiguration serviceConfiguration;
    @Mock
    StudentDTO studentDTO;
    @Mock
    FormDataContentDisposition fileDetail;

    public StudentResource studentResource;

    @Before
    public void setup() {
        studentResource = new StudentResource(serviceConfiguration, studentService);
    }

    @Test
    public void readAll() {
        expect().
                statusCode(200).
                when().
                get("/students");
        assertEquals(200, studentResource.readAll().getStatus());
    }

    @Test
    public void readAllFalse() {
        if (studentService.readAll() == null) {
            assertEquals(404, studentResource.readAll().getStatus());
        }
    }

    @Test
    public void readOne() {
        Response res = get("/students/560bc77e10208e242429674b");

        expect().
                statusCode(200).
                when().
                get("/students/560bc77e10208e242429674b");
        String json = res.asString();
        JsonPath jp = new JsonPath(json);
        assertEquals("application/json", res.getContentType());
        assertEquals(200, studentResource.readOne("560bc77e10208e242429674b").getStatus());
        assertEquals("s1", jp.get("name"));
    }

    @Test
    public void create() {
        assertEquals(201, studentResource.createDoc(studentDTO).getStatus());
    }

    @Test
    public void update() {
        assertEquals(200, studentResource.updateDoc("", studentDTO).getStatus());
    }

    @Test
    public void delete() {
        assertEquals(200, studentResource.deleteDoc("").getStatus());
    }

    @Test
    public void upload() {

        try {
            assertNotNull(studentResource.uploadFile("", fileDetail));
            assertEquals(200, studentResource.uploadFile("", fileDetail).getStatus());
        } catch (Exception e) {

        }
    }

    @Test
    public void download() {
        try {
            assertEquals(200, studentResource.getFile("").getStatus());
        } catch (Exception e) {

        }
    }}

package com.endava.resource;

import com.endava.config.PATCH;
import com.endava.config.ServiceConfiguration;
import com.endava.dto.StudentDTO;
import com.endava.model.Student;
import com.endava.service.StudentService;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Component
@Path("students")
public class StudentResource {


    @Autowired
    Environment environment;
    @Autowired
    public StudentService studentService;
    @Autowired
    public ServiceConfiguration serviceConfiguration;


    @Autowired
    public StudentResource(ServiceConfiguration serviceConfiguration, StudentService studentService) {
        this.serviceConfiguration = serviceConfiguration;
        this.studentService = studentService;
    }

    // create student
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDoc(@Valid StudentDTO dto) {
        Response response;
        String created = studentService.create(dto.getName(), dto.getAddress(), dto.getFileName());
        try {
            response = Response.status(201)
                    .header(
                            "Location",
                            String.format(
                                    "%s",
                                    created
                            )
                    )
                    .entity(created)
                    .build();
        } catch (Exception e) {
            response = Response.status(500).entity(e.getMessage()).build();
        }
        return response;
    }

    // GET ALL
    @GET
    public Response readAll() {
        List<Student> response;
        if(studentService.readAll() != null) {
            response = studentService.readAll();
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        }
        else return Response.status(404).entity("No entries found!").build();
    }


    //GET ONE
    @GET
    @Path(value = "/{_id}")
    public Response readOne(@PathParam("_id") String id) {
        String response = studentService.read(id);
        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }

    // UPDATE
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Path(value = "/{_id}")
    public Response updateDoc(@PathParam("_id") String id,
                              @Valid StudentDTO dto) {
        studentService.update(id, dto.getName());
        String response = "Document with 'id': " + id + ", was updated succesfully!";
        return Response.ok(response, MediaType.APPLICATION_XML).build();
    }

    // DELETE
    @DELETE
    @Path(value = "/{_id}")
    public Response deleteDoc(@PathParam("_id") String id) {
        studentService.delete(id);
        String response = "Document with '_id': " + id
                + ", was deleted succesfully!";
        return Response.ok(response, MediaType.APPLICATION_XML).build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_XML)
    public Response uploadFile(
            @FormDataParam("student") String student,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {

        String uploadedFileLocation = environment.getProperty("fileLocation")
                + fileDetail.getFileName();
        String fileName = fileDetail.getFileName();

        String output = "File uploaded to: " + uploadedFileLocation;
        if (studentService.writeToFile(uploadedFileLocation, student, fileName) == true) {
            return Response.status(200).entity(output).build();
        } else return Response.status(400).entity("Bad request: student must contain a name!").build();
    }

    @GET
    @Path("/files/{fileName}")
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public Response getFile(@PathParam("fileName") String fileName) {

        File file = new File(environment.getProperty("fileLocation") + fileName);
        Response.ResponseBuilder response = Response.ok(file);
        response.header("Content-Disposition", "attachment; filename="
                + fileName);
        return response.build();
    }

}

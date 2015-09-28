package com.endava.resource;

import com.endava.config.PATCH;
import com.endava.dto.StudentDTO;
import com.endava.model.Student;
import com.endava.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by avas on 9/28/2015.
 */
@Component
@Path("students")
public class StudentResource {


    @Autowired
    public StudentService service;

    // create student
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDoc(@Valid StudentDTO dto) {
        Response response;
        String created = service.create(dto.getName(), dto.getAddress(), dto.getFileName());
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
        if(service.readAll() != null) {
            response = service.readAll();
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        }
        else return Response.status(404).entity("No entries found!").build();
    }


    //GET ONE
    @GET
    @Path(value = "/{_id}")
    public Response readOne(@PathParam("_id") String id) {
        String response = service.read(id);
        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }

    // UPDATE
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Path(value = "/{_id}")
    public Response updateDoc(@PathParam("_id") String id,
                              @Valid StudentDTO dto) {
        service.update(id, dto.getName());
        String response = "Document with 'id': " + id + ", was updated succesfully!";
        return Response.ok(response, MediaType.APPLICATION_XML).build();
    }

    // DELETE
    @DELETE
    @Path(value = "/{_id}")
    public Response deleteDoc(@PathParam("_id") String id) {
        service.delete(id);
        String response = "Document with '_id': " + id
                + ", was deleted succesfully!";
        return Response.ok(response, MediaType.APPLICATION_XML).build();
    }

}

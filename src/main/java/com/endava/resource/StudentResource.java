package com.endava.resource;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by avas on 9/28/2015.
 */
@Component
@Path("students")
public class StudentResource {


    @GET
    public javax.ws.rs.core.Response readAll() {
        String s = "Gotcha!";
        return Response.ok(s, MediaType.APPLICATION_JSON).build();
    }
}

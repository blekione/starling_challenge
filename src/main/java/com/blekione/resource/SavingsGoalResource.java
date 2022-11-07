package com.blekione.resource;

import java.util.concurrent.ExecutionException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class SavingsGoalResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() throws InterruptedException, ExecutionException {
        return "{\"message\": \"Hello from RESTEasy Reactive\"}";
    }
}
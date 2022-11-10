package com.blekione.resource;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.blekione.response.SavingsGoalResponse;
import com.blekione.service.SavingsGoalService;

@Path("/savings-goals")
public class SavingsGoalResource {

    @Inject
    SavingsGoalService savingsGoalService;

    @PUT
    @Path("/{accountUid}/category/{categoryUid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGoal(@PathParam("accountUid") String accountUid, @PathParam("categoryUid") String categoryUid, @QueryParam("fromDate") LocalDate fromDate)
            throws InterruptedException, ExecutionException {
        SavingsGoalResponse response = savingsGoalService.createGoal(accountUid, categoryUid, fromDate)
                .toCompletableFuture().completeOnTimeout(new SavingsGoalResponse(false), 30, TimeUnit.SECONDS).get();
        if (response.isSuccess()) {
            return Response.ok().entity(response).build();
        }
        return Response.status(409).entity(new ErrorResponse()).build();
    }

    public static class ResponseOne {
        String message = "created 'Rounding Savings' goal";

        public ResponseOne() {
            // TODO Auto-generated constructor stub
        }

        public String getMessage() {
            return message;
        }
    }

    public static class ErrorResponse {
        String error = "The Rounding Goal has not been created.";

        public ErrorResponse() {
        }

        public String getError() {
            return error;
        }
    }
}
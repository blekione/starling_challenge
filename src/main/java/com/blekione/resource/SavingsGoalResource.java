package com.blekione.resource;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

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
    @Path("/{accountUid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGoal(@PathParam("accountUid") String accountUid, @QueryParam("fromDate") LocalDate fromDate) throws InterruptedException, ExecutionException {
        SavingsGoalResponse response = savingsGoalService.createGoal(accountUid, fromDate);
        if (response.isSuccess()) {
            return Response.ok().entity(new ResponseOne()).build();
        }
        return Response.ok().entity(new ResponseTwo()).build();
    }

    public static class  ResponseOne {
        String message = "created 'Rounding Savings' goal";

        public ResponseOne() {
            // TODO Auto-generated constructor stub
        }

        public String getMessage() {
            return message;
        }
    }

    public static class ResponseTwo {
        String error = "Some error";

        public ResponseTwo() {
            // TODO Auto-generated constructor stub
        }

        public String getError() {
            return error;
        }
    }
}
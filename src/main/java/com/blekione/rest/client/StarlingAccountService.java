package com.blekione.rest.client;

import java.util.concurrent.CompletionStage;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.blekione.rest.client.model.SavingsGoalList;

@Path("/account")
@RegisterRestClient(configKey = "starling-account")
@RegisterClientHeaders(StarlingApiHeadersFactory.class)
public interface StarlingAccountService {

    @GET
    @Path("/{accountUid}/savings-goals")
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<SavingsGoalList> getSavingGoals(@PathParam("accountUid") String accountUid);


}

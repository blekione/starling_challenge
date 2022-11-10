package com.blekione.rest.client;

import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.blekione.rest.client.model.Balance;
import com.blekione.rest.client.model.CreateOrUpdateSavingsGoalResponse;
import com.blekione.rest.client.model.SavingsGoalList;
import com.blekione.rest.client.model.SavingsGoalRequest;
import com.blekione.rest.client.model.SavingsGoalTransferResponse;
import com.blekione.rest.client.model.TopUpRequest;

@Path("/")
@RegisterRestClient(configKey = "starling-account")
@RegisterClientHeaders(StarlingApiHeadersFactory.class)
@ApplicationScoped
public interface StarlingAccountService {

    @GET
    @Path("account/{accountUid}/savings-goals")
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<SavingsGoalList> getSavingsGoals(@PathParam("accountUid") String accountUid);

    @PUT
    @Path("account/{accountUid}/savings-goals")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    CompletionStage<CreateOrUpdateSavingsGoalResponse> createSavingsGoal(@PathParam("accountUid") String accountUid,
            SavingsGoalRequest savingGoal);

    @PUT
    @Path("account/{accountUid}/savings-goals/{savingsGoalUid}/add-money/{transferUid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    CompletionStage<SavingsGoalTransferResponse> addMoneyToSavingGoal(@PathParam("accountUid") String accountUid,
            @PathParam("savingsGoalUid") String savingsGoalUid, @PathParam("transferUid") String transferUid, TopUpRequest request);

    @GET
    @Path("accounts/{accountUid}/balance")
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<Balance> getBalance(@PathParam("accountUid") String accountUid);

}

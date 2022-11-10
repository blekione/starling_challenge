package com.blekione.rest.client;

import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.blekione.rest.client.model.FeedItems;

@Path("/feed")
@RegisterRestClient(configKey = "starling-feed")
@RegisterClientHeaders(StarlingApiHeadersFactory.class)
@ApplicationScoped
public interface StarlingFeedService {

    @GET
    @Path("account/{accountUid}/category/{categoryUid}/transactions-between")
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<FeedItems> getByAccount(@PathParam("accountUid") String accountUid,
            @PathParam("categoryUid") String categoryUid,
            @QueryParam("minTransactionTimestamp") String minTransactionTimestamp,
            @QueryParam("maxTransactionTimestamp") String maxTransactionTimestamp);
}

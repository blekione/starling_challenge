package com.blekione.rest.client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import com.blekione.rest.client.model.FeedItem;
import com.blekione.rest.client.model.FeedItems;
import com.blekione.testutils.A;
import com.blekione.testutils.QuarkusWireMockExtension;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(QuarkusWireMockExtension.class)
public class StarlingFeedServiceTest {

    @Inject
    @RestClient
    StarlingFeedService service;

    @Inject
    DateTimeFormatter dateTimeFormatter;

    @Test
    void returnsExpectedFeedItemsIfThereAreTransactionsForThePeriod() throws Exception {
        // given
        String minTransactionTimestamp = LocalDateTime.of(2022, 10, 1, 12, 34, 56, 0).format(dateTimeFormatter);
        String maxTransactionTimestamp = LocalDateTime.of(2022, 11, 10, 12, 34, 56, 0).format(dateTimeFormatter);

        // when
        FeedItems actualItems = service.getByAccount("61ca3b12-33f0-47fd-a3be-7db27e10cffa",
                "c254cc12-eb42-4838-bf2f-ba3637edd448", minTransactionTimestamp, maxTransactionTimestamp)
                .toCompletableFuture().get();

        // then
        FeedItem expectedItemA = A.feedItem.build();
        FeedItem expectedItemB = A.feedItem.w("11233c30-2b95-4ce6-8025-64152c127f69")
                .w("GBP", BigInteger.valueOf(1000L)).build();

        assertThat(actualItems.getFeedItems(), containsInAnyOrder(expectedItemA, expectedItemB));
    }

    @Test
    void returnsEmptyFeedItemsIfThereAreNoTransactionsForThePeriod() throws Exception {
        // given
        String minTransactionTimestamp = LocalDateTime.of(2020, 9, 1, 12, 34, 56, 0).format(dateTimeFormatter);
        String maxTransactionTimestamp = LocalDateTime.of(2020, 9, 10, 12, 34, 56, 0).format(dateTimeFormatter);

        // when
        FeedItems actualItems = service.getByAccount("61ca3b12-33f0-47fd-a3be-7db27e10cffa",
                "c254cc12-eb42-4838-bf2f-ba3637edd448", minTransactionTimestamp, maxTransactionTimestamp)
                .toCompletableFuture().get();

        // then
        assertTrue(actualItems.getFeedItems().isEmpty());
    }

    @Test
    void completesWithErrorWhenNotExistingAccountGiven() throws Exception {
        // given
        String minTransactionTimestamp = LocalDateTime.of(2022, 10, 1, 12, 34, 56, 0).format(dateTimeFormatter);
        String maxTransactionTimestamp = LocalDateTime.of(2022, 11, 10, 12, 34, 56, 0).format(dateTimeFormatter);
        String incorrectAccountUid = "61ca3b12-33f0-47fd-a3be";

        // when
        CompletionStage<FeedItems> actualResponse = service.getByAccount(incorrectAccountUid,
                "c254cc12-eb42-4838-bf2f-ba3637edd448", minTransactionTimestamp, maxTransactionTimestamp);

        // then
        actualResponse.whenComplete((response, ex) -> {
            assertTrue(ex != null);
            assertThat(ex, instanceOf(WebApplicationException.class));
            WebApplicationException exUnwrapped = (WebApplicationException)ex;
            assertThat(exUnwrapped.getResponse().getStatus(), equalTo(404));
        });
    }
}

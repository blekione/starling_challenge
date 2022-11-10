package com.blekione.rest.client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import com.blekione.rest.client.model.Balance;
import com.blekione.rest.client.model.CreateOrUpdateSavingsGoalResponse;
import com.blekione.rest.client.model.SavingsGoal;
import com.blekione.rest.client.model.SavingsGoalRequest;
import com.blekione.rest.client.model.SavingsGoalTransferResponse;
import com.blekione.rest.client.model.TopUpRequest;
import com.blekione.testutils.A;
import com.blekione.testutils.QuarkusWireMockExtension;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(QuarkusWireMockExtension.class)
public class StarlingAccountServiceTest {

    @Inject
    @RestClient
    StarlingAccountService service;

    @Test
    void returnsSavingGoalsForAccount() throws Exception {
        // given
        String accountUid = "61ca3b12-33f0-47fd-a3be-7db27e10cffa";

        // when
        var actualSavingGoalList = service.getSavingsGoals(accountUid).toCompletableFuture().get();

        // then
        SavingsGoal expectedSavingGoalA = A.savingsGoal.build();
        SavingsGoal expectedSavingGoalB = A.savingsGoal.w("5b9fab8e-cbbc-4fd6-a3bb-8728ebf4ba1c", "Vacations").build();

        assertThat(actualSavingGoalList.getSavingsGoalList(),
                containsInAnyOrder(expectedSavingGoalA, expectedSavingGoalB));
    }

    @Test
    void returnsEmptySavingsGoalsWhenThereIsNoGoal() throws Exception {
        // given
        String accountUid = "61ca3b12-33f0-47fd-a3be";

        // when
        var actualSavingGoalList = service.getSavingsGoals(accountUid).toCompletableFuture().get();

        // then
        assertTrue(actualSavingGoalList.getSavingsGoalList().isEmpty());
    }

    @Test
    void createsSavingsGoal() throws Exception {
        // given
        String accounUid = "61ca3b12-33f0-47fd-a3be-7db27e10cffa";
        SavingsGoalRequest requestBody = A.savingsGoalRequest.build();

        // when
        var actualResponse = service.createSavingsGoal(accounUid, requestBody)
                .toCompletableFuture().get();

        // then
        CreateOrUpdateSavingsGoalResponse expectedResponse = new CreateOrUpdateSavingsGoalResponse(
                "b76311af-a8ee-4a7c-9e34-42da8c2e7bcb", true, Collections.emptyList());
        assertThat(actualResponse, equalTo(expectedResponse));
    }

    @Test
    void topUpsSavingsGoal() throws Exception {
        // given
        String accounUid = "61ca3b12-33f0-47fd-a3be-7db27e10cffa";
        String goalUid = "44457b88-bd9f-4643-ae46-664c9772b151";
        String transferUid = "123e4568-e89b-12d3-a456-556642440000";

        TopUpRequest request = A.topUpRequest.build();

        // when
        var actualResponse = service.addMoneyToSavingGoal(accounUid, goalUid, transferUid, request).toCompletableFuture().get();

        // then
        SavingsGoalTransferResponse expectedResponse = new SavingsGoalTransferResponse(transferUid, true,
                Collections.emptyList());
        assertThat(actualResponse, equalTo(expectedResponse));
    }

    @Test
    void getsTheBalance() throws Exception {
        // given
        String accounUid = "61ca3b12-33f0-47fd-a3be-7db27e10cffa";

        // when
        var actualBalance = service.getBalance(accounUid).toCompletableFuture().get();

        // then
        Balance expectedBalance = A.balance.build();
        assertThat(actualBalance, equalTo(expectedBalance));
    }
}

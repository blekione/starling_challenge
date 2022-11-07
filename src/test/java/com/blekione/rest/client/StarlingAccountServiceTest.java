package com.blekione.rest.client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import com.blekione.rest.client.model.SavingsGoal;
import com.blekione.rest.client.model.SavingsGoalList;
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
        SavingsGoalList actualSavingGoalList = service.getSavingGoals(accountUid).toCompletableFuture().get();

        // then
        SavingsGoal expectedSavingGoalA = A.savingGoal.build();
        SavingsGoal expectedSavingGoalB = A.savingGoal.w("5b9fab8e-cbbc-4fd6-a3bb-8728ebf4ba1c", "Vacations").build();

        assertThat(actualSavingGoalList.getSavingsGoalList(), containsInAnyOrder(expectedSavingGoalA, expectedSavingGoalB));
    }

    @Test
    void returnsEmptySavingsGoalsWhenThereIsNoGoal() throws Exception {
     // given
        String accountUid = "61ca3b12-33f0-47fd-a3be";

        // when
        SavingsGoalList actualSavingGoalList = service.getSavingGoals(accountUid).toCompletableFuture().get();

        // then
        assertTrue(actualSavingGoalList.getSavingsGoalList().isEmpty());
    }
}

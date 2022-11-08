package com.blekione.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.blekione.response.SavingsGoalResponse;
import com.blekione.service.SavingsGoalService;
import com.blekione.testutils.QuarkusWireMockExtension;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@QuarkusTest
@QuarkusTestResource(QuarkusWireMockExtension.class)
public class SavingsGoalResourceTest {

    @InjectMock
    SavingsGoalService savingsGoalService;

    @Test
    public void testHelloEndpoint() {
        // given
        SavingsGoalResponse success = new SavingsGoalResponse(true);
        when(savingsGoalService.createGoal(anyString(), any(LocalDate.class))).thenReturn(success);

        String accountUid = "111-222-333";
        LocalDate fromDate = LocalDate.of(2022, 10, 01);
        RequestSpecification request = given();

        // when
        Response response = request.when().put("/savings-goals/" + accountUid + "?fromDate=" + fromDate);

        // then
        response.then().statusCode(200).body("message", is("created 'Rounding Savings' goal"));
    }

}
package com.blekione.testutils;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.notContaining;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

import java.util.Map;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class QuarkusWireMockExtension implements QuarkusTestResourceLifecycleManager {

    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        wireMockServer.stubFor(get(urlPathEqualTo(
                "/feed/account/61ca3b12-33f0-47fd-a3be-7db27e10cffa/category/c254cc12-eb42-4838-bf2f-ba3637edd448/transactions-between"))
                .withQueryParam("minTransactionTimestamp", containing("2022-10-01"))
                .withQueryParam("maxTransactionTimestamp", containing("2022-11-10"))
                .withHeader("Authorization", containing("Bearer bearerKey"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withBody("{"
                                + "  \"feedItems\": ["
                                + "    {"
                                + "      \"feedItemUid\": \"112326e1-b29c-4658-8128-6f4a3b8526e9\","
                                + "      \"categoryUid\": \"c254cc12-eb42-4838-bf2f-ba3637edd448\","
                                + "      \"amount\": {"
                                + "        \"currency\": \"GBP\","
                                + "        \"minorUnits\": 5266"
                                + "      },"
                                + "      \"sourceAmount\": {"
                                + "        \"currency\": \"GBP\","
                                + "        \"minorUnits\": 5266"
                                + "      },"
                                + "      \"direction\": \"OUT\","
                                + "      \"updatedAt\": \"2022-11-03T18:11:18.662Z\","
                                + "      \"transactionTime\": \"2022-11-03T18:11:17.770Z\","
                                + "      \"settlementTime\": \"2022-11-03T18:11:18.567Z\","
                                + "      \"source\": \"FASTER_PAYMENTS_OUT\","
                                + "      \"status\": \"SETTLED\","
                                + "      \"transactingApplicationUserUid\": \"66d452ca-04b3-49d5-8ddf-d82560f7e55c\","
                                + "      \"counterPartyType\": \"PAYEE\","
                                + "      \"counterPartyUid\": \"56930d7d-fac6-4d20-98db-5238e79b267c\","
                                + "      \"counterPartyName\": \"Mickey Mouse\","
                                + "      \"counterPartySubEntityUid\": \"41edac0a-f0d1-44ee-88f9-5e9fbc1a8687\","
                                + "      \"counterPartySubEntityName\": \"UK account\","
                                + "      \"counterPartySubEntityIdentifier\": \"204514\","
                                + "      \"counterPartySubEntitySubIdentifier\": \"00000825\","
                                + "      \"reference\": \"Sim 131356903\","
                                + "      \"country\": \"GB\","
                                + "      \"spendingCategory\": \"SHOPPING\","
                                + "      \"hasAttachment\": false,"
                                + "      \"hasReceipt\": false,"
                                + "      \"batchPaymentDetails\": null"
                                + "    },"
                                + "    {"
                                + "      \"feedItemUid\": \"11233c30-2b95-4ce6-8025-64152c127f69\","
                                + "      \"categoryUid\": \"c254cc12-eb42-4838-bf2f-ba3637edd448\","
                                + "      \"amount\": {"
                                + "        \"currency\": \"GBP\","
                                + "        \"minorUnits\": 1000"
                                + "      },"
                                + "      \"sourceAmount\": {"
                                + "        \"currency\": \"GBP\","
                                + "        \"minorUnits\": 1000"
                                + "      },"
                                + "      \"direction\": \"OUT\","
                                + "      \"updatedAt\": \"2022-11-03T18:11:18.662Z\","
                                + "      \"transactionTime\": \"2022-11-03T18:11:17.770Z\","
                                + "      \"settlementTime\": \"2022-11-03T18:11:18.567Z\","
                                + "      \"source\": \"FASTER_PAYMENTS_OUT\","
                                + "      \"status\": \"SETTLED\","
                                + "      \"transactingApplicationUserUid\": \"66d452ca-04b3-49d5-8ddf-d82560f7e55c\","
                                + "      \"counterPartyType\": \"PAYEE\","
                                + "      \"counterPartyUid\": \"56930d7d-fac6-4d20-98db-5238e79b267c\","
                                + "      \"counterPartyName\": \"Mickey Mouse\","
                                + "      \"counterPartySubEntityUid\": \"41edac0a-f0d1-44ee-88f9-5e9fbc1a8687\","
                                + "      \"counterPartySubEntityName\": \"UK account\","
                                + "      \"counterPartySubEntityIdentifier\": \"204514\","
                                + "      \"counterPartySubEntitySubIdentifier\": \"00000825\","
                                + "      \"reference\": \"Sim 131356903\","
                                + "      \"country\": \"GB\","
                                + "      \"spendingCategory\": \"SHOPPING\","
                                + "      \"hasAttachment\": false,"
                                + "      \"hasReceipt\": false,"
                                + "      \"batchPaymentDetails\": null"
                                + "    }"
                                + "  ]"
                                + "}")));

        wireMockServer.stubFor(get(urlPathEqualTo(
                "/feed/account/61ca3b12-33f0-47fd-a3be-7db27e10cffa/category/c254cc12-eb42-4838-bf2f-ba3637edd448/transactions-between"))
                .withQueryParam("minTransactionTimestamp", notContaining("2022-10-01"))
                .withQueryParam("maxTransactionTimestamp", notContaining("2022-11-10"))
                .withHeader("Authorization", containing("Bearer bearerKey"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withBody("{"
                                + "  \"feedItems\": []"
                                + "}")));

        wireMockServer.stubFor(get(urlPathEqualTo(
                "/feed/account/61ca3b12-33f0-47fd-a3be/category/c254cc12-eb42-4838-bf2f-ba3637edd448/transactions-between"))
                .withQueryParam("minTransactionTimestamp", containing("2022-10-01"))
                .withQueryParam("maxTransactionTimestamp", containing("2022-11-10"))
                .withHeader("Authorization", containing("Bearer bearerKey"))
                .willReturn(aResponse().withStatus(404)));

        wireMockServer.stubFor(get(urlPathEqualTo(
                "/account/61ca3b12-33f0-47fd-a3be-7db27e10cffa/savings-goals"))
                .withHeader("Authorization", containing("Bearer bearerKey"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json").withStatus(200)
                        .withBody("{"
                                + "  \"savingsGoalList\": ["
                                + "    {"
                                + "      \"savingsGoalUid\": \"5b9fab8e-cbbc-4fd6-a3bb-8728ebf4ba1d\","
                                + "      \"name\": \"For a car\","
                                + "      \"target\": {"
                                + "        \"currency\": \"GBP\","
                                + "        \"minorUnits\": 1000000"
                                + "      },"
                                + "      \"totalSaved\": {"
                                + "        \"currency\": \"GBP\","
                                + "        \"minorUnits\": 20000"
                                + "      },"
                                + "      \"savedPercentage\": 2"
                                + "    },"
                                + "    {"
                                + "      \"savingsGoalUid\": \"5b9fab8e-cbbc-4fd6-a3bb-8728ebf4ba1c\","
                                + "      \"name\": \"Vacations\","
                                + "      \"target\": {"
                                + "        \"currency\": \"GBP\","
                                + "        \"minorUnits\": 1000000"
                                + "      },"
                                + "      \"totalSaved\": {"
                                + "        \"currency\": \"GBP\","
                                + "        \"minorUnits\": 20000"
                                + "      },"
                                + "      \"savedPercentage\": 2"
                                + "    }"
                                + "  ]"
                                + "}")));

        wireMockServer.stubFor(get(urlPathEqualTo(
                "/account/61ca3b12-33f0-47fd-a3be/savings-goals"))
                .withHeader("Authorization", containing("Bearer bearerKey"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json").withStatus(200)
                        .withBody("{"
                                + "    \"savingsGoalList\": []"
                                + "}")));
        wireMockServer.stubFor(put(urlPathEqualTo(
                "/account/61ca3b12-33f0-47fd-a3be-7db27e10cffa/savings-goals"))
                .withRequestBody(WireMock.equalToJson("{"
                        + "\"name\": \"test goal\","
                        + "\"currency\": \"GBP\","
                        + "\"target\": {"
                        + "  \"currency\":\"GBP\","
                        + "  \"minorUnits\": 20000"
                        + "  }"
                        + "}"))
                .withHeader("Authorization", containing("Bearer bearerKey"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json").withStatus(200)
                        .withBody("{"
                                + "  \"savingsGoalUid\": \"b76311af-a8ee-4a7c-9e34-42da8c2e7bcb\","
                                + "  \"success\": true,"
                                + "  \"errors\": []"
                                + "}")));

        wireMockServer.stubFor(put(urlPathEqualTo(
                "/account/61ca3b12-33f0-47fd-a3be-7db27e10cffa/savings-goals/44457b88-bd9f-4643-ae46-664c9772b151/add-money/123e4568-e89b-12d3-a456-556642440000"))
                .withRequestBody(WireMock.equalToJson("{"
                        + "  \"amount\": {"
                        + "    \"currency\": \"GBP\","
                        + "    \"minorUnits\": 10"
                        + "  }"
                        + "}"))
                .withHeader("Authorization", containing("Bearer bearerKey"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json").withStatus(200)
                        .withBody("{"
                                + "  \"transferUid\": \"123e4568-e89b-12d3-a456-556642440000\","
                                + "  \"success\": true,"
                                + "  \"errors\": []"
                                + "}")));
        wireMockServer.stubFor(get(urlPathEqualTo(
                "/accounts/61ca3b12-33f0-47fd-a3be-7db27e10cffa/balance"))
                .withHeader("Authorization", containing("Bearer bearerKey"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json").withStatus(200)
                        .withBody("{"
                                + "  \"clearedBalance\": {"
                                + "    \"currency\": \"GBP\","
                                + "    \"minorUnits\": 254277"
                                + "  },"
                                + "  \"effectiveBalance\": {"
                                + "    \"currency\": \"GBP\","
                                + "    \"minorUnits\": 254277"
                                + "  },"
                                + "  \"pendingTransactions\": {"
                                + "    \"currency\": \"GBP\","
                                + "    \"minorUnits\": 0"
                                + "  },"
                                + "  \"acceptedOverdraft\": {"
                                + "    \"currency\": \"GBP\","
                                + "    \"minorUnits\": 0"
                                + "  },"
                                + "  \"amount\": {"
                                + "    \"currency\": \"GBP\","
                                + "    \"minorUnits\": 254277"
                                + "  },"
                                + "  \"totalClearedBalance\": {"
                                + "    \"currency\": \"GBP\","
                                + "    \"minorUnits\": 254287"
                                + "  },"
                                + "  \"totalEffectiveBalance\": {"
                                + "    \"currency\": \"GBP\","
                                + "    \"minorUnits\": 254287"
                                + "  }"
                                + "}")));

        return Map.of("quarkus.rest-client.starling-feed.url", wireMockServer.baseUrl(), "rest-client.auth-key",
                "bearerKey", "quarkus.rest-client.starling-account.url", wireMockServer.baseUrl());

    }

    @Override
    public void stop() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }

}

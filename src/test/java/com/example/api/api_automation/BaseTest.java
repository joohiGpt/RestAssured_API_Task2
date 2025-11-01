package com.example.api.api_automation;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BaseTest {

    protected Response response;

    protected Response getRequest(String endpoint) {
        return RestAssured
                .given()
                .relaxedHTTPSValidation()
                .log().all()
                .when()
                .get(endpoint)
                .then()
                //.log().all()
                .extract().response();
    }

    protected Response postRequest(String endpoint, String payload) {
        return RestAssured
                .given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .body(payload)
                .log().all()
                .when()
                .post(endpoint)
                .then()
                //.log().all()
                .extract().response();
    }
}

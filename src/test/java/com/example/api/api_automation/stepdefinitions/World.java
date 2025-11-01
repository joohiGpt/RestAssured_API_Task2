package com.example.api.api_automation.stepdefinitions;

import io.restassured.response.Response;

/**
 * Shared "World" state for Cucumber dependency injection.
 * This allows different step definition classes to share data,
 * such as the latest API response, within the same scenario.
 */
public class World {
    public Response response;
}

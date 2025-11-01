package com.example.api.api_automation.stepdefinitions;

import com.example.api.api_automation.BaseTest;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import java.util.Map;

public class GetRequestSteps extends BaseTest {

    private final World world;

    // Constructor for dependency injection (shared response across step classes)
    public GetRequestSteps(World world) {
        this.world = world;
    }

    @Given("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        world.response = getRequest(endpoint);
    }

    @Then("I should receive a valid response with status code {int}")
    public void i_should_receive_a_valid_response_with_status_code(Integer statusCode) {
        Assert.assertNotNull("Response is null!", world.response);
        Assert.assertEquals(statusCode.intValue(), world.response.getStatusCode());
        Assert.assertTrue(
                "Response content type is not JSON",
                world.response.getContentType().toLowerCase().contains("application/json")
        );
    }

    @Then("the response should include the fields {string}, {string}, and {string}")
    public void the_response_should_include_the_fields_and(String pathKey, String ipKey, String headersKey) {
        JsonPath json = world.response.jsonPath();

        Assert.assertNotNull("Missing 'path'", json.get(pathKey));
        Assert.assertNotNull("Missing 'ip'", json.get(ipKey));
        Assert.assertNotNull("Missing 'headers'", json.get(headersKey));

        Assert.assertTrue("Path should be non-empty", json.getString(pathKey).length() > 0);
        Assert.assertTrue("IP should be non-empty", json.getString(ipKey).length() > 0);
    }

    @Then("all headers should be present in the response")
    public void all_headers_should_be_present_in_the_response() {
        JsonPath json = world.response.jsonPath();
        Map<String, Object> headers = json.getMap("headers");

        Assert.assertNotNull("Headers map is null", headers);
        String keys = headers.keySet().toString().toLowerCase();

        Assert.assertTrue("Expected host header", keys.contains("host"));
        Assert.assertTrue("Expected user-agent header", keys.contains("user-agent"));
    }
}

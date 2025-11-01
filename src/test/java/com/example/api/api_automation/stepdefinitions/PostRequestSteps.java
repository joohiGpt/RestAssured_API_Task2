package com.example.api.api_automation.stepdefinitions;

import com.example.api.api_automation.BaseTest;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

public class PostRequestSteps extends BaseTest {

    private final World world;

    public PostRequestSteps(World world) {
        this.world = world;
    }

    private final String payload = "{\n" +
            "  \"order_id\": \"12345\",\n" +
            "  \"customer\": {\n" +
            "    \"name\": \"Jane Smith\",\n" +
            "    \"email\": \"janesmith@example.com\",\n" +
            "    \"phone\": \"1-987-654-3210\",\n" +
            "    \"address\": {\n" +
            "      \"street\": \"456 Oak Street\",\n" +
            "      \"city\": \"Metropolis\",\n" +
            "      \"state\": \"NY\",\n" +
            "      \"zipcode\": \"10001\",\n" +
            "      \"country\": \"USA\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"items\": [\n" +
            "    {\"product_id\": \"A101\", \"name\": \"Wireless Headphones\", \"quantity\": 1, \"price\": 79.99},\n" +
            "    {\"product_id\": \"B202\", \"name\": \"Smartphone Case\", \"quantity\": 2, \"price\": 15.99}\n" +
            "  ],\n" +
            "  \"payment\": {\"method\": \"credit_card\", \"transaction_id\": \"txn_67890\", \"amount\": 111.97, \"currency\": \"USD\"},\n" +
            "  \"shipping\": {\"method\": \"standard\", \"cost\": 5.99, \"estimated_delivery\": \"2024-11-15\"},\n" +
            "  \"order_status\": \"processing\",\n" +
            "  \"created_at\": \"2024-11-07T12:00:00Z\"\n" +
            "}";

    @Given("I send a POST request to {string} with valid payload")
    public void i_send_a_post_request_to_with_valid_payload(String endpoint) {
        world.response = postRequest(endpoint, payload);
    }

    @Then("the response should include correct customer, payment, and product details")
    public void the_response_should_include_correct_customer_payment_and_product_details() {
        JsonPath json = world.response.jsonPath();

        // Customer (note: data is under parsedBody)
        Assert.assertEquals("Jane Smith", json.getString("parsedBody.customer.name"));
        Assert.assertEquals("janesmith@example.com", json.getString("parsedBody.customer.email"));
        Assert.assertEquals("456 Oak Street", json.getString("parsedBody.customer.address.street"));
        Assert.assertEquals("Metropolis", json.getString("parsedBody.customer.address.city"));
        Assert.assertEquals("NY", json.getString("parsedBody.customer.address.state"));

        // Payment
        Assert.assertEquals("credit_card", json.getString("parsedBody.payment.method"));
        Assert.assertEquals("txn_67890", json.getString("parsedBody.payment.transaction_id"));
        Assert.assertEquals(111.97, json.getDouble("parsedBody.payment.amount"), 0.0);
        Assert.assertEquals("USD", json.getString("parsedBody.payment.currency"));

        // Items
        Assert.assertEquals("A101", json.getString("parsedBody.items[0].product_id"));
        Assert.assertEquals(1, (int) json.getInt("parsedBody.items[0].quantity"));
        Assert.assertEquals(79.99, json.getDouble("parsedBody.items[0].price"), 0.0);

        Assert.assertEquals("B202", json.getString("parsedBody.items[1].product_id"));
        Assert.assertEquals(2, (int) json.getInt("parsedBody.items[1].quantity"));
        Assert.assertEquals(15.99, json.getDouble("parsedBody.items[1].price"), 0.0);

        // Status
        Assert.assertEquals("processing", json.getString("parsedBody.order_status"));
    }
}

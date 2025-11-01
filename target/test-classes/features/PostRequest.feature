Feature: Validate POST API response from Beeceptor

  Scenario: Verify POST request with order details
    Given I send a POST request to "http://echo.free.beeceptor.com/sample-request?author=beeceptor" with valid payload
    Then I should receive a valid response with status code 200
    And the response should include correct customer, payment, and product details

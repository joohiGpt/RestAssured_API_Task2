Feature: Validate GET API response from Beeceptor

  Scenario: Validate fields in the GET response
    Given I send a GET request to "https://echo.free.beeceptor.com/sample-request?author=beeceptor"
    Then I should receive a valid response with status code 200
    And the response should include the fields "path", "ip", and "headers"
    And all headers should be present in the response

package steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class CommonSteps {
    protected static Response response;
    protected static String baseUrl;
    private static String responseBody; // Cache response body
    
    public static RequestSpecification createRequest() {
        return RestAssured.given()
            .contentType("application/json")
            .accept("application/json");
    }
    
    public static RequestSpecification createMultipartRequest() {
        return RestAssured.given()
            .accept("application/json");
    }
    
    public static void setResponse(Response resp) {
        response = resp;
        // Cache the response body to avoid stream issues
        try {
            responseBody = resp.getBody().asString();
        } catch (Exception e) {
            responseBody = "";
        }
    }
    
    public static Response getResponse() {
        return response;
    }
    
    public static String getResponseBody() {
        return responseBody;
    }
    
    public static String getBaseUrl() {
        return RestAssured.baseURI;
    }

    @Given("the Petstore API is available at {string}")
    public void the_petstore_api_is_available_at(String url) {
        RestAssured.baseURI = url;
    }
    
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, 
            "Expected status code: " + expectedStatusCode + ", but got: " + response.getStatusCode());
    }
    
    @Then("the response should contain {string} message")
    public void the_response_should_contain_message(String expectedMessage) {
        if (responseBody == null || responseBody.trim().isEmpty()) {
            Assert.fail("Response body is empty, cannot check for message: " + expectedMessage);
        }
        try {
            // Try to parse as JSON first
            if (responseBody.trim().startsWith("{") || responseBody.trim().startsWith("[")) {
                String actualMessage = response.jsonPath().getString("message");
                if (actualMessage != null) {
                    Assert.assertTrue(actualMessage.contains(expectedMessage), 
                        "Expected message: " + expectedMessage + ", but got: " + actualMessage);
                } else {
                    Assert.assertTrue(responseBody.contains(expectedMessage), 
                        "Expected message: " + expectedMessage + " not found in response: " + responseBody);
                }
            } else {
                // Plain text response
                Assert.assertTrue(responseBody.contains(expectedMessage), 
                    "Expected message: " + expectedMessage + " not found in response: " + responseBody);
            }
        } catch (Exception e) {
            // Fallback to string search if JSON parsing fails
            Assert.assertTrue(responseBody.contains(expectedMessage), 
                "Expected message: " + expectedMessage + " not found in response: " + responseBody);
        }
    }
}
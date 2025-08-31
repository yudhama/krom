package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class CommonApiSteps {
    private static RequestSpecification request;
    private static Response response;
    private static String baseUrl;
    
    public static void setupApi(String url) {
        baseUrl = url;
        RestAssured.baseURI = url;
        request = RestAssured.given()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json");
    }
    
    public static RequestSpecification getRequest() {
        return request;
    }
    
    public static void setResponse(Response resp) {
        response = resp;
    }
    
    public static Response getResponse() {
        return response;
    }
    
    public static void validateStatusCode(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, 
            "Expected status code: " + expectedStatusCode + ", but got: " + response.getStatusCode());
    }
    
    public static void validateResponseMessage(String expectedMessage) {
        String actualMessage = response.jsonPath().getString("message");
        Assert.assertTrue(actualMessage.contains(expectedMessage), 
            "Expected message: " + expectedMessage + ", but got: " + actualMessage);
    }
}
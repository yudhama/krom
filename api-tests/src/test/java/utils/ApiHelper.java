package utils;

import io.restassured.response.Response;
import org.testng.Assert;

public class ApiHelper {
    
    public static void validatePetSchema(Response response) {
        // Validate basic pet schema
        Assert.assertNotNull(response.jsonPath().get("id"), "Pet ID should be present");
        Assert.assertNotNull(response.jsonPath().get("name"), "Pet name should be present");
        Assert.assertNotNull(response.jsonPath().get("status"), "Pet status should be present");
        
        // Validate status values
        String status = response.jsonPath().getString("status");
        Assert.assertTrue(status.equals("available") || status.equals("pending") || status.equals("sold"),
            "Pet status should be one of: available, pending, sold");
    }
    
    public static void validateOrderSchema(Response response) {
        // Validate basic order schema
        Assert.assertNotNull(response.jsonPath().get("id"), "Order ID should be present");
        Assert.assertNotNull(response.jsonPath().get("petId"), "Pet ID should be present in order");
        Assert.assertNotNull(response.jsonPath().get("quantity"), "Quantity should be present");
        Assert.assertNotNull(response.jsonPath().get("status"), "Order status should be present");
        
        // Validate status values
        String status = response.jsonPath().getString("status");
        Assert.assertTrue(status.equals("placed") || status.equals("approved") || status.equals("delivered"),
            "Order status should be one of: placed, approved, delivered");
        
        // Validate quantity is positive
        Integer quantity = response.jsonPath().getInt("quantity");
        Assert.assertTrue(quantity > 0, "Order quantity should be positive");
    }
    
    public static void validateInventorySchema(Response response) {
        // Validate that response is a JSON object with status counts
        Assert.assertNotNull(response.jsonPath().get(""), "Inventory response should not be null");
        
        // Check if response contains typical inventory status keys
        Object responseBody = response.jsonPath().get("");
        Assert.assertTrue(responseBody instanceof java.util.Map, "Inventory should be a JSON object");
    }
}
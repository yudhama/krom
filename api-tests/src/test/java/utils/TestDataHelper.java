package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TestDataHelper {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    public static String createPetPayload(String name, String status) {
        ObjectNode pet = objectMapper.createObjectNode();
        pet.put("id", System.currentTimeMillis());
        pet.put("name", name);
        pet.put("status", status);
        
        ObjectNode category = objectMapper.createObjectNode();
        category.put("id", 1);
        category.put("name", "Dogs");
        pet.set("category", category);
        
        return pet.toString();
    }
    
    public static String createPetPayloadWithId(int id, String name, String status) {
        ObjectNode pet = objectMapper.createObjectNode();
        pet.put("id", id);
        pet.put("name", name);
        pet.put("status", status);
        
        ObjectNode category = objectMapper.createObjectNode();
        category.put("id", 1);
        category.put("name", "Dogs");
        pet.set("category", category);
        
        return pet.toString();
    }
    
    public static String createInvalidPetPayload() {
        ObjectNode pet = objectMapper.createObjectNode();
        // Missing required fields like name
        pet.put("status", "available");
        return pet.toString();
    }
    
    // In the createOrderPayload method, ensure status is set to "placed" not "available"
    public static String createOrderPayload(int petId, int quantity) {
        return "{\"petId\":" + petId + ",\"quantity\":" + quantity + ",\"status\":\"placed\",\"complete\":false}";
    }
    
    public static String createOrderPayloadWithId(int orderId, int petId, int quantity) {
        ObjectNode order = objectMapper.createObjectNode();
        order.put("id", orderId);
        order.put("petId", petId);
        order.put("quantity", quantity);
        order.put("shipDate", "2024-01-15T10:00:00.000Z");
        order.put("status", "placed");
        order.put("complete", false);
        return order.toString();
    }
    
    public static String createInvalidOrderPayload() {
        ObjectNode order = objectMapper.createObjectNode();
        order.put("petId", -1); // Invalid pet ID
        order.put("quantity", 1);
        return order.toString();
    }
    
    public static String createIncompleteOrderPayload() {
        ObjectNode order = objectMapper.createObjectNode();
        // Missing required petId field
        order.put("quantity", 1);
        order.put("status", "placed");
        return order.toString();
    }
}
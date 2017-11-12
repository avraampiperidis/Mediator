/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.mediator;

import io.restassured.RestAssured;
import java.io.StringReader;
import java.math.BigDecimal;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import static org.junit.Assert.assertTrue;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author piper
 */
public class TestMediator {
    
    
    @Test
    public void testMain() {
        System.out.println();
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "test";
        
        JsonReader jsonReader = Json.createReader(new StringReader(RestAssured.given()
                .contentType("application/json")
                .get("model")
                .then()
                .statusCode(200)
                .extract().body().asString()));
        
        JsonObject jsonData = jsonReader.readObject();
        
        JsonObject skeleton = Mediator.generateSkeleton(jsonData);
                
        Mediator.given(jsonData)
                .with(skeleton)
                .exact();
                
    }
    
    
    @Test
    @Ignore
    public void testArray() {
        System.out.println();
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "test";
        
        JsonReader jsonReader = Json.createReader(new StringReader(RestAssured.given()
                .contentType("application/json")
                .get("model/findAll")
                .then()
                .statusCode(200)
                .extract().body().asString()));
        
        JsonArray jsonArr = jsonReader.readArray();
        JsonArray skeleton = Mediator.generateSkeleton(jsonArr);
                
    }
    
    
    
    @Test
    @Ignore
    public void testArrStrings() {
        System.out.println();
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "test";
        
        JsonReader jsonReader = Json.createReader(new StringReader(RestAssured.given()
                .contentType("application/json")
                .get("model/strings")
                .then()
                .statusCode(200)
                .extract().body().asString()));
        
        JsonArray jsonArr = jsonReader.readArray();
        JsonArray skeleton = Mediator.generateSkeleton(jsonArr);
                
        System.out.println(skeleton);
    }
    
    
    @Test
    @Ignore
    public void testEmptyJsonObject() {
        System.out.println();
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "test";
        
        JsonReader jsonReader = Json.createReader(new StringReader(RestAssured.given()
                .contentType("application/json")
                .get("model/mock2")
                .then()
                .statusCode(200)
                .extract().body().asString()));
        
        JsonObject json = jsonReader.readObject();
        JsonObject skeleton = Mediator.generateSkeleton(json);
                
        System.out.println(skeleton);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.mediator;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.junit.Rule;
import org.junit.Test;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

/**
 *
 * @author piper
 */
public class TestMediatorIT {
    
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
    
    /**
     * set up stubs
     */
    @Before
    public void beforeTest() {
        stubFor(get(urlEqualTo("/test/model"))
            .withHeader("Accept", equalTo("application/json"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(Mock.JSON_OB)));
        
        stubFor(get(urlEqualTo("/test/model2"))
            .withHeader("Accept", equalTo("application/json"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(Json.createObjectBuilder().add("name","value")
                        .add("id",1)
                        .add("lastname","value")
                        .add("address", Json.createObjectBuilder().add("street","streetAddress").add("address",12).build())
                        .build().toString()
                )));
        
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = wireMockRule.port();
        RestAssured.basePath = "test";
    }
    
    
    @Test
    public void testMediatorJoinExact() {
        JsonReader jsonReader = Json.createReader(new StringReader(RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .get("model")
                .then()
                .extract().body().asString()));
        JsonObject jsonData = jsonReader.readObject();
        String skeleton = Mediator
                .given(jsonData.toString())
                .withArray(ARRAY.ALL)
                .generateSkeleton();
        Mediator.given(jsonData.toString())
                .withArray(ARRAY.ALL)
                .withSkel(skeleton)
                .joinExact();
    }
    
    
    
    @Test
    public void testMediatorJoin() {
        JsonReader jsonReader = Json.createReader(new StringReader(RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .get("model2")
                .then()
                .extract().body().asString()));
        JsonObject jsonData = jsonReader.readObject();
        String skeleton = "{\"type2\":\"string\",\"type1\":\"number\",\"type3\":\"string\",\"type4\":{\"type5\":\"string\",\"type6\":\"number\"}}\n";
        System.out.println(skeleton);
        Mediator.given(jsonData.toString())
                .withArray(ARRAY.ALL)
                .withSkel(skeleton)
                .join();
    }
    
    
    @Test(expected = MediatorAssertionError.class)
    public void testMediatorJoinExactFail() {
        JsonReader jsonReader = Json.createReader(new StringReader(RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .get("model2")
                .then()
                .extract().body().asString()));
        String skeleton = Json.createObjectBuilder().add("type2","string")
                        .add("type1","number")
                        .add("type3","string")
                        .add("type4", Json.createObjectBuilder().add("type5","string").add("type6","number").build())
                        .build().toString();
        AssertionError error = null;
        try {
            Mediator.given(jsonReader.readObject().toString())
                .withArray(ARRAY.ALL)
                .withSkel(skeleton)
                .joinExact();
        }catch(AssertionError ex) {
            error = ex;
            assertTrue(error instanceof MediatorAssertionError);
            MediatorAssertionError merror = (MediatorAssertionError)error;
            assertEquals(ErrorType.EQUALITY.ordinal(),merror.getCode().ordinal());
            throw merror;
        }
    }
    
    
    @Test(expected = AssertionError.class)
    public void testMediatorJoinFail() {
        throw new MediatorAssertionError();
    }
    
}

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
import org.junit.Before;

/**
 *
 * @author piper
 */
public class TestMediator {
    
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
    
    @Before
    public void beforeTest() {
        stubFor(get(urlEqualTo("/test/model"))
            .withHeader("Accept", equalTo("application/json"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(Mock.JSON_OB)));
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = wireMockRule.port();
        RestAssured.basePath = "test";
    }
    
    
    @Test
    public void testMediatorSuccess() {
        JsonReader jsonReader = Json.createReader(new StringReader(RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .get("model")
                .then()
                .statusCode(200)
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
    
    
    
    @Test(expected = AssertionError.class)
    public void testMediatorFail() {
        JsonReader jsonReader = Json.createReader(new StringReader(RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .get("model")
                .then()
                .statusCode(200)
                .extract().body().asString()));
        JsonObject jsonData = jsonReader.readObject();
        String skeleton = Mediator
                .given(jsonData.toString())
                .withArray(ARRAY.ALL)
                .generateSkeleton();
        Mediator.given(Mock.JSON_3)
                .withArray(ARRAY.ALL)
                .withSkel(skeleton)
                .joinExact();
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.mediator;

import javax.json.JsonArray;
import javax.json.JsonObject;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author piper
 */
public class TestJsonUtil {
    
    
    @Test
    public void testIsJsonArray() {
        assertTrue(JsonUtil.isJsonArray("[]"));
        assertFalse(JsonUtil.isJsonArray("{}"));
        assertTrue(JsonUtil.isJsonArray("[{},{}]"));
        assertTrue(JsonUtil.isJsonArray("[2,3]"));
        assertTrue(JsonUtil.isJsonArray("[\"val\",\"val2\"]"));
        assertFalse(JsonUtil.isJsonArray("{\"arr\":[]}"));
        assertFalse(JsonUtil.isJsonArray("{\"key\":\"val\",\"arr\":[]}"));
        assertTrue(JsonUtil.isJsonArray(Mock.JSON_ARR));
    }
    
    @Test
    public void testGetJsonObject() {
        assertNotNull(JsonUtil.getJsonObject(Mock.JSON_OB));
    }
    
    @Test
    public void testGetJsonArray() {
        assertNotNull(JsonUtil.getJsonArray(Mock.JSON_ARR));
    }
    
    @Test
    public void testGetJsonStructure() {
        assertTrue(JsonUtil.getJsonStructure(Mock.JSON_OB) instanceof JsonObject);
        assertTrue(JsonUtil.getJsonStructure(Mock.JSON_ARR) instanceof JsonArray);
    }
    
}

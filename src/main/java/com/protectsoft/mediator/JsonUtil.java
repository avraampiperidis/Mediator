/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.mediator;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

/**
 *
 * @author piper
 */
public class JsonUtil {
    
    public static JsonObject getJsonObject(String json) {
        return getJsonReader(json).readObject();
    }
    
    public static JsonArray getJsonArray(String json) {
        return getJsonReader(json).readArray();
    }
    
    public static boolean isJsonArray(String json) {
        return getJsonReader(json).readValue().getValueType().equals(JsonValue.ValueType.ARRAY);
    }
    
    
    
    private static JsonReader getJsonReader(String json) {
        return Json.createReader(new StringReader(json));
    }
    
}

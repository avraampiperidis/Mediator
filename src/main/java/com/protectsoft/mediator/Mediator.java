/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.mediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonString;
import javax.json.JsonValue;

/**
 *
 * @author avraam
 */
public class Mediator {

    
    
    private JsonObject object;
    private Object skeleton;
    
    //temp counter
    private static int typeCounter=1;
    
    private Mediator(JsonObject object) {
        this.object = object;
    }

   
    public static Mediator given(JsonObject object) {
        return new Mediator(object);
    }

    public Mediator with(Object skeleton) {
        this.skeleton = skeleton;
        return this;
    }

    
    public void exact() {
        typeCounter=1;
        if(skeleton instanceof JsonObject) {
            if(!((JsonObject)skeleton).toString().equals(generateSkeleton(object).toString())) {
                throw new AssertionError();
            }
        }
        
    }
    
    
    public static JsonObject generateSkeleton(JsonObject jsonData) {
        JsonObject skel = getSkeleton(jsonData);
        return skel;
    }
    
    
    public static JsonArray generateSkeleton(JsonArray jsonArr) {
        JsonArrayBuilder skelArr = Json.createArrayBuilder();
        for(JsonValue val:jsonArr) {
            if(val instanceof JsonObject) {
                skelArr.add(getSkeleton((JsonObject)val));
            } else if(val instanceof JsonString) {
                skelArr.add("string");
            } else if(val instanceof JsonNumber) {
                skelArr.add("number");
            } else if(val.equals(JsonValue.TRUE) || val.equals(JsonValue.FALSE)) {
                skelArr.add("boolean");
            }
        }
        
        return skelArr.build();
    }
    
    
    private static JsonObject getSkeleton(JsonObject json) {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        
        for(Map.Entry<String,JsonValue> entry: json.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            
            if(value.equals(JsonValue.NULL)) {
                //
            } else if(value instanceof JsonString) {
                obj.add(getTypeKey(),"string");
            } else if(value instanceof JsonNumber) {
                obj.add(getTypeKey(),"number");
            } else if(value instanceof JsonObject) {
                obj.add(getTypeKey(),getSkeleton((JsonObject)value));
            } else if(value.equals(JsonValue.TRUE) || value.equals(JsonValue.FALSE)) {
                obj.add(getTypeKey(),"boolean");
            } else if(value instanceof JsonArray) {
                
                JsonArrayBuilder list = Json.createArrayBuilder();
                JsonArray arr = ((JsonArray) value);
                for(JsonValue val : arr) {
                    if(val instanceof JsonString) {
                        list.add("string");
                        break;
                    }
                }
                for(JsonValue val : arr) {
                    if(val instanceof JsonNumber) {
                        list.add("number");
                        break;
                    }
                }
                for(JsonValue val : arr) {
                    if(val.equals(JsonValue.FALSE) || val.equals(JsonValue.TRUE)) {
                        list.add("boolean");
                        break;
                    }
                }                
                for(JsonValue val : arr) {
                    if(val instanceof JsonObject) {
                        JsonObject ob = getSkeleton((JsonObject)val);
                        list.add(getSkeleton((JsonObject)val));
                        //break;
                    }
                }
                
                obj.add(getTypeKey(), list);
            }
            
        }
        
        return obj.build();
    }
    
    
    
    private static String getTypeKey() {
        return "type"+typeCounter++;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.mediator;

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
 * @author piper
 */
class SkeletonBuilder {
    
    private Skeleton skel;
    private int typeCounter;
    
    public SkeletonBuilder(Skeleton skel) {
        this.skel = skel;       
    }
    
    
    public String build() {
        typeCounter = 1;
        if(JsonUtil.isJsonArray(skel.getData())) {
            return getSkeleton(JsonUtil.getJsonArray(skel.getData())).toString();
        } else {
            return getSkeleton(JsonUtil.getJsonObject(skel.getData())).toString();
        }
    }
    
    
    private JsonArray getSkeleton(JsonArray jsonArr) {
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
            } else if(val instanceof JsonArray) {
                skelArr.add(getSkeleton((JsonArray)val));
            }
            
            if(getArrayOnlyFirst()) {
                break;
            }
        }
        
        return skelArr.build();
    }
    
    
    private  JsonObject getSkeleton(JsonObject json) {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        
        for(Map.Entry<String,JsonValue> entry: json.entrySet()) {
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
                        if(getArrayOnlyFirst()) {
                            break;
                        }
                    }
                }
                for(JsonValue val : arr) {
                    if(val instanceof JsonNumber) {
                        list.add("number");
                        if(getArrayOnlyFirst()) {
                            break;
                        }
                    }
                }
                for(JsonValue val : arr) {
                    if(val.equals(JsonValue.FALSE) || val.equals(JsonValue.TRUE)) {
                        list.add("boolean");
                        if(getArrayOnlyFirst()) {
                            break;
                        }
                    }
                }                
                for(JsonValue val : arr) {
                    if(val instanceof JsonObject) {
                        list.add(getSkeleton((JsonObject)val));
                        if(getArrayOnlyFirst()) {
                            break;
                        }
                    } else if(val instanceof JsonArray) {
                        list.add(getSkeleton((JsonArray)val));
                        if(getArrayOnlyFirst()) {
                            break;
                        }
                    }
                }
                
                obj.add(getTypeKey(), list);
            }
            
        }
        
        return obj.build();
    }
    
    
    private boolean getArrayOnlyFirst() {
        return skel.getOptions().getArrayOption().equals(ARRAY.FIRST);
    }
    
    
    private String getTypeKey() {
        return "type"+typeCounter++;
    }
    
}

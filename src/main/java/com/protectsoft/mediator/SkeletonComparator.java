/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.mediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonStructure;
import javax.json.JsonValue;

/**
 *
 * @author piper
 */
public class SkeletonComparator {
    
    private Skeleton skel;
    
    private SkeletonComparator(Skeleton skeleton) {
        this.skel = skeleton;
    }
    
    static void join(Skeleton skeleton) {
        new SkeletonComparator(skeleton).joinExact();
    }

    static void joinAbove(Skeleton skeleton) {
        new SkeletonComparator(skeleton).joinAbove();
    }
    
    
    private void joinExact() {
        String dataSkeleton = new SkeletonBuilder(skel).build();
        String userSkeleton = skel.getSkeleton();
        if(isArray()) {
            JsonArray userSkel = JsonUtil.getJsonArray(userSkeleton);
            JsonArray dataSkel = JsonUtil.getJsonArray(dataSkeleton);
            List<String> userkeys = getKeysFlat(userSkel,new ArrayList<String>());
            List<String> dataKeys = getKeysFlat(dataSkel,new ArrayList<String>());
            compare(userSkel,dataSkel,userkeys,dataKeys);
        } else {
            JsonObject userSkel = JsonUtil.getJsonObject(userSkeleton);
            JsonObject dataSkel = JsonUtil.getJsonObject(dataSkeleton);
            List<String> userkeys = getKeysFlat(userSkel,new ArrayList<String>());
            List<String> dataKeys = getKeysFlat(dataSkel,new ArrayList<String>());
            compare(userSkel,dataSkel,userkeys,dataKeys);
        }
    }
    
    
    //not implemented yet
    private void joinAbove() {
    }
    
    
    private void compare(JsonStructure userSkel,JsonStructure dataSkel,List<String> userKeys,List<String> dataKeys) {
        for(String k : userKeys) {
            if(!dataKeys.remove(k)) {
                throw new AssertionError("Given the skeleton the:"+k+" doesn't exist");
            }
        }
        if(!dataKeys.isEmpty()) {
            String types = listToString(dataKeys);
            throw new AssertionError("types didn't found on data given the skeleton:"+types);
        }
        if(!userSkel.toString().equals(dataSkel.toString())) {
            throw new AssertionError("Given Skeleton String didn't match the generated skeleton from data");
        }
    }
    
    
    private List<String> getKeysFlat(JsonArray json,List<String> keys) {
        for(JsonValue val:json) {
            if(val instanceof JsonObject) {
                getKeysFlat((JsonObject)val,keys);
            } else if(val instanceof JsonArray) {
                getKeysFlat((JsonArray)val,keys);
            }
        }
        return keys;
    }
    
    private List<String> getKeysFlat(JsonObject json,List<String> keys) {
        for(Map.Entry<String,JsonValue> entry: json.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            keys.add(key);
            if(value instanceof JsonObject) {
                getKeysFlat((JsonObject)value,keys);
            } else if(value instanceof JsonArray) {
                JsonArray arr = ((JsonArray) value);
                for(JsonValue val : arr) {
                    if(val instanceof JsonObject) {
                        getKeysFlat((JsonObject)val,keys);
                    } else if(val instanceof JsonArray) {
                        getKeysFlat((JsonArray)val,keys);
                    }
                }
            }
        }
        
        return keys;
    }
    
    private boolean isArray() {
        return JsonUtil.isJsonArray(skel.getData());
    }
    
    
    private String listToString(List<String> list) {
        String listString = "";
        for (String s : list) {
            listString += s + ",";
        }
        return listString;
    }
 
}

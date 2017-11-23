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
        new SkeletonComparator(skeleton).join();
    }
    
    static void joinExact(Skeleton skeleton) {
        new SkeletonComparator(skeleton).joinExact();
    }

    static void joinAbove(Skeleton skeleton) {
        new SkeletonComparator(skeleton).joinAbove();
    }
    
    private void join() {
        String dataSkeleton = new SkeletonBuilder(skel).build();
        String userSkeleton = skel.getSkeleton();
        JsonStructure userSkel = JsonUtil.getJsonStructure(userSkeleton);
        JsonStructure dataSkel = JsonUtil.getJsonStructure(dataSkeleton);
        join(userSkel,dataSkel);
    }
    
    private void join(JsonStructure userSkel,JsonStructure dataSkel) {
        List<String> userkeys = getKeysFlat(userSkel,new ArrayList<String>());
        List<String> dataKeys = getKeysFlat(dataSkel,new ArrayList<String>());
        compare(userkeys,dataKeys);
    }
    
    private void joinExact() {
        String dataSkeleton = new SkeletonBuilder(skel).build();
        String userSkeleton = skel.getSkeleton();
        JsonStructure userSkel = JsonUtil.getJsonStructure(userSkeleton);
        JsonStructure dataSkel = JsonUtil.getJsonStructure(dataSkeleton);
        join(userSkel,dataSkel);
        compareEquals(userSkel,dataSkel);
    }
    
    
    //not implemented yet
    private void joinAbove() {
    }
    
    private void compare(List<String> userKeys,List<String> dataKeys) {
        for(String k : userKeys) {
            if(!dataKeys.remove(k)) {
                throw new MediatorAssertionError(ErrorType.KEYNOTEXISTS,"Given the skeleton the:"+k+" doesn't exist");
            }
        }
        if(!dataKeys.isEmpty()) {
            String types = listToString(dataKeys);
            throw new MediatorAssertionError(ErrorType.KEYSNOTFOUND,"types didn't found on data given the skeleton:"+types);
        }
    }
    
    
    private void compareEquals(JsonStructure userSkel,JsonStructure dataSkel) {
        if(!userSkel.toString().equals(dataSkel.toString())) {
            throw new MediatorAssertionError(ErrorType.EQUALITY,"Given Skeleton String didn't match the generated skeleton from data");
        }
    }
    
    private List<String> getKeysFlat(JsonStructure json,List<String> keys) {
        if(json instanceof JsonObject) {
             getKeysFlat((JsonObject)json,keys);
        } else if(json instanceof JsonArray) {
            getKeysFlat((JsonArray)json,keys);
        }
        return keys;
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
   
    
    private String listToString(List<String> list) {
        String listString = "";
        for (String s : list) {
            listString += s + ",";
        }
        return listString;
    }

    
 
}

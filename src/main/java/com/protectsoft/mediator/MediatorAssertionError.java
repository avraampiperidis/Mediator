/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.mediator;

import javax.json.Json;

/**
 *
 * @author piper
 */
public class MediatorAssertionError extends AssertionError {
    
    private ErrorType code;
    
    public MediatorAssertionError() {
        super("");
    }
    
    public MediatorAssertionError(String message) {
        super(message);
    }
    
    public MediatorAssertionError(ErrorType code,String message) {
        super(message);
        this.code = code;
    }

    /**
     * @return the code
     */
    public ErrorType getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(ErrorType code) {
        this.code = code;
    }
    
    @Override
    public String toString() {
        return Json.createObjectBuilder().add("type",this.getClass().getCanonicalName())
                .add("code", this.code.ordinal())
                .add("message",super.getMessage()).build().toString();
    }
}

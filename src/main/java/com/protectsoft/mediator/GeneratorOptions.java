/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.mediator;

/**
 *
 * @author piper
 */
public class GeneratorOptions {
    
    private ARRAY arrayOption;
    
    public GeneratorOptions() {
        this.arrayOption = ARRAY.ALL;
    }
    
    public GeneratorOptions (ARRAY arrOption) {
        this.arrayOption = arrOption;
    }

    /**
     * @return the arrayOption
     */
    public ARRAY getArrayOption() {
        return arrayOption;
    }

    /**
     * @param arrayOption the arrayOption to set
     */
    public void setArrayOption(ARRAY arrayOption) {
        this.arrayOption = arrayOption;
    }
    
    
    
}

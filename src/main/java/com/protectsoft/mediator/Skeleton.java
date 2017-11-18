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
class Skeleton {
    
    private GeneratorOptions options;
    private String data;
    private String skeleton;
    public boolean isDataArray;
    
    public Skeleton(GeneratorOptions options) {
        this.options = options;
    }

    /**
     * @return the options
     */
    public GeneratorOptions getOptions() {
        return options;
    }
    

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the skeleton
     */
    public String getSkeleton() {
        return skeleton;
    }

    /**
     * @param skeleton the skeleton to set
     */
    public void setSkeleton(String skeleton) {
        this.skeleton = skeleton;
    }

    
}

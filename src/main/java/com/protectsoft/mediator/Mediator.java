/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.mediator;

/**
 *
 * @author avraam
 */
public class Mediator {

    private Skeleton skeleton;
    
    private Mediator(String data) {
        skeleton = new Skeleton(new GeneratorOptions());
        skeleton.setData(data);
    }
    
   
    public static Mediator given(String data) {
        return new Mediator(data);
    }
    

    public Mediator withSkel(String skeleton) {
        this.skeleton.setSkeleton(skeleton);
        return this;
    }
    
    
    public Mediator withArray(ARRAY opt) {
        skeleton.getOptions().setArrayOption(opt);
        return this;
    }
    
    
    public String generateSkeleton() {
        return new SkeletonBuilder(skeleton).build();
    }
    
    public void join() {
        SkeletonComparator.join(skeleton);
    }
    
    public void joinExact() {
        SkeletonComparator.joinExact(skeleton);
    }

    public void joinAbove() {
        SkeletonComparator.joinAbove(skeleton);
    }
    
    
    
}

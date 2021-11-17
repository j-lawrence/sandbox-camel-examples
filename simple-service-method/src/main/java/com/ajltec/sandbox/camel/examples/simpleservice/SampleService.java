package com.ajltec.sandbox.camel.examples.simpleservice;

public class SampleService {
    public void execute(String message){
        System.out.println("Service method received message: " + message);
    }
}

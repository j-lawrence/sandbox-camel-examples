package com.ajltec.sandbox.camel.examples.helloworld;

import org.apache.camel.builder.RouteBuilder;

public class HelloWorldRoute extends RouteBuilder {
    public void configure() throws Exception {
        System.out.println("Hello World Camel");
    }
}

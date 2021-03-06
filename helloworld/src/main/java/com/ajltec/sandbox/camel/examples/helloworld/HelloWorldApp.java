package com.ajltec.sandbox.camel.examples.helloworld;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class HelloWorldApp {
    public static void main(String[] args) throws Exception {
        //Setting up camel context
        CamelContext camelContext = new DefaultCamelContext();
        //Adding Camel route
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                System.out.println("Hello World Camel");
            }
        });
        //starting context
        camelContext.start();
    }
}
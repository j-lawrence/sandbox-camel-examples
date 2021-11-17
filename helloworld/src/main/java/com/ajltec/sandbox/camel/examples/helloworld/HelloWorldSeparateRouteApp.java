package com.ajltec.sandbox.camel.examples.helloworld;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class HelloWorldSeparateRouteApp {
    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new HelloWorldRoute());
        camelContext.start();
    }
}

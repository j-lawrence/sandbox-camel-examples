package com.ajltec.sandbox.camel.examples.helloworld;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class HelloWorldSeparateRouteApp {
    public static void main(String[] args) throws Exception {
        //Setting up camel context
        CamelContext camelContext = new DefaultCamelContext();
        //Adding Camel route
        camelContext.addRoutes(new HelloWorldRoute());
        //starting context
        camelContext.start();
    }
}

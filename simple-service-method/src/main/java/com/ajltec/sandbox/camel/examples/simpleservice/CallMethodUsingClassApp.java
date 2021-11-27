package com.ajltec.sandbox.camel.examples.simpleservice;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CallMethodUsingClassApp {
    public static void main(String[] args) throws Exception {
        //Setting up camel context
        CamelContext context = new DefaultCamelContext();
        //Adding Camel route
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:endpoint")
                        .to("class:com.ajltec.sandbox.camel.examples.simpleservice.SampleService?method=execute");
            }
        });
        //starting context
        context.start();
        ProducerTemplate producerTemplate = context.createProducerTemplate();
        //making synchronous call
        producerTemplate.sendBody("direct:endpoint", "Hello Camel");
    }
}

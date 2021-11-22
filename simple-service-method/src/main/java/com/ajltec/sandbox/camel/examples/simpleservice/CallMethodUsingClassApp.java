package com.ajltec.sandbox.camel.examples.simpleservice;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CallMethodUsingClassApp {
    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:endpoint")
                        .to("class:com.ajltec.sandbox.camel.examples.simpleservice.SampleService?method=execute");
            }
        });
        context.start();
        ProducerTemplate producerTemplate = context.createProducerTemplate();
        producerTemplate.sendBody("direct:endpoint", "Hello Camel");
    }
}

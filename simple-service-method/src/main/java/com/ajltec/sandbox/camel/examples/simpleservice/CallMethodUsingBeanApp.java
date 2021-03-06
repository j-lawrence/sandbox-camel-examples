package com.ajltec.sandbox.camel.examples.simpleservice;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.SimpleRegistry;

import java.util.HashMap;
import java.util.Map;

public class CallMethodUsingBeanApp {
    public static void main(String[] args) throws Exception {

        SampleService sampleService = new SampleService();
        //Setting up Registry
        SimpleRegistry simpleRegistry =  new SimpleRegistry();
        Map<Class<?>, Object> map = new HashMap();
        map.put(SampleService.class, sampleService);
        simpleRegistry.put("sampleService", map);
        //Setting up camel context with Registry
        CamelContext context = new DefaultCamelContext(simpleRegistry);
        //Adding Camel route
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:endpoint")
                        .to("bean:sampleService?method=execute");
            }
        });
        //starting context
        context.start();
        ProducerTemplate producerTemplate = context.createProducerTemplate();
        //making synchronous call
        producerTemplate.sendBody("direct:endpoint", "Hello Camel");
    }
}

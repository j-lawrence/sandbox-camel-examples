package com.ajltec.sandbox.camel.examples.prodconsprocessor;

import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ProdAndConsumerProcessorApp {
    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:endpoint").process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        Object message = exchange.getIn().getBody();
                        System.out.println("Processor invoked. Message is: " + message);
                        exchange.getIn().setBody("Hello World by AJL");
//                        exchange.getMessage().setBody("Hello World by AJL");
                    }
                })
                        .to("seda:endpoint");
            }
        });
        context.start();
        ProducerTemplate producerTemplate = context.createProducerTemplate();
        String msg = "Hello Everyone";
        System.out.println("Sending message: " + msg);
        producerTemplate.sendBody("direct:endpoint", msg);

        ConsumerTemplate consumerTemplate = context.createConsumerTemplate();
        String message = consumerTemplate.receiveBody("seda:endpoint", String.class);
        System.out.println("Message Received: " + message);
    }
}

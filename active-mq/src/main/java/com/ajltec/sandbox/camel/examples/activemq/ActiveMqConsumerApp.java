package com.ajltec.sandbox.camel.examples.filecopy;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.Arrays;

public class ActiveMqConsumerApp {

    public static void main(String[] args) throws Exception {

        CamelContext context = new DefaultCamelContext();
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        connectionFactory.setTrustedPackages(Arrays.asList("com.ajltec.sandbox.camel.examples"));
        context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("activemq:queue:my_queue").log("${body}").to("seda:end");
            }
        });
        context.start();
        ConsumerTemplate consumerTemplate = context.createConsumerTemplate();
        consumerTemplate.receiveBody("seda:end");

    }
}

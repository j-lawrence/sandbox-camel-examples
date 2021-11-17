package com.ajltec.sandbox.camel.examples.mysql;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.SimpleRegistry;

import java.util.HashMap;
import java.util.Map;

public class MysqlQueryDemoApp {
    public static void main(String[] args) throws Exception {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/camel_demo");
        dataSource.setUser("root");
        dataSource.setPassword("mypassword");

        SimpleRegistry registry = new SimpleRegistry();
        Map<Class<?>, Object> dataSourceMap = new HashMap();
        dataSourceMap.put(com.mysql.cj.jdbc.MysqlDataSource.class, dataSource);
        registry.put("myDataSource", dataSourceMap);

        CamelContext context = new DefaultCamelContext(registry);
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .to("jdbc:myDataSource")
                        .bean(new ResultHandler(), "printResult");
            }
        });

        context.start();
        ProducerTemplate producerTemplate = context.createProducerTemplate();
        producerTemplate.sendBody("direct:start", "select * from customer");
    }
}

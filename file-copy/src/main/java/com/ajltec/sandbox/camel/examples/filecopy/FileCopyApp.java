package com.ajltec.sandbox.camel.examples.filecopy;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FileCopyApp {

    public static void main(String[] args) throws Exception {
        final CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:file-copy/target/input_dir").to("file:file-copy/target/output_dir");
            }
        });
        //Running indefinitely so that files will be picked up as soon as it is dropped
        while(true){
            camelContext.start();
            //Sleeping 1 min to avoid overhead for CPU
            Thread.sleep(1000);
        }
    }

}

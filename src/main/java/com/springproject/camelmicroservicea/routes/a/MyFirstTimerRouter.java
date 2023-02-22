package com.springproject.camelmicroservicea.routes.a;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class MyFirstTimerRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        //queue- use timer
        //transformation
        //database -log
        //Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
        from("timer:first-timer")//start of queue
                .transform().constant("My Constant Message")
                .transform().constant(LocalDateTime.now())
                .to("log:first-timer");//database


    }
}

package com.springproject.camelmicroservicea.routes.a;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;


@Component
public class MyFirstTimerRouter extends RouteBuilder {
    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;
    @Autowired
    private SimpleLoggingProcessingComponent loggingComponent;
    @Override
    public void configure() throws Exception {
        //queue- use timer
        //transformation
        //database -log
        //Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]

        //processing- donot change body of message
        //transformation- changes body of message

        from("timer:first-timer")//start of queue
                //.transform().constant("My Constant Message")
                .bean(getCurrentTimeBean,"getCurrentTime")
                .bean(loggingComponent)
                .to("log:first-timer");//database


    }
}

@Component
class GetCurrentTimeBean {
    public String getCurrentTime() {
        return "Time now is " + LocalDateTime.now();
    }
}
@Component
class SimpleLoggingProcessingComponent {
    private Logger logger= (Logger) LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);
    public void process(String message) {
        logger.info("SimpleLoggingProcessComponent {}",message);
    }
}
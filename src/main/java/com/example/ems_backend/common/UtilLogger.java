package com.example.ems_backend.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UtilLogger {

    private Logger logger= LoggerFactory.getLogger(UtilLogger.class);

    public Logger createLogger(){
        return this.logger;
    }
}

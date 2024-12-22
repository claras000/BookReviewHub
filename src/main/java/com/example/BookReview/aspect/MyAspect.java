package com.example.BookReview.aspect;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Logger when review is created
 */
@Aspect
@Component
public class MyAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * create logger info
     */
    @After("execution(public * com.example.BookReview.controllers.ControllerReview.addReview(..))")
    public void afterEveryServiceAction(){
        logger.info("add Review");
    }
}

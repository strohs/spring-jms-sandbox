package com.cliff.jms;

import com.cliff.jms.config.JmsCommonConfig;
import com.cliff.jms.config.JmsConsumerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * Test app used to load an AppContext and then consume messages from the user queue
 * User: Cliff
 */
public class UserConsumerApp {

    private static final Logger logger = LoggerFactory.getLogger( UserConsumerApp.class );

    public static void main( String[] args ) throws IOException {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(
                JmsCommonConfig.class,
                JmsConsumerConfig.class );
        ctx.registerShutdownHook();

        //get the UserReceiver from the context
        UserReceiver userReceiver = ctx.getBean( UserReceiver.class );
        assertNotNull( userReceiver );
        logger.info( "waiting for user....." );
        System.in.read();
        ctx.close();
    }
}

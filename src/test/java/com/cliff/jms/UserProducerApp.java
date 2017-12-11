package com.cliff.jms;

import com.cliff.jms.config.JmsCommonConfig;
import com.cliff.jms.config.JmsProducerConfig;
import com.cliff.jms.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Sends messages to the user queue and reads messages from the confirmation queue
 *
 * User: Cliff
 */
public class UserProducerApp {

    private static final Logger logger = LoggerFactory.getLogger( UserProducerApp.class );

    public static void main( String[] args ) throws IOException {

        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(
                JmsCommonConfig.class, JmsProducerConfig.class
        );



        UserService userService = ctx.getBean( UserService.class );
        UserSender userSender = ctx.getBean( UserSender.class );
        assertNotNull( userService );
        assertNotNull( userSender );

        List<User> users = userService.getAllUsers();
        assertTrue( users.size() > 0 );

        for( User u : users ) {
            userSender.sendMessage( u );
        }

        logger.info( "user messages sent. waiting for confirmation...");
        System.in.read();
        ctx.close();
    }
}

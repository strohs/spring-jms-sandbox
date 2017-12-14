package com.cliff.jms;

import com.cliff.jms.domain.Confirmation;
import com.cliff.jms.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * MessageListener for the 'user' queue. This class also puts messages on the 'confirmation' queue
 * for each User message received
 * User: Cliff
 */
@Component
public class UserReceiver {

    private Logger logger = LoggerFactory.getLogger( UserReceiver.class );

    private static AtomicInteger id = new AtomicInteger();


    private ConfirmationSender confirmationSender;

    @Autowired
    public UserReceiver( ConfirmationSender confirmationSender ) {
        this.confirmationSender = confirmationSender;
    }


    @JmsListener(destination = "com.queue.user")
    public void receiveMessage( User receivedUser, Message message ) {
        logger.info( " >>>>>>>>>> Original received message: " + message );
        logger.info( " >>>>>>>>>> Received user: " + receivedUser );

        confirmationSender.sendMessage( new Confirmation( id.incrementAndGet(),
                "User " + receivedUser.getLastName() + " received" ) );

    }
}

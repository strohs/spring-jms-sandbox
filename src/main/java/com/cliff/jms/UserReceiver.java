package com.cliff.jms;

import com.cliff.jms.domain.Confirmation;
import com.cliff.jms.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * MessageListener for the 'user' queue. This class also puts messages on the 'confirmation' queue
 * for each User message received
 * User: Cliff
 */
@Component
public class UserReceiver implements MessageListener {

    private Logger logger = LoggerFactory.getLogger( UserReceiver.class );
    private static AtomicInteger id = new AtomicInteger();

    private MessageConverter messageConverter;

    private ConfirmationSender confirmationSender;

    @Autowired
    public UserReceiver( MessageConverter messageConverter, ConfirmationSender confirmationSender ) {
        this.messageConverter = messageConverter;
        this.confirmationSender = confirmationSender;
    }

    @Override
    public void onMessage( Message message ) {
        try {
            //convert message to User
            User receivedUser = (User) messageConverter.fromMessage(message);
            logger.info(" >> Received user: " + receivedUser);
            //send a confirmation message
            confirmationSender.sendMessage(new Confirmation(id.incrementAndGet(), "User " + receivedUser.getLastName() + " received."));
        } catch (JMSException e) {
            logger.error("something went wrong ", e);
        }
    }
}

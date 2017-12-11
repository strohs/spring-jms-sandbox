package com.cliff.jms;

import com.cliff.jms.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.Session;

/**
 * publishes user messages to the user queue
 * User: Cliff
 */
@Component
public class UserSender {

    private JmsTemplate jmsTemplate;

    private Destination userQueue;

    @Autowired
    public UserSender( JmsTemplate jmsTemplate, Destination userQueue ) {
        this.jmsTemplate = jmsTemplate;
        this.userQueue = userQueue;
    }

    /**
     * send a user message to the jmsTemplate default destination
     * @param user
     */
    public void sendMessage( final User user ) {
        jmsTemplate.send( userQueue, (Session session) -> session.createObjectMessage( user ) );
    }
}

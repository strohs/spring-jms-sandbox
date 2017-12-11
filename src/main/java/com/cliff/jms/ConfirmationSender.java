package com.cliff.jms;

import com.cliff.jms.domain.Confirmation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * uses jmsTemplate to send Confirmation messages to the confirmation queue
 * User: Cliff
 */
@Component
public class ConfirmationSender {

    private JmsTemplate jmsTemplate;

    private Destination confirmationQueue;

    @Autowired
    public ConfirmationSender( JmsTemplate jmsTemplate, Destination confirmationQueue ) {
        this.jmsTemplate = jmsTemplate;
        this.confirmationQueue = confirmationQueue;
    }

    /**
     * semd an object message using jmsTemplate.send( MessageCreator )
     * @param confirmation
     */
    public void sendMessage( final Confirmation confirmation ) {
        jmsTemplate.send( confirmationQueue, new MessageCreator() {
            @Override
            public Message createMessage( Session session ) throws JMSException {
                return session.createObjectMessage( confirmation );
            }
        } );
    }
}

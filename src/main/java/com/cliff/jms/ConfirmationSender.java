package com.cliff.jms;

import com.cliff.jms.domain.Confirmation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * uses jmsTemplate to send Confirmation messages to the confirmation queue
 * User: Cliff
 */
@Component
public class ConfirmationSender {

    private static Logger logger = LoggerFactory.getLogger( ConfirmationSender.class );

    private JmsTemplate jmsTemplate;

    private Queue confirmationQueue;

    @Autowired
    public ConfirmationSender( JmsTemplate jmsTemplate, Queue confirmationQueue ) {
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

package com.cliff.jms;

import com.cliff.jms.domain.Confirmation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Creates a MessageListener for the 'confirmation' queue
 * User: Cliff
 */
@Component
public class ConfirmationReceiver {

    private Logger logger = LoggerFactory.getLogger( ConfirmationReceiver.class );



    @JmsListener( destination = "com.queue.confirmation")
    public void receiveConfirmation( Confirmation confirmation ) {
        logger.info(">>>>>>>>> Received Confirmation: " + confirmation );

    }
}

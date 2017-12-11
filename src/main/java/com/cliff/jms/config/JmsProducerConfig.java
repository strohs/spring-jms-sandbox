package com.cliff.jms.config;

import com.cliff.jms.ConfirmationReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 * Configures a jmsTemplate that produces to the 'user' queue
 * Configures a DefaultMessageListenerContainer that listens on the 'confirmation' queue
 * User: Cliff
 */
@Configuration
public class JmsProducerConfig {

    private JmsCommonConfig jmsCommonConfig;

    @Autowired
    public JmsProducerConfig( JmsCommonConfig jmsCommonConfig ) {
        this.jmsCommonConfig = jmsCommonConfig;
    }



//    @Bean
//    public ConfirmationReceiver confirmationReceiver(){
//        return new ConfirmationReceiver();
//    }

    @Bean
    public DefaultMessageListenerContainer containerListener( ConfirmationReceiver confirmationReceiver ) {
        DefaultMessageListenerContainer listener = new DefaultMessageListenerContainer();
        listener.setConnectionFactory( jmsCommonConfig.connectionFactory() );
        listener.setDestination( jmsCommonConfig.confirmationQueue() );
        listener.setMessageListener( confirmationReceiver );
        return listener;
    }
}

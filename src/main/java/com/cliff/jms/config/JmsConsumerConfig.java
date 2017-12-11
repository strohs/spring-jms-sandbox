package com.cliff.jms.config;

import com.cliff.jms.UserReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 * Configure a JMS Consumer that will consume messages from the user queue
 * and produce messages to the 'confirmation' queue
 * User: Cliff
 */
@Configuration
public class JmsConsumerConfig {

    private JmsCommonConfig jmsCommonConfig;

    @Autowired
    public JmsConsumerConfig( JmsCommonConfig jmsCommonConfig ) {
        this.jmsCommonConfig = jmsCommonConfig;
    }

    //UserReceiver is the message listener
//    @Bean
//    public UserReceiver userReceiver() {
//        return new UserReceiver();
//    }

    @Bean
    public DefaultMessageListenerContainer containerListener( UserReceiver userReceiver ) {
        DefaultMessageListenerContainer listenerContainer = new DefaultMessageListenerContainer();
        listenerContainer.setConnectionFactory( jmsCommonConfig.connectionFactory() );
        listenerContainer.setDestination( jmsCommonConfig.userQueue() );
        listenerContainer.setMessageListener( userReceiver );
        return listenerContainer;
    }


}

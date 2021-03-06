package com.cliff.jms.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import java.util.Arrays;

/**
 * Configure JMS Connection Factories, Destinations, and MessageConverters
 * User: Cliff
 */
@Configuration
@ComponentScan( basePackages = {"com.cliff.jms"})
public class JmsCommonConfig {

    private final String [] trustedPackages = {"com.cliff.jms.domain"};

    @Bean
    public ConnectionFactory nativeConnectionFactory() {
        ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
        cf.setBrokerURL( "tcp://localhost:61616" );
        //cf.setTrustAllPackages( true );
        cf.setTrustedPackages( Arrays.asList( trustedPackages ) );
        return cf;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        //wrap nativeConnectionFactory in Spring's CachingConnectionFactory
        CachingConnectionFactory ccf = new CachingConnectionFactory(  );
        ccf.setTargetConnectionFactory( nativeConnectionFactory() );
        return ccf;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory( connectionFactory() );
        //jmsTemplate.setDefaultDestination(jmsCommonConfig.userQueue());
        return  jmsTemplate;
    }

    //create the 'user' queue
    @Bean
    public Destination userQueue() {
        return new ActiveMQQueue( "com.queue.user" );
    }

    @Bean
    public Destination confirmationQueue() {
        return new ActiveMQQueue( "com.queue.confirmation" );
    }

    @Bean
    public MessageConverter converter() {
        return new SimpleMessageConverter();
    }
}

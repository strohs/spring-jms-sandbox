Spring JMS Sandbox
====================================
Sample Spring Framework 4 project for exploring Spring JMS functionality using ActiveMQ as a broker.

## Overview
This application demonstrates a simple publish/subscribe scenario using two queues: a 'user' queue and a 'confirmation' queue.
User objects are put onto a user queue, and a UserReceiver will consume them. The UserReceiver will also publish
a Confirmation message to the confirmation queue for each User message it consumes.

This application is run via two test classes:
* UserConsumerApp
  * Starts the UserReceiver bean that implements a MessageListener
    1. consumes a User message
    2. logs it to console
    3. publishes a Confirmation message to the confirmation queue
* UserProducerApp
  * publishes User messages to the user queue
  * starts the ConfirmationReceiver bean that logs any confirmation messages on the confirmation queue


* ConfirmationReceiver
  * A DefaultMessageListenerContainer that subscribes to the confirmation queue and logs confirmation messages
* UserReceiver
  * A DefaultMessageListenerContainer that does the following:
    1. listens to the user queue and logs any messages received
    2. publishes a confirmation message for each user message received

* ConfirmationSender
  * Uses JmsTemplate to send Confirmation messages to the confirmation queue
* UserSender
  * Uses JmsTemplate to send User messages to the user queue

### Spring/JMS features used
* JmsTemplate
* CachingConnectionFactory
* MessageListener with Spring's DefaultMessageListenerContainer
* Queues
* MessageConverter with Spring's SimpleMessageConverter
* ActiveMQ

### Requirements
A standalone ActiveMQ broker is required (this project used Version 5.15.2)
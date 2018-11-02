package com.example.demo.rabbitMQ.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class SubscribeFanoutRabbitMQConfiguration {
	
	private static final String SUBSCRIBE_FANOUT_EXCHANGE= "subscribe_fanout_exchange";	 
	private static final String SMS_QUEUE_NAME = "sms_queue";
	private static final String EMAIL_QUEUE_NAME = "email_queue";
	
	@Bean("fanoutExchange")
	public FanoutExchange fanoutExchange(){
        return new FanoutExchange(SUBSCRIBE_FANOUT_EXCHANGE, false, false);
    }
	
	@Bean("fanoutSmsQueue")
	public Queue smsQueue() {
		return new Queue(SMS_QUEUE_NAME, false, false, false, null);
	}
	
	@Bean("fanoutEmailQueue")
	public Queue emailQueue() {
		return new Queue(EMAIL_QUEUE_NAME, false, false, false, null);
	}
	
	private static final String ROUTE_NAME = "route";
	
	@Bean
    public Binding smsQueueExchangeBinding(FanoutExchange fanoutExchange, Queue fanoutSmsQueue){
        return BindingBuilder.bind(fanoutSmsQueue).to(fanoutExchange);
    }
    @Bean
    public Binding emailQueueExchangeBinding(FanoutExchange fanoutExchange, Queue fanoutEmailQueue){
        return BindingBuilder.bind(fanoutEmailQueue).to(fanoutExchange);
    }
    
    //绑定路由，只有DirectExchange才能绑定
//    @Bean
//    public Binding binding0b(DirectExchange directExchange, Queue autoDeleteQueue0) {
//      return BindingBuilder.bind(autoDeleteQueue0).to(directExchange).with("black");
//    }

}

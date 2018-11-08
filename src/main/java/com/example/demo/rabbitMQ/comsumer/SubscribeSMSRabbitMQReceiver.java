package com.example.demo.rabbitMQ.comsumer;

import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@RabbitListener(queues = "sms_queue")
@Component
public class SubscribeSMSRabbitMQReceiver {
	
	@RabbitHandler
	public void process(String message) {
		System.out.println("[SubscribeSMSRabbitMQReceiver] receive message:" + message + " at " + new Date());
	}
}

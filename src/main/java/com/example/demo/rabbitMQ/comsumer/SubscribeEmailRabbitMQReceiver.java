package com.example.demo.rabbitMQ.comsumer;

import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@RabbitListener(queues = "email_queue")
@Component
public class SubscribeEmailRabbitMQReceiver {
	
	@RabbitHandler
	public void process(String message) {
		System.out.println("[SubscribeEmailRabbitMQReceiver] receive message:" + message + " at " + new Date());
	}
}

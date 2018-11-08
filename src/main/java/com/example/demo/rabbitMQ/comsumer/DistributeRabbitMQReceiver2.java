package com.example.demo.rabbitMQ.comsumer;

import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@RabbitListener(queues = "distribute_queue_name")
@Component
public class DistributeRabbitMQReceiver2 {
	
	@RabbitHandler
	public void process(String message) {
		System.out.println("[DistributeRabbitMQReceiver2] receive message:" + message + " at " + new Date());
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package com.ecommerce.emailservice.service;

import com.ecommerce.emailservice.event.OrderPlacedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    @KafkaListener(topics = "notificationTopic", groupId = "notificationId")
    public void listen(OrderPlacedEvent notification) {
        sendEmail(notification);
        System.out.println("Order placed with id " + notification.getOrderNumber());
    }

    private void sendEmail(OrderPlacedEvent orderEvent) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("csumit408@yahoo.com");
        message.setSubject("Order Confirmation");
        message.setText("Thank you for your order! Your order ID is " + orderEvent.getOrderNumber());

        javaMailSender.send(message);

        System.out.println("Email sent for order: " + orderEvent.getOrderNumber());
    }
}

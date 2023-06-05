package com.niit.pushnotification.samplecodeforusermoviepleasedeletelater;

import com.niit.pushnotification.dto.NotificationDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("")
public class Controller {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange directExchange;

    //Notifications should be stored in user movie service and immediately notificationDTO should be sent to PushNotification service
    //When notification  is delivered, NotificationDeliveredDTO will be sent to UserMovie Service with rabbitMq on notification-delivered-queue, then the corresponding notification should be deleted in UserMovie service
    //Asynchronous notification deletion is still yet to be conceptualized

    //Message production from UserMovie microservice example
    @PostMapping("/api/v1/test-message")
    public ResponseEntity<?> run(@RequestBody NotificationDTO notificationDTO) throws Exception {
        LocalDateTime localDateTime = LocalDate.parse(notificationDTO.getMovie().getReleaseDate()).atStartOfDay();
        //dont send json object send notificationDTO with delivery-date attribute set as release date, please change release date format to LocalDate.toString() format
        this.rabbitTemplate.convertAndSend(this.directExchange.getName(), "notification-consume-routing", notificationDTO, message -> {
            message.getMessageProperties().setHeader("delivery-date", localDateTime.toString());
            return message;
        });
        System.out.println("sent:" + notificationDTO.toString() + " time:" + Instant.now());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

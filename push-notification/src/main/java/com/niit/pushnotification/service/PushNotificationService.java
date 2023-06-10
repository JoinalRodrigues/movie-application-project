package com.niit.pushnotification.service;

import com.niit.pushnotification.dto.NotificationDTO;
import com.rabbitmq.client.Channel;

public interface PushNotificationService {
    void consumeNotification(NotificationDTO notificationDTO, Channel channel, long tag, String deliveryDate) throws Exception;
}

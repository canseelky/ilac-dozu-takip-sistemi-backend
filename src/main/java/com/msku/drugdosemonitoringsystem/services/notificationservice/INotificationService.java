package com.msku.drugdosemonitoringsystem.services.notificationservice;

import com.msku.drugdosemonitoringsystem.controllers.response.NotificationResponse;
import com.msku.drugdosemonitoringsystem.enums.NotifyType;

import java.util.UUID;

public interface INotificationService {

    void sendNotification(NotificationResponse notificationResponse, String oneSignalPlayerId, UUID doctorId, NotifyType notifyType);
}

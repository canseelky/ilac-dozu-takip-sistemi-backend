package com.msku.drugdosemonitoringsystem.controllers.response;

import com.msku.drugdosemonitoringsystem.enums.NotifyType;
import lombok.Data;

@Data
public class NotificationResponse {
    private String messageTr;
    private String titleTr;
    private NotifyType type;
    private String messageEn;
    private String titleEn;
    private Long notificationId;

    public NotificationResponse(String messageTr, String titleTr, NotifyType type, String messageEn, String titleEn, Long notificationId) {
        this.messageTr = messageTr;
        this.titleTr = titleTr;
        this.type = type;
        this.messageEn = messageEn;
        this.titleEn = titleEn;
        this.notificationId = notificationId;
    }
}

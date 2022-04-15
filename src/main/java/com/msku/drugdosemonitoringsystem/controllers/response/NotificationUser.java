package com.msku.drugdosemonitoringsystem.controllers.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NotificationUser {
    private Long id;
    private String type;
    private String content;
    private LocalDate date;



}

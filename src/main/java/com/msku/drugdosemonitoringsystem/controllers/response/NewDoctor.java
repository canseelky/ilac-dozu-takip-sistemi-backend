package com.msku.drugdosemonitoringsystem.controllers.response;

import com.msku.drugdosemonitoringsystem.enums.NotifyType;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class NewDoctor {
    private String notifyType = "NEWDOCTOR";
    private UUID doctorId;
}

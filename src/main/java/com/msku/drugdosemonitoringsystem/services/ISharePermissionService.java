package com.msku.drugdosemonitoringsystem.services;

import com.msku.drugdosemonitoringsystem.controllers.response.DefaultResponse;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.UUID;

public interface ISharePermissionService {

    DefaultResponse givePermissionToDoctor(@RequestParam UUID doctorId, @RequestParam UUID patientId);
    DefaultResponse removePermissionFromDoctor (@RequestParam UUID doctorId,@RequestParam UUID patientId);


}

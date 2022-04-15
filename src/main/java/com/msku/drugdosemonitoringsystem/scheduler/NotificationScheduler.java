package com.msku.drugdosemonitoringsystem.scheduler;

import com.msku.drugdosemonitoringsystem.controllers.response.NotificationResponse;
import com.msku.drugdosemonitoringsystem.entities.Doctor;
import com.msku.drugdosemonitoringsystem.entities.Drug;
import com.msku.drugdosemonitoringsystem.entities.Notification;
import com.msku.drugdosemonitoringsystem.entities.PatientHasDrug;
import com.msku.drugdosemonitoringsystem.enums.NotifyType;
import com.msku.drugdosemonitoringsystem.repositories.DoctorRepo;
import com.msku.drugdosemonitoringsystem.repositories.NotificationRepo;
import com.msku.drugdosemonitoringsystem.repositories.PatientHasDrugRepo;
import com.msku.drugdosemonitoringsystem.repositories.PatientRepo;
import com.msku.drugdosemonitoringsystem.services.notificationservice.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NotificationScheduler {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private PatientHasDrugRepo patientHasDrugRepo;

    @Autowired
    private INotificationService mNoticationService;

    @Autowired
    private NotificationRepo mNotificationRepo;

    @Autowired
    private DoctorRepo mDoctorRepo;

//8:30 every day.
    @Scheduled(cron="0 30 8 * * *", zone="Europe/Istanbul")
    public void sendNotification(){
        try{
            List<PatientHasDrug>  patientHasDrugs=
                    patientHasDrugRepo.findAll()
                            .stream()
                            .filter(item->(item.getNextNotifyDate().equals(LocalDate.now()))).collect(Collectors.toList());
            patientHasDrugs.forEach(item->{

                String MESSAGE_TR =item.getDrug().getDrugName() + "ilacı için lütfen boy/kilo verilerinizi güncelleyiniz.";
                String MESSAGE_EN = "Please update weight/height data:  "+ item.getDrug().getDrugName();

                Notification notificationEntity = new Notification();
                notificationEntity.setPatient(item.getPatient());
                Doctor doctor = mDoctorRepo.getById(item.getAddedby());
                notificationEntity.setDoctor(doctor);
                notificationEntity.setNotifyDate(LocalDate.now());
                PatientHasDrug drug = patientHasDrugRepo.findPatientHasDrugById(item.getId());
                drug.setNextNotifyDate(LocalDate.now().plusDays(drug.getUpdateFeq()));
                notificationEntity.setNotificationType(NotifyType.UPDATE_DATA.toString());
                notificationEntity.setContent(item.getDrug().getDrugName());
                mNotificationRepo.save(notificationEntity);
                patientHasDrugRepo.save(drug);
                NotificationResponse notification = new NotificationResponse(MESSAGE_TR,MESSAGE_TR,NotifyType.NEWDOSE,MESSAGE_EN,MESSAGE_EN,notificationEntity.getId());
                mNoticationService.sendNotification(notification,item.getPatient().getOneSignalId(),null,NotifyType.NEWDOSE);

            });
        }catch (Exception e){
            System.out.println("err"+e.getMessage());
        }

    }


}

package com.msku.drugdosemonitoringsystem.repositories;

import com.msku.drugdosemonitoringsystem.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {
    List<Notification> findNotificationByPatientIdOrderByNotifyDateAsc(UUID patientId);
    List<Notification> findNotificationByDoctorIdOrderByNotifyDateAsc(UUID doctorId);


}

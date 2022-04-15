package com.msku.drugdosemonitoringsystem.services;


import com.msku.drugdosemonitoringsystem.controllers.response.*;
import com.msku.drugdosemonitoringsystem.entities.*;
import com.msku.drugdosemonitoringsystem.enums.NotifyType;
import com.msku.drugdosemonitoringsystem.enums.ResponseTypes;
import com.msku.drugdosemonitoringsystem.repositories.*;
import com.msku.drugdosemonitoringsystem.services.notificationservice.INotificationService;
import com.msku.drugdosemonitoringsystem.utils.CalculateNewDosage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ImplPatientService implements IPatientService{

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private PatientHasDrugRepo patientHasDrugRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private DrugRepo drugRepo;

    @Autowired
    private PatientHasDoctorRepo mPatientDoctorRepo;
    
    @Autowired
    private INotificationService mNotificationService;

    @Autowired
    private NotificationRepo mNotificationRepo;

    @Override
    @Transactional
    public DefaultResponse updateData(Double weight, Double height,UUID patientId) {
        try{
            Patient patient = patientRepo.getById(patientId);
            List<PatientHasDrug> listOfDrugs =  patientHasDrugRepo.findAllByPatientId(patientId);
            LocalDate today = LocalDate.now();
            Double weight1 = weight != 0 ? weight : patient.getWeight();
            Double height1 = height != 0 ? height : patient.getHeight();
            patient.setWeight(weight1);
            patient.setHeight(height1);

            listOfDrugs.stream().forEach(drug -> {
                if(drug.getNextNotifyDate().equals(today) ||
                        drug.getNextNotifyDate().isBefore(today)){
                    System.out.println("isBİİİf"+ drug.getNextNotifyDate().isBefore(today));
                    PatientHistory patientHistory = new PatientHistory();
                    patientHistory.setPatient(patient);
                    Drug drug2 = drugRepo.getById(drug.getId());
                    patientHistory.setDrug(drug2);
                    Doctor doctor = doctorRepo.getById(drug.getAddedby());
                    Formula formula = doctor.getFormula();
                    Double heightForCalc = height != null ? height : patient.getHeight();;
                    System.out.println("adsaass"+drug);
                    CalculateNewDosage newDosage = new CalculateNewDosage(formula.getName(),heightForCalc,weight,drug.getBase_dose());
                    patientHistory.setDose(newDosage.calculateDose());
                    patientHistory.setBsa(newDosage.getBsa());
                    drug.setNextNotifyDate(LocalDate.now().plusDays(drug.getUpdateFeq()));
                    patientHasDrugRepo.save(drug);
                    Notification notification = new Notification();
                    notification.setNotifyDate(LocalDate.now());
                    notification.setDoctor(doctor);
                    notification.setNotificationType(NotifyType.NEWDOSE.toString());
                    notification.setContent(patient.getName() + " " + patient.getSurname() +"-"+drug2.getDrugName()+"-"+newDosage.calculateDose());

                    mNotificationRepo.save(notification);
                    String MESSAGE_EN = "Patient " + patient.getName() + " " + patient.getSurname() + " has taken " + drug.getDrug().getDrugName() + " dose " + newDosage.getDose();
                    String MESSAGE_TR = patient.getName()+ patient.getSurname() + "-"+drug.getDrug().getDrugName()+" için yeni doz hesaplandı! ";

                    NotificationResponse notificationResponse = new NotificationResponse(
                            MESSAGE_TR,
                            MESSAGE_TR,
                            NotifyType.NEWDOSE,
                            MESSAGE_EN,
                            MESSAGE_EN,
                            notification.getId()
                    );

                    mNotificationService.sendNotification(notificationResponse,doctor.getOnesignalid(),null,NotifyType.NEWDOSE);

                }

                    });
            patientRepo.save(patient);

            return new DefaultResponse(ResponseTypes.SUCCESS, "Weight updated successfully",null);
        }catch (Exception e){
            System.out.println("EDD"+e.toString());
            return new DefaultResponse(ResponseTypes.ERROR, "Weight update failed",null);
        }
    }

    @Override
    public void updateHeight(Double height, UUID patientId) {

    }


    @Override
    public DefaultResponse<Patient> personalInfo(UUID patientId) {
        try{
            Patient patient = patientRepo.getById(patientId);
            return new DefaultResponse<Patient>(ResponseTypes.SUCCESS, "Personal info fetched successfully",patient);

        }catch (Exception e){
            System.out.println("E"+e.toString());
            return new DefaultResponse<Patient>(ResponseTypes.ERROR, "Personal info fetch failed",null);
        }

    }


    @Override
    public void resetPassword(String oldPassword, String newPassword) {

    }



    @Override
    public List<Patient> getAll() {
        List<Patient>  patients = patientRepo.findAll();
        return patients;
    }

    @Override
    public DefaultResponse setNotificationId(String notificationId, UUID patientId) {
        try{
            Patient patient = patientRepo.getById(patientId);
            patient.setOneSignalId(notificationId);
            patientRepo.save(patient);
            return new DefaultResponse(ResponseTypes.SUCCESS, "Notification id successfully set",null);
        }
        catch (Exception e){
            return new DefaultResponse(ResponseTypes.ERROR, "Notification id could not be set",null);
        }
    }

    @Override
    public Patient getPatientByEmail(String email) {
       Patient patient = patientRepo.findPatientByEmail(email);
        return patient;
    }

    @Override
    public DefaultResponse<List<HistoryResponse>> history(UUID patientId) {
        try{
            Patient patient = patientRepo.getById(patientId);
            List<PatientHistory> patientHistories = patient.getHistoryPatient();
            List<HistoryResponse> response = patientHistories.stream().map(patientHistory -> {
                           HistoryResponse historyResponse = new HistoryResponse();
                           historyResponse.setDate(patientHistory.getDate());
                           historyResponse.setDrugName(patientHistory.getDrug().getDrugName());
                           Doctor doctor = doctorRepo.getById(patientHistory.getAddedby());
                           historyResponse.setDoctorFullName(doctor.getName()+" "+doctor.getSurname());
                           historyResponse.setDose(patientHistory.getDose());
                           historyResponse.setHospital(patientHistory.getHospitalName());
                           historyResponse.setBsa(patientHistory.getBsa());

                           return historyResponse;

                              }).collect(Collectors.toList());
            return new DefaultResponse<List<HistoryResponse>>(ResponseTypes.SUCCESS, "History fetched successfully",response);

        }
        catch (Exception e){
            System.out.println("E"+e.toString());
            return new DefaultResponse<List<HistoryResponse>>(ResponseTypes.ERROR, "History fetch failed",null);
        }

    }

    @Override
    public Patient savePatient(Patient patient) {
        try{
           Patient patient_saved = patientRepo.save(patient);
           return patient_saved;
        }catch (Exception e){
            System.out.println("E"+e.toString());
            return null;
        }
    }

    @Override
    public DefaultResponse getDoctors(UUID patientId) {
        try{
            List<PatientHasDoctor> doctors =  mPatientDoctorRepo.findPatientHasDoctorByPatientId(patientId);
            List<DoctorInfo> reponse = doctors.stream().map(patientHasDoctor -> {
                Doctor doctor = patientHasDoctor.getDoctor();
                return new DoctorInfo(doctor.getId(),doctor.getName(),doctor.getSurname(),doctor.getHospital().getName(),doctor.getGender(),patientHasDoctor.getIsApproved()==1 ? true : false);
            }).collect(Collectors.toList());
            return new DefaultResponse<List<DoctorInfo>>(ResponseTypes.SUCCESS, "Doctors fetched successfully",reponse);

        }catch (Exception e){
            System.out.println("E"+e.toString());
            return new DefaultResponse(ResponseTypes.ERROR, "Doctors fetch failed",null);
        }

    }

    @Override
    public DefaultResponse getDoctor(UUID doctor) {

        try{
            Doctor doctor_saved = doctorRepo.getById(doctor);
            PatientHasDoctor doctors =  mPatientDoctorRepo.findPatientHasDoctorByDoctorId(doctor).get(0);
            DoctorInfo doctorInfo = new DoctorInfo(doctor_saved.getId(),doctor_saved.getName(),doctor_saved.getSurname(),doctor_saved.getHospital().getName(),doctor_saved.getGender(), doctors.getIsApproved() == 1);
            return new DefaultResponse(ResponseTypes.SUCCESS, "Doctor fetched successfully",doctorInfo);

        }catch (Exception e){
            System.out.println("E"+e.toString());
            return new DefaultResponse(ResponseTypes.ERROR, "Doctor fetch failed",null);
        }
    }

    @Override
    public DefaultResponse<List<NotificationUser>> getNotifications(UUID patientId) {

        try {
            List<Notification> notifications = mNotificationRepo.findNotificationByPatientIdOrderByNotifyDateAsc(patientId);

            List<NotificationUser> response = notifications.stream().map(notification -> {
                NotificationUser notificationUser = new NotificationUser();
                notificationUser.setId(notification.getId());
                if(notification.getNotificationType().equals(NotifyType.DRUG_ADDED.toString())){
                    String drugName = notification.getContent();
                    String doctorFullName = notification.getDoctor().getName() + " " + notification.getDoctor().getSurname();
                    String content = doctorFullName + "-" + drugName;
                    notificationUser.setContent(content);
                }
                if(notification.getNotificationType().equals(NotifyType.NEWDOCTOR.toString())){
                    String doctorFullName = notification.getDoctor().getName() + " " + notification.getDoctor().getSurname();
                    notificationUser.setContent(doctorFullName);
                }
                if(notification.getNotificationType().equals(NotifyType.UPDATE_DATA.toString())){
                    String drugName = notification.getContent();
                    notificationUser.setContent(drugName);
                }

                notificationUser.setDate(notification.getNotifyDate());
                notificationUser.setType(notification.getNotificationType());
                return notificationUser;

            }).collect(Collectors.toList());

            return new DefaultResponse<List<NotificationUser>>(ResponseTypes.SUCCESS, "Notifications fetched successfully",response);

        }
        catch (Exception e){
            System.out.println("E"+e.toString());
            return new DefaultResponse<List<NotificationUser>>(ResponseTypes.ERROR, "Notifications fetch failed",null);
        }
    }

}

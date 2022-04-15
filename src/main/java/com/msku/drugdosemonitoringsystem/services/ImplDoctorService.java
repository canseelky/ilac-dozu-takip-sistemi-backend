package com.msku.drugdosemonitoringsystem.services;

import com.msku.drugdosemonitoringsystem.controllers.response.*;
import com.msku.drugdosemonitoringsystem.entities.*;
import com.msku.drugdosemonitoringsystem.enums.NotifyType;
import com.msku.drugdosemonitoringsystem.enums.ResponseTypes;
import com.msku.drugdosemonitoringsystem.repositories.*;
import com.msku.drugdosemonitoringsystem.services.notificationservice.INotificationService;
import com.msku.drugdosemonitoringsystem.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ImplDoctorService implements IDoctorService {
    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private FormulaRepo formulaRepo;

    @Autowired
    private PatientHasDoctorRepo patientHasDoctorRepo;

    @Autowired
    private DrugRepo drugRepo;

    @Autowired
    private HistoryRepo historyRepo;

    @Autowired
    private INotificationService mNotificationService;

    @Autowired
    private NotificationRepo mNotificationRepo;

    @Autowired
    private IFormulaService mFormulaService;

    @Autowired
    private PaginationRepo paginationRepo;


    @Override
    public  DefaultResponse<Patient>  sendRequestToPatient(UUID patientId, UUID doctorId) {
        System.out.println("reqqqq"+doctorId+" "+patientId);
        try{
            Doctor doctor = doctorRepo.getById(doctorId);
            Patient patient = patientRepo.getById(patientId);
            System.out.println("reqqq"+patient);
            Boolean isAlredayInList = doctor.getDoctorPatientList().stream().filter(p -> p.getPatient().getId().equals(patientId)).findFirst().isPresent();
            System.out.println("reqqqAL"+isAlredayInList);
            if(isAlredayInList){
                return new DefaultResponse<>(ResponseTypes.ERROR, "Patient already in the list",null);
            }
            System.out.println("reqqqAL"+isAlredayInList);
            System.out.println("reqqq"+patient);
            PatientHasDoctor patientHasDoctor = new PatientHasDoctor();
            patientHasDoctor.setDoctor(doctor);
            patientHasDoctor.setPatient(patient);
            patientHasDoctor.setIsApproved(0);
            System.out.println("reqqq"+patient);
            patientHasDoctorRepo.save(patientHasDoctor);
            Notification notification = new Notification();
            notification.setPatient(patient);
            notification.setDoctor(doctor);
            notification.setIsRead(0);
            patient.addNotification(notification);
            System.out.println("reqqq"+patient);
            mNotificationRepo.save(notification);
            String MESSAGE_TR ="Doktor " + doctor.getName() + " " + doctor.getSurname() + " bilgilerinize erişim izni istiyor.";
            String MESSAGE_EN ="Doctor " + doctor.getName() + " " + doctor.getSurname() + " is requesting access to your information.";
            NotificationResponse notificationResponse = new NotificationResponse(
                    MESSAGE_TR,
                    MESSAGE_TR,
                    NotifyType.NEWDOCTOR,
                    MESSAGE_EN,
                    MESSAGE_EN,
                    notification.getId()

            );
            patientRepo.save(patient);

            mNotificationService.sendNotification(notificationResponse,patient.getOneSignalId(),doctorId,NotifyType.NEWDOCTOR);
            return new DefaultResponse<>(ResponseTypes.SUCCESS, "Request sent successfully",patient);

        }catch (Exception e){
                System.out.println("reqqqq"+e.getMessage());
                return new DefaultResponse<>(ResponseTypes.ERROR, "Request failed",null);
        }

    }



    @Override
    public List<DoctorPatientResponse> seePatients(UUID doctorid) {
        List<PatientHasDoctor> patientList = patientHasDoctorRepo.findPatientHasDoctorByDoctorId(doctorid);
        List<DoctorPatientResponse> patientsList = new ArrayList<>();
        patientList.forEach((pa -> {
                    DoctorPatientResponse doctorPatientResponse = new DoctorPatientResponse();
                    doctorPatientResponse.setId(pa.getPatient().getId());
                    doctorPatientResponse.setName(pa.getPatient().getName());
                    doctorPatientResponse.setSurname(pa.getPatient().getSurname());
                    doctorPatientResponse.setPhoneNumber(pa.getPatient().getPhoneNumber());;
                    List <Drug> drugs = new ArrayList<>();
                    pa.getPatient().getPatientHasDrugs().stream().forEach(p ->
                            drugs.add(p.getDrug()));
                    doctorPatientResponse.setDrugs(drugs);
                    patientsList.add(doctorPatientResponse);
                }));

        return patientsList;
    }


    @Override
    public List<PatientHistory> seePatientHistory(UUID patientId, UUID doctorId) {
        Patient patient = patientRepo.getById(patientId);
        for (PatientHasDoctor item : patient.getPatientDoctorList()) {
            if (item.getDoctor().getId() == doctorId ) {
                int isApproved = item.getIsApproved();
                return isApproved ==1 ?  patient.getHistory() : null;
            }
        }
        return null;
    }


    @Override
    public PatientSearchResponse searchPatient(String phoneNumber,UUID doctorId) {

        try{
            Patient patient = patientRepo.findPatientByPhoneNumber(phoneNumber);
            PatientHasDoctor isAlreadyExist = patientHasDoctorRepo.findPatientHasDoctorByPatientIdAndDoctorId(patient.getId(),doctorId);
            System.out.println("aaaa"+isAlreadyExist);

            if (patient != null) {
                AnonymizationPatient anonymizationPatient = new AnonymizationPatient(patient.getName(), patient.getSurname(), patient.getCity());
                PatientSearchResponse searchPatient = new PatientSearchResponse(patient.getId(),anonymizationPatient.getName(),anonymizationPatient.getSurname(),isAlreadyExist!=null?true:false,patient.getDateofbirth(),patient.getCity());
                return searchPatient;

            }

        }catch (Exception e){
            System.out.println("aaaa"+e.getMessage());
            return null;
        }
        return null;


    }

    @Override
    public List<Patient> getAll() {
        List<Patient> patients = patientRepo.findAll();
        return patients;
    }


    @Override
    public DefaultResponse<Drug> addDrugToPatient(UUID doctorid, UUID patientid, Long drugid, Integer updateFreq,Integer baseDose) {
        try {
            Drug drug = drugRepo.getById(drugid);
            Patient patient = patientRepo.getById(patientid);
            Doctor doctor = doctorRepo.getById(doctorid);
            for (PatientHasDoctor item : patient.getPatientDoctorList()) {

                if (item.getDoctor().getId().equals(doctorid)) {
                    int isApproved = item.getIsApproved();

                    if (isApproved == (1)) {
                        PatientHasDrug patientDrug = new PatientHasDrug();
                        patientDrug.setPatient(patient);
                        patientDrug.setDrug(drug);
                        patientDrug.setUpdateFeq(updateFreq);
                        patientDrug.setNextNotifyDate(LocalDate.now().plusDays(updateFreq));
                        patientDrug.setAddedby(doctorid);
                        patientDrug.setBase_dose(baseDose);
                        patient.addDrugToPatient(patientDrug);
                        double weight = patient.getWeight();
                        double height = patient.getHeight();
                        Long formulaId = doctor.getFormula().getId();
                        double bsa = mFormulaService.calculateBSA(formulaId, weight, height);
                        String hospitalName = doctor.getHospital().getName();
                        String hospitalCity = doctor.getHospital().getCity();
                        double dose = bsa * baseDose;
                        historyRepo.save(new PatientHistory(hospitalName, hospitalCity, bsa, dose, doctorid, drug, new Date(), patient));
                        patientRepo.save(patient);
                        Notification notification = new Notification();
                        notification.setPatient(patient);
                        //notification.setDoctor(doctor);
                        notification.setIsRead(0);
                        notification.setNotificationType(NotifyType.DRUG_ADDED.toString());
                        notification.setNotifyDate(LocalDate.now());
                        patient.addNotification(notification);
                        String MESSAGE_TR ="Doktor " +doctor.getName() +" "+ doctor.getSurname() +", "+drug.getDrugName()+ " adında yeni bir ilaç ekledi. ";
                        String MESSAGE_EN ="Doctor " + doctor.getName() + " " + doctor.getSurname() + " added new drug: " + drug.getDrugName() + ".";
                        NotificationResponse notificationResponse = new NotificationResponse(
                                MESSAGE_TR,
                                MESSAGE_TR,
                                NotifyType.DRUG_ADDED,
                                MESSAGE_EN,
                                MESSAGE_EN,
                                notification.getId()

                        );
                        notification.setContent(drug.getDrugName());
                        mNotificationRepo.save(notification);
                        mNotificationService.sendNotification(notificationResponse,patient.getOneSignalId(),null,NotifyType.DRUG_ADDED);
                        return new DefaultResponse<>(ResponseTypes.SUCCESS, "Drug added to patient", drug);
                    }

                }
            }
            return new DefaultResponse<>(ResponseTypes.ERROR, "You are not approved by patient", null);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("err"+e.getMessage());
            return new DefaultResponse<>(ResponseTypes.ERROR, "Drug not added to patient", null);

        }

    }

    @Override
    public Boolean checkEmailInUse(String email) {

        Doctor doctor = doctorRepo.findDoctorByEmail(email);
        if(doctor == null){
            return false;
        }
        return true;
    }

    @Override
    public Doctor save(Doctor doctor) {
        try {
            doctorRepo.save(doctor);
            return doctor;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public DefaultResponse setNotificationId(String notificationId, UUID doctorId) {
        try{
            Doctor doctor = doctorRepo.getById(doctorId);
            doctor.setOnesignalid(notificationId);
            doctorRepo.save(doctor);
            return new DefaultResponse(ResponseTypes.SUCCESS, "Notification id successfully set",null);
        }
        catch (Exception e){
            return new DefaultResponse(ResponseTypes.ERROR, "Notification id could not be set",null);
        }

    }

    @Override
    public DefaultResponse<Long> getSelectedFormula(UUID doctorId) {
        try{
            Doctor doctor = doctorRepo.getById(doctorId);
            Formula formula = doctor.getFormula();
            return new DefaultResponse<Long>(ResponseTypes.SUCCESS, "Formula successfully retrieved",formula.getId());

        }catch (Exception e){
            return new DefaultResponse<>(ResponseTypes.ERROR, "Formula did not successfully retrieved",null);
        }
    }

    @Override
    public DefaultResponse<Formula> setFormula(Long formulaId, UUID doctorId) {
        try{
            Doctor doctor = doctorRepo.getById(doctorId);
            Formula formula = formulaRepo.getById(formulaId);
            doctor.setFormula(formula);
            doctorRepo.save(doctor);
            return new DefaultResponse<>(ResponseTypes.SUCCESS, "Formula successfully set",formula);
        }catch (Exception e){
            return new DefaultResponse<>(ResponseTypes.ERROR, "Formula did not successfully set",null);
        }

    }

    @Override
    public List<DoctorPatientResponse> getPatientList(UUID doctorId, int pageNumber) {
        try{
            Pageable pageable = PageRequest.of(pageNumber, 10);
            List<PatientHasDoctor> response =  paginationRepo.findPatientHasDoctorByDoctorId(doctorId, pageable);
            List<DoctorPatientResponse>  listOfPatients = new ArrayList<>();

            response.stream().forEach(patientHasDoctor -> {
                Patient patient = patientHasDoctor.getPatient();
                DoctorPatientResponse doctorPatientResponse = new DoctorPatientResponse();
                doctorPatientResponse.setId(patient.getId());
                doctorPatientResponse.setName(patient.getName());
                doctorPatientResponse.setSurname(patient.getSurname());
                List <Drug> drugs = new ArrayList<>();
                patient.getPatientHasDrugs().stream().forEach(p ->
                        drugs.add(p.getDrug()));
                doctorPatientResponse.setDrugs(drugs);
                listOfPatients.add(doctorPatientResponse);
            });
            return listOfPatients;
        }catch (Exception e){
            return null;
        }


    }

    @Override
    public DefaultResponse<List<NotificationUser>> getNotifications(UUID doctorId) {
        try{
            List<Notification> notificationList = mNotificationRepo.findNotificationByDoctorIdOrderByNotifyDateAsc(doctorId);
            List<NotificationUser> notifications = new ArrayList<>();
            notificationList.stream().forEach(notification -> {
                NotificationUser notificationUser = new NotificationUser();
                notificationUser.setId(notification.getId());
                notificationUser.setContent(notification.getContent());
                notificationUser.setDate(notification.getNotifyDate());
                notificationUser.setType(notification.getNotificationType());
                notifications.add(notificationUser);
            });
            return new DefaultResponse<>(ResponseTypes.SUCCESS, "Notifications successfully retrieved",notifications);
        }catch (Exception e){
            return new DefaultResponse<>(ResponseTypes.ERROR, "Notifications did not successfully retrieved",null);
        }
    }


}

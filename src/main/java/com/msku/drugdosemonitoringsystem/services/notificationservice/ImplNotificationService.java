package com.msku.drugdosemonitoringsystem.services.notificationservice;

import com.msku.drugdosemonitoringsystem.controllers.response.NotificationResponse;
import com.msku.drugdosemonitoringsystem.enums.NotifyType;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.UUID;


@Service
public class ImplNotificationService<T> implements INotificationService {

    @Autowired
    private RestTemplate restTemplate;

    final String uri = "https://onesignal.com/api/v1/notifications";


    @Value("${ilacdozutakipsistemi.one.signal.app.id}")
    private static String appId;

    @Override
    public void sendNotification(NotificationResponse notificationResponse, String oneSignalPlayerId, UUID doctorId,NotifyType notifyType) {
        try{
            HttpEntity<?> NotificationRes;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            JSONObject request = new JSONObject();
            request.put("app_id", "6df40ed0-bbb3-47a2-bba0-2ef2c639e450");
            request.put("include_player_ids",  new JSONArray().put(oneSignalPlayerId));
            request.put("contents", new JSONObject().put("en", notificationResponse.getMessageEn()));
            JSONObject data = new JSONObject();
            data.put("doctorId", doctorId);
            data.put("notifyType", notifyType);
            data.put("announcementId", notificationResponse.getNotificationId());
            request.put("data", new JSONObject().put("data", data));
            NotificationRes = new HttpEntity<>(request.toString(), httpHeaders);
            ResponseEntity<String> response = restTemplate.postForEntity(uri, NotificationRes, String.class);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in sending notification"+e.getMessage());
        }

    }


}

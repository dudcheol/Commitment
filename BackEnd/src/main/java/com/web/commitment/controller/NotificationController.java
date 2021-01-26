package com.web.commitment.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.Lists;
import com.web.commitment.dao.FCMDao;
//import com.web.commitment.dao.List;
//import com.web.commitment.dao.LoginUser;
//import com.web.commitment.dao.NotificationDao;
//import com.web.commitment.dao.User;
//import com.web.commitment.dao.UserSession;
import com.web.commitment.dto.NotificationRequest;

import net.minidev.json.JSONObject;

@RestController
public class NotificationController {

//	@Autowired
//    private final FCMDao fCMDao;
//    
//    @Autowired
//    private NotificationDao notificationDao;
//
//    public NotificationController(FCMDao fCMDao) {
//		this.fCMDao = fCMDao;
//    }

//    @PostMapping("/register")
//    public ResponseEntity register(@RequestBody String token, @LoginUser UserSession userSession) {
//    	fCMDao.register(userSession.getId(), token);
//        return ResponseEntity.ok().build();
//    }
//
//	private void createReceiveNotification(User sender, User receiver) {
//	    if (receiver.isLogin()) {
//	        NotificationRequest notificationRequest = NotificationRequest.builder()
//	            .title("POST RECEIVED")
//	            .token(notificationDao.getToken(receiver.getId()))
//	            .message(NotificationType.POST_RECEIVED.generateNotificationMessage(sender, receiver))
//	            .build();
//	        notificationDao.sendNotification(notificationRequest);
//	    }
//	}
//
//	private void createTaggedNotification(User sender, List<User> receivers) {
//	    receivers.stream()
//	        .filter(User::isLogin)
//	        .forEach(receiver -> {
//	    		NotificationRequest notificationRequest = NotificationRequest.builder()
//	                .title("POST TAGGED")
//	                .token(notificationService.getToken(receiver.getId()))
//	                .message(NotificationType.POST_TAGGED.generateNotificationMessage(sender, receiver))
//	                .build();
//	            notificationService.sendNotification(notificationRequest);
//	        });
//	}
//	
//	
//	// 로그아웃 할 때 Map에서 삭제
//	@PostMapping("/logout")
//	public ResponseEntity logout(@LoginUser UserSession userSession, HttpSession httpSession) {
//	    loginService.logout(userSession.getId());
//	    notificationDao.deleteToken(userSession.getId());
//	    httpSession.removeAttribute(USER_SESSION_KEY);
//	    return ResponseEntity.ok().build();
//	}
    
    
    @PostMapping("/test")
    public void Test() throws IOException {
    	// 푸시보내기 TEST

		String FCM_URL = "https://fcm.googleapis.com/fcm/send";
//		String FCM_URL = "https://fcm.googleapis.com/v1/projects/commitment-c494c/messages:send";
		String server_key = "AAAAWld_GwM:APA91bHDTo6Kmzb1nVQrlAbHZ9Q-cmQeW1gHKbNPUBlyv83Jm0yUOTNXKxu5ePh_7TxFVVLkeY9L8VDNKzNr2fSDxurHgo_Pf9IhBpLonkdlQXKrf3Ap2fLe6fCHuYTDaBDnfxvriqzm";
		String tokenId = "ya29.c.Kp0B7wfzHV5rwl1tKk9Pj1JtnrjEVhdGTx1124mtWKAPe8gxEkL1ADLmgAJtukpWlvzCBOacMx9EqV_799xWFtxv6JavXVUgR8mOYxMIWPaEuzMWSkVXGUA7kH7w3ZF3mngOq-3tjPR_ylZ0Pi3T6IObtp2A0ZWMMPmJpSdBvLuyfVAP5DFVEHqifMofwNabYFs-BbWYema6CoBkNsZcDg";
		// tokenId: 프론트단에서 db에 넣은 토큰
		
		
		// 프론트에서 쓸 key 쌍
		//BD7qQTwflVa_ZVBBAyRu8YFsl8G75nCMbfNi9NScq7Kh7GzMEkLwhhot20hU-vIzAVnE3hC80XspyOPGpf2hnEw
		
		String result = "";

		URL url = new URL(FCM_URL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "key=" + server_key);
		conn.setRequestProperty("Content-Type", "application/json");

		JSONObject json = new JSONObject();

		try {

			json.put("to", tokenId.trim()); 
//			json.compute("registration_ids" , tokens)// 만약 여러명한테 푸시 보내려면  --> FirebaseCloudNotification.java 참고
			JSONObject data = new JSONObject();
//			data.put("url", "https://test.com");
			data.put("url", "http://localhost:8080/test");
			data.put("icon", "test.png"); 
			json.put("data", data);

			JSONObject info = new JSONObject();
			info.put("title", "푸시 테스트"); 
			info.put("body", "teeeeeeeeeeeeeest");
			json.put("notification", info);

		} catch (JSONException e1) {
			e1.printStackTrace();

		}

		try {
			
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(json.toString());
			wr.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;

			System.out.println("Output from Server .... \n");

			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			result = "succcess";

		} catch (Exception e) {

			e.printStackTrace();
			result = "failure";

		}

		System.out.println("FCM Notification is sent successfully : " + result);
    }
    
    // 토큰 얻기 (임시)
    @PostMapping("/token")
	private String getAccessToken() throws IOException {
		String firebaseConfigPath = "firebase/asder36-firebase-adminsdk-ltf6t-8243fcaebf.json";
		
		GoogleCredentials googleCredentials = GoogleCredentials
				.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
				.createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
		
		googleCredentials.refreshIfExpired();
		
		System.out.println(googleCredentials.getAccessToken().getTokenValue());
		return googleCredentials.getAccessToken().getTokenValue();
	}
}
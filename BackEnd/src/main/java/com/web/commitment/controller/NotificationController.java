package com.web.commitment.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dto.Token;

import net.minidev.json.JSONObject;

@RestController
public class NotificationController {
    
	static String FCM_URL = "https://fcm.googleapis.com/fcm/send";
	static String server_key = "AAAAFYsURMs:APA91bFIYjEBRGo9ZaveARADG5vTW62OWaHI_5hZbo0tgxynNioco3qgP_ZyUNCWUS60SpIup-kRrYoUUAZ-10EU_r1codVtadp0rrjjVRHvptVmt77ogLEw9c8U_lABTacuDdqyXun8";	
	
    @PostMapping("/test")
    public JSONObject Test(@RequestBody Token dataa, int type) throws IOException {

		String tokenId = dataa.getToken();
		System.out.println(dataa.getToken());
		
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
//			json.put("registration_ids" , tokens)// 만약 여러명한테 푸시 보내려면  --> FirebaseCloudNotification.java 참고
			JSONObject data = new JSONObject();
			data.put("title", "Commitment");
			data.put("body", "hello");
			json.put("data", data);
			
			
			JSONObject info = new JSONObject();
			if(type == 0) {
				info.put("title", "좋아요 알림"); 
				info.put("body", "누군가가 당신에게 좋아요를 눌렀습니다.");
				json.put("notification", info);
			}
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
		return json;
    }

}
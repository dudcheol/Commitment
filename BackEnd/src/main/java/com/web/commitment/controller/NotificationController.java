package com.web.commitment.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.websocket.RemoteEndpoint.Async;

import org.json.JSONException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dto.Token;

import net.minidev.json.JSONObject;

@RestController
public class NotificationController {
    
	
	@PostMapping("/ggget")
	public void ggget(@RequestBody String data) throws IOException {
		System.out.println(data);
	}
	
//	@PostMapping("/test")
//	public void send(@RequestBody String data) throws IOException {
//		String targetToken = data;
//		String title = "commitment";
//		String body = "test message";
//		fcmDao.sendMessageTo(targetToken, title, body);
//	}
	
	
    @PostMapping("/test")
    public JSONObject Test(@RequestBody Token dataa) throws IOException {
    	// 푸시보내기 TEST

		String FCM_URL = "https://fcm.googleapis.com/fcm/send";
//		String FCM_URL = "https://fcm.googleapis.com/v1/projects/commitment-c494c/messages:send";
		String server_key = "AAAAFYsURMs:APA91bFIYjEBRGo9ZaveARADG5vTW62OWaHI_5hZbo0tgxynNioco3qgP_ZyUNCWUS60SpIup-kRrYoUUAZ-10EU_r1codVtadp0rrjjVRHvptVmt77ogLEw9c8U_lABTacuDdqyXun8";
//		String tokenId = "dXiD9-R-FK_hMWnn6YkmwX:APA91bFPkmIfRTsGI1hAbo9nnN6pLO5kHTek5v98Z9XJyxD5T25EuoQEwNGUmXj5w0Uxks2vh6JcTkC98tHFPrl5-guO-fFAdbVdqYBrjyWmXZaBSJ9I9ejD_7hX2dPl0xJmlN7JsjUO";
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
//			json.compute("registration_ids" , tokens)// 만약 여러명한테 푸시 보내려면  --> FirebaseCloudNotification.java 참고
			JSONObject data = new JSONObject();
			data.put("title", "Commitment");
//			data.put("message", "hello, world");
			data.put("body", "hello, world");
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
		return json;
    }

//    
//    // 토큰 얻기 (임시)
//    @PostMapping("/token")
//	private String getAccessToken() throws IOException {
//		String firebaseConfigPath = "firebase/asder36-firebase-adminsdk-ltf6t-8243fcaebf.json";
//		
//		GoogleCredentials googleCredentials = GoogleCredentials
//				.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
//				.createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
//		
//		googleCredentials.refreshIfExpired();
//		
//		System.out.println(googleCredentials.getAccessToken().getTokenValue());
//		return googleCredentials.getAccessToken().getTokenValue();
//	}
    
//    @PostMapping("/test")
//    private FirebaseOptions Test1() throws IOException {
//    	FileInputStream serviceAccount =
//    			  new FileInputStream("firebase/asder36-firebase-adminsdk-ltf6t-8243fcaebf.json");
//
//    			FirebaseOptions options = new FirebaseOptions.Builder()
//    			  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//    			  .setDatabaseUrl("https://asder36-default-rtdb.firebaseio.com")
//    			  .build();
//
//    			FirebaseApp.initializeApp(options);
//				return options;
//    }
}
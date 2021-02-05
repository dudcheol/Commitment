package com.web.commitment.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dto.Token;
import com.web.commitment.dto.Notification.NotificationReqDto;
import com.web.commitment.service.NotificationService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/noti")
@RequiredArgsConstructor
public class NotificationController {
	
    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
    
	static String FCM_URL = "https://fcm.googleapis.com/fcm/send";
	static String server_key = "AAAAFYsURMs:APA91bFIYjEBRGo9ZaveARADG5vTW62OWaHI_5hZbo0tgxynNioco3qgP_ZyUNCWUS60SpIup-kRrYoUUAZ-10EU_r1codVtadp0rrjjVRHvptVmt77ogLEw9c8U_lABTacuDdqyXun8";	
	
    private final NotificationService notificationService;

    @ApiOperation(value = "알람 저장하기")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @PostMapping
    public ResponseEntity<Void> saveNotification(@RequestParam String email, @RequestBody final NotificationReqDto requestDto) {
    	System.out.println(email);
    	notificationService.saveNoti(requestDto, email);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "알람 읽기")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @PutMapping("/{notiId}")
    public ResponseEntity<Void> readNotification(@RequestParam String email, @PathVariable("notiId") final String notiId) {
        notificationService.readNoti(notiId, email);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "알람 삭제")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @DeleteMapping("/{notiId}")
    public ResponseEntity<Void> deleteNotification(@RequestParam String email, @PathVariable("notiId") final String notiId) {
        notificationService.deleteNoti(notiId, email);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "댓글 삭제 시, 알람 삭제")
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteCommentsNotification(@RequestParam String email, @PathVariable("commentId") final String commentId) {
        notificationService.deleteCommentAlert(commentId, email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/test")
    public static JSONObject Push(@RequestBody Token dataa) throws IOException {

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
//			if(type == 0) {
				info.put("title", "댓글 알림"); 
				info.put("body", "누군가가 당신에게 댓글을 달았습니다.");
				json.put("notification", info);
//			}
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
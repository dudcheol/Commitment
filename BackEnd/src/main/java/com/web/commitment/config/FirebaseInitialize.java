package com.web.commitment.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class FirebaseInitialize {
    @PostConstruct
    public void initialize() throws IOException {
		FileInputStream serviceAccount =
		  new FileInputStream("C:\\Users\\multicampus\\Documents\\s04p13a308\\BackEnd\\src\\main\\resources\\serviceAccountKey2.json");
		
		FirebaseApp firebaseApp = null;
		List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
		if(firebaseApps != null && !firebaseApps.isEmpty()) {
			for(FirebaseApp app : firebaseApps) {
				if(app.getName().equals(FirebaseApp.DEFAULT_APP_NAME))
					firebaseApp = app;
			}
		} else {

			FirebaseOptions options = new FirebaseOptions.Builder()
			  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
			  .setDatabaseUrl("https://asder36-default-rtdb.firebaseio.com")
			  .build();
			
			FirebaseApp.initializeApp(options);
		}
    }
}
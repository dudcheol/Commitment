package com.web.commitment.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseInitialize {
    @PostConstruct
    public void initialize() throws IOException {
		FileInputStream serviceAccount =
		  new FileInputStream("C:\\Users\\0901B\\Downloads\\s04p13a308-master\\BackEnd\\src\\main\\resources\\serviceAccountKey.json");
		
		FirebaseOptions options = new FirebaseOptions.Builder()
		  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
		  .setDatabaseUrl("https://asder36-default-rtdb.firebaseio.com")
		  .build();
		
		FirebaseApp.initializeApp(options);

    }
}
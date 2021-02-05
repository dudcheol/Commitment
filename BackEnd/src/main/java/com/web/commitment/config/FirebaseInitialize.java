package com.web.commitment.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class FirebaseInitialize {
    @PostConstruct
    public void initialize() throws IOException {		
    	String rootPath = System.getProperty("user.dir");
        System.out.println("현재 프로젝트의 경로 : "+ rootPath);
        
		FileInputStream serviceAccount =
		  new FileInputStream(rootPath + "\\src\\main\\resources\\serviceAccountKey.json");
		
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
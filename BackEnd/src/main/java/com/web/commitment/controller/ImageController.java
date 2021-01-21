package com.web.commitment.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.commitment.dao.S3Dao;

@RestController
public class ImageController {
    
	@Autowired
	private S3Dao s3Dao;
	
	@Autowired
	private S3Dao s3Uploader;

    @PostMapping(path = "/upload")
    public Map<String,String> uploadFile(@RequestParam(value = "file",required = false) MultipartFile[] files) throws IOException {
    	System.out.println(files[0].getOriginalFilename());
    	
    	String s3Path = "";
    	s3Dao.upload(files[0], s3Path);
        Map<String,String> result = new HashMap<>();
        result.put("key", files[0].getOriginalFilename());
        return result;
    }
}
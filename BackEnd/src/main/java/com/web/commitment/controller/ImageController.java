package com.web.commitment.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.commitment.dao.ImageDao;
import com.web.commitment.dao.S3Dao;
import com.web.commitment.dto.Image;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class ImageController {
    
//	private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
	
	@Autowired
	ImageDao imageDao;
	
	@Autowired
	private S3Dao s3Dao;
	
	@Autowired
	private S3Dao s3Uploader;

	// s3 -> sns/{sns_id}/ : 각 게시글 번호 폴더 안에 저장
    @PostMapping(path = "/image/{sns_id}")
    @ApiOperation(value = "이미지 업로드")
    public Map<String,String> uploadImage(@PathVariable String sns_id, @RequestParam(value = "file",required = false) MultipartFile[] files) throws IOException {
    	Map<String,String> result = new HashMap<>();
    	
    	String[] saveFileName = new String[files.length];
    	for (int i = 0; i < files.length; i++) {
			
			String originalFilename = files[i].getOriginalFilename();
//			파일 이름이 중복될 경우 처리해주기 위함		
//			String saveFileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf('.'));
			saveFileName[i] = UUID.randomUUID().toString() + "_" + originalFilename;
			System.out.println(saveFileName[i]);

			String s3Path = "sns/" + sns_id + "/";			
			Image image = new Image();
			image.setSnsId(sns_id);
			image.setFilePath(s3Path);
			image.setFileName(saveFileName[i]);
			
			imageDao.save(image);
			
			s3Dao.uploadImages(files[i], s3Path, saveFileName[i]);
			result.put("url" + i, files[i].getOriginalFilename());
		}
    	return result;
    }
    
    // 파일 경로는 "sns/sns_id/file_name"으로 고정 -> 파일 이름만 return하면 front에서 이미지 불러올 수 있음
    @GetMapping(path = "/image/{sns_id}")
    @ApiOperation(value = "이미지 불러오기")
    public List<String> loadImage(@PathVariable String sns_id) throws IOException {
    		
		List<Image> images = new ArrayList<>();
		images.addAll(imageDao.findAllBySnsId(sns_id));
		
		List<String> file_name = new ArrayList<>();
		for (int j = 0; j < images.size(); j++) {
			file_name.add(images.get(j).getFileName());
		}
    	return file_name;
    }
}
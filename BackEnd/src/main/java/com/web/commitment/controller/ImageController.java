package com.web.commitment.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

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
    
	@Autowired
	ImageDao imageDao;
	
	@Autowired
	private S3Dao s3Dao;
	
	@Autowired
	private S3Dao s3Uploader;

	// s3 -> sns/{sns_id}/ : 각 게시글 번호 폴더 안에 저장
    @PostMapping(path = "/image/{sns_id}")
    @ApiOperation(value = "사진 업로드 & 수정 & 삭제")
    @Transactional
    public Map<String,String> uploadImage(@PathVariable String sns_id, 
    		@RequestParam(value = "file",required = false) MultipartFile[] files) throws IOException {
    	Map<String,String> result = new HashMap<>();
    	
    	List<Image> images =  imageDao.findAllBySnsId(sns_id);
    	for (int i = 0; i < images.size(); i++) {
    		System.out.println(images.get(i).getFileName());
    		s3Uploader.deletefile(images.get(i).getFileName());
		}
    	
    	imageDao.deleteBySnsId(sns_id);
    	String[] saveFileName = new String[files.length];
    	for (int i = 0; i < files.length; i++) {
    		
			// 파일 이름이 중복될 경우 처리해주기 위함		
			saveFileName[i] = UUID.randomUUID().toString();
			System.out.println(saveFileName[i]);
			String s3Path = "sns/" + sns_id;	
			
			String url = s3Uploader.upload(files[i], s3Path, saveFileName[i]);
			
			Image image = new Image();
			image.setSnsId(sns_id);
			image.setFilePath(url);
			image.setFileName("sns/" + sns_id + "/" + saveFileName[i]);
			
			imageDao.save(image);
			
			
			result.put("url" + i, url);
		}
    	return result;
    }
    
    // 파일 경로는 "sns/sns_id/file_name"으로 고정 -> 파일 이름만 return하면 front에서 이미지 불러올 수 있음
    @GetMapping(path = "/image/{sns_id}")
    @ApiOperation(value = "사진 불러오기")
    public List<Image> loadImage(@PathVariable String sns_id) throws IOException {

    	List<Image> images = imageDao.findAllBySnsId(sns_id);
    	return images;
    }
}
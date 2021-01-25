package com.web.commitment.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor    // final 멤버변수가 있으면 생성자 항목에 포함시킴
@Component
public class S3Dao { 
    
    @Autowired
    AmazonS3Client amazonS3Client;
    
    @Value("${cloud.aws.s3.bucket}") 
    private String bucket; 
    
    // MultipartFile을 전달받아 File로 전환한 후 S3에 업로드
    public String upload(MultipartFile multipartFile, String dirName, String saveFileName) throws IOException {
        File uploadFile = convert(multipartFile) 
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));
        return upload(uploadFile, dirName, saveFileName); 
    } 
    
    private String upload(File uploadFile, String dirName, String saveFileName) {
    	String fileName = dirName +"/" + saveFileName; 
        String uploadImageUrl = putS3(uploadFile, fileName); 
        removeNewFile(uploadFile); // 로컬에 생성된 File 삭제 (MultipartFile -> File 전환 하며 로컬에 파일 생성됨)

        return uploadImageUrl;  // 업로드된 파일의 S3 URL 주소 반환
    } 

    private String putS3(File uploadFile, String fileName) { 
        
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead)); // PublicRead 권한으로 업로드 됨
        return amazonS3Client.getUrl(bucket, fileName).toString(); 
        
    } 
    
    private void removeNewFile(File targetFile) { 
        if (targetFile.delete()) { 

            System.out.println("파일이 삭제되었습니다.");
        } else { 
            System.out.println("파일이 삭제되지 못했습니다.");
        } 
        
    } 

    private void removeNewFiles(File[] targetFile) {
        for (int i = 0; i < targetFile.length; i++) {
            
            if (targetFile[i].delete()) { 
                System.out.println("파일이 삭제되었습니다.");
            
            } else { 
                System.out.println("파일이 삭제되지 못했습니다.");
            } 
        }
    }
    
    public void deletefile(String file_name) {
        System.out.println(file_name);
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, file_name));
    }
    
    private Optional<File> convert(MultipartFile file) throws IOException { 
        File convertFile = new File(file.getOriginalFilename()); 
        
        if(convertFile.createNewFile()) { 
            try (FileOutputStream fos = new FileOutputStream(convertFile)) { 
                fos.write(file.getBytes()); 
                
            } 
            
            return Optional.of(convertFile); 
            
        } 
        
        return Optional.empty(); 
    }
}
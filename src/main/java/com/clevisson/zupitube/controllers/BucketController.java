package com.clevisson.zupitube.controllers;

import com.clevisson.zupitube.service.AmazonClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/storage")
public class BucketController {
    private AmazonClient amazonClient;

    BucketController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @PostMapping("/uploadVideo")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return this.amazonClient.uploadVideo(file);
    }

    @DeleteMapping("/deleteVideo")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }

    @GetMapping("/listVideos")
    public List<String> listVideos() {
        return this.amazonClient.listObjects();
    }
}

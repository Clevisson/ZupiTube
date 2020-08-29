package com.clevisson.zupitube.controllers;

import com.clevisson.zupitube.service.AmazonClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storage")
public class BucketController {
    private AmazonClient amazonClient;

    BucketController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @PostMapping("/uploadVideo")
    public String uploadFile(@RequestPart(value = "video") MultipartFile file) {
        return this.amazonClient.uploadVideo(file);
    }

    @DeleteMapping("/deleteVideo")
    public String deleteFile(@RequestPart(value = "url") String videoUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(videoUrl);
    }

}

package com.clevisson.zupitube.controllers;

import com.clevisson.zupitube.model.Video;
import com.clevisson.zupitube.repository.VideoRepository;
import com.clevisson.zupitube.service.AmazonClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/video")
public class VideoController {

    private final VideoRepository repository;
    private AmazonClient amazonClient;

    public VideoController(VideoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Video> listVideo() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Video> listVideoById(@PathVariable("id") long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Video createVideo(@RequestBody Video video, @RequestPart(value = "file") MultipartFile file) {
        video.setKey(this.amazonClient.uploadVideo(file));
        return repository.save(video);
    }


    @PutMapping(value = "/{id}")//VER NECESSIDADE DE UPDATE COMMENT
    public ResponseEntity<Video> updateVideoById(@PathVariable("id") long id, @RequestBody Video video) {
        return repository.findById(id)
                .map(record -> {
                    record.setTumbnail_url(video.getTumbnail_url());
                    record.setKey(video.getKey());
                    record.setDescription(video.getDescription());
                    record.setTitle(video.getTitle());
                    record.setCreatedAt(video.getCreatedAt());
                    record.setUpdated_at(video.getUpdated_at());
                    Video updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> deleteVideoById(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}

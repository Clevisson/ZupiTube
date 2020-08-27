package com.clevisson.zupitube.controllers;

import com.clevisson.zupitube.model.Video;
import com.clevisson.zupitube.repository.VideoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Post")
public class PostController {
    private final VideoRepository repository;

    public PostController(VideoRepository repository) {
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
    public Video createComment(@RequestBody Video video) {
        return repository.save(video);
    }

    @PutMapping(value = "/{id}")//VER NECESSIDADE DE UPDATE COMMENT
    public ResponseEntity<Video> updateVideoById(@PathVariable("id") long id, @RequestBody Video video) {
        return repository.findById(id)
                .map(record -> {
                    record.setTumbnail_url(video.getTumbnail_url());
                    record.setKey(video.getKey());
                    record.setProcessed(video.getProcessed());
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

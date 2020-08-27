package com.clevisson.zupitube.controllers;

import com.clevisson.zupitube.model.Comment;
import com.clevisson.zupitube.repository.CommentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/comments")
public class CommentController {
    private final CommentRepository repository;

    public CommentController(CommentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Comment> listComments() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Comment> listChannelById(@PathVariable("id") long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return repository.save(comment);
    }

    @PutMapping(value = "/{id}")//VER NECESSIDADE DE UPDATE COMMENT
    public ResponseEntity<Comment> updateCommentById(@PathVariable("id") long id, @RequestBody Comment comment) {
        return repository.findById(id)
                .map(record -> {
                    record.setReplay(comment.getReplay());
                    record.setCreatedAt(comment.getCreatedAt());
                    record.setUpdated_at(comment.getUpdated_at());
                    Comment updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> deleteCommentById(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}


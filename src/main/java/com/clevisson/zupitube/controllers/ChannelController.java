package com.clevisson.zupitube.controllers;

import com.clevisson.zupitube.model.Channel;
import com.clevisson.zupitube.repository.ChannelRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/channel")
public class ChannelController {
    private final ChannelRepository repository;

    public ChannelController(ChannelRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Channel> listChannel() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Channel> listChannelById(@PathVariable("id") long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Channel createChannel(@RequestBody Channel channel) {
        return repository.save(channel);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Channel> updateChannelById(@PathVariable("id") long id, @RequestBody Channel channel) {
        return repository.findById(id)
                .map(record -> {
                    record.setSequence(channel.getSequence());
                    record.setCreatedAt(channel.getCreatedAt());
                    record.setUpdated_at(channel.getUpdated_at());
                    Channel updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> deleteChannelById(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}

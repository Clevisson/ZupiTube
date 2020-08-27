package com.clevisson.zupitube.repository;

import com.clevisson.zupitube.model.Video;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<Video, Long> {
}

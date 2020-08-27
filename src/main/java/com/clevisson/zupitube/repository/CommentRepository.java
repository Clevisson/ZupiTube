package com.clevisson.zupitube.repository;

import com.clevisson.zupitube.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}

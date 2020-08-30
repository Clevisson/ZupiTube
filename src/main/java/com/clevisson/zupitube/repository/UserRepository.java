package com.clevisson.zupitube.repository;

import com.clevisson.zupitube.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}

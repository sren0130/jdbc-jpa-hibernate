package org.james.mongospringbootdemo.repository;

import org.james.mongospringbootdemo.document.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository <Users, Integer> {
}

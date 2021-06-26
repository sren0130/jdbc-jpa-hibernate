package org.james.anotherdemo.dao;

import org.james.anotherdemo.model.User;

public interface UserDetailsDao {
    User findUserByUsername(String username);
}
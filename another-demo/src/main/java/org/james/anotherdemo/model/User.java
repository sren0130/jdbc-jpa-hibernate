package org.james.anotherdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String username;
    private String password;
    private String[] roles;
//
//    public User(String username, String password, String... roles) {
//        this.username = username;
//        this.password = password;
//        this.roles = roles;
//    }


}

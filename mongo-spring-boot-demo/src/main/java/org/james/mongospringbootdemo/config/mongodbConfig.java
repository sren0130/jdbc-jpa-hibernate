package org.james.mongospringbootdemo.config;

import org.james.mongospringbootdemo.document.Users;
import org.james.mongospringbootdemo.repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = UsersRepository.class)
public class mongodbConfig {

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {

            @Override
            public void run(String... args) throws Exception {
                return strings->{
                    usersRepository.save(new Users(3,"John", "Development", 4400L));
                };
            }
        };
    }
}

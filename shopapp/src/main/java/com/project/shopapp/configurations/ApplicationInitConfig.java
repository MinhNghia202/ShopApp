package com.project.shopapp.configurations;

import com.project.shopapp.Repositories.RoleRepository;
import com.project.shopapp.Repositories.UserRepository;
import com.project.shopapp.models.Role;
import com.project.shopapp.models.User;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ApplicationInitConfig {

    private static final Logger log = LoggerFactory.getLogger(ApplicationInitConfig.class);
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository){
        return args -> {
            if(userRepository.findByUsername("admin").isEmpty()){
                Role role = Role.builder()
                        .id(2)
                        .name("admin")
                        .build();
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .role(role)
                        .build();
                roleRepository.save(role);
                userRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change password");
            }
        };
    }

    @PostConstruct
    public void initRoles() {
        if (roleRepository.findByName("user").isEmpty()) {
            Role roleUser = Role.builder()
                    .name("user")
                    .build();
            roleRepository.save(roleUser);
        }
    }
}

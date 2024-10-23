package com.system.athon_stock.domain;

import com.system.athon_stock.domain.person.Person;
import com.system.athon_stock.domain.person.UserRole;
import com.system.athon_stock.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDataBase implements ApplicationListener<ContextRefreshedEvent> {

    public final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        var user = personRepository.findByLoginAndId("admin", 1)
                .orElse(Person.builder()
                        .login("admin")
                        .password(passwordEncoder.encode("admin"))
                        .role(UserRole.ADMIN)
                        .phone("44997574461")
                        .email("admin@GMAIL.com")
                        .name("admin")
                        .build());
        personRepository.save(user);

    }
}

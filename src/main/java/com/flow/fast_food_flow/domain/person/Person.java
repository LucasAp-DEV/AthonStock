package com.flow.fast_food_flow.domain.person;

import com.flow.fast_food_flow.domain.excessoes.CredentialsException;
import com.flow.fast_food_flow.domain.store.Store;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class Person implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String name;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Store> stores = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    public Person(String login, String password, String email, String name, String phone, UserRole role) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    private void validatePerson(UpdatePersonDTO person) {
        if (Objects.isNull(person.name()) || person.name().isBlank())
            throw new CredentialsException("É necessario informar um nome");
    }

    public void bind (UpdatePersonDTO person) {
        validatePerson(person);
        this.name = person.name();
        this.email = person.email();
        this.phone = person.phone();
    }

}

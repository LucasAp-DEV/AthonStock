package com.system.athon_stock.domain.person;

import com.system.athon_stock.domain.contrato.Contrato;
import com.system.athon_stock.domain.excessoes.CredentialsException;
import com.system.athon_stock.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "person")
public class Person implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;

    private String name;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Contrato> contrato = new ArrayList<>();

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
        if (this.role == UserRole.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
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

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{2}\\d{8,9}$");


    private void validatePerson(UpdatePersonDTO person) {
        if (Objects.isNull(person.name()) || person.name().isBlank())
            throw new CredentialsException("É necessario informar um Nome");
        if (!Objects.equals(person.email(), "") && !EMAIL_PATTERN.matcher(person.email()).matches())
            throw new CredentialsException("É necessario informar um Email válido");
        if (!Objects.equals(person.phone(), "") && !PHONE_PATTERN.matcher(person.phone()).matches())
            throw new CredentialsException("É necessario informar um Telefone válido");
    }

    private void validateRegisterPerson(RegisterPersonDTO registerPersonDTO) {
        if (Objects.isNull(registerPersonDTO.login()) || registerPersonDTO.login().isBlank())
            throw new CredentialsException("É necessario informar um Login");
        if (Objects.isNull(registerPersonDTO.name()) || registerPersonDTO.name().isBlank())
            throw new CredentialsException("É necessario informar um Nome");
        if (!Objects.equals(registerPersonDTO.email(), "") && !EMAIL_PATTERN.matcher(registerPersonDTO.email()).matches())
            throw new CredentialsException("É necessario informar um Email válido");
        if (!Objects.equals(registerPersonDTO.phone(), "") && !PHONE_PATTERN.matcher(registerPersonDTO.phone()).matches())
            throw new CredentialsException("É necessario informar um Telefone válido");
    }

    public void bind(UpdatePersonDTO person) {
        validatePerson(person);
        this.name = person.name();
        this.email = person.email();
        this.phone = person.phone();
    }

    public void bindRegister(RegisterPersonDTO registerPersonDTO) {
        validateRegisterPerson(registerPersonDTO);
        this.name = registerPersonDTO.name();
        this.email = registerPersonDTO.email();
        this.phone = registerPersonDTO.phone();
    }

}

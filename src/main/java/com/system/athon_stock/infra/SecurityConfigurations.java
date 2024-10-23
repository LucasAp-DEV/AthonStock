package com.system.athon_stock.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    public final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers(HttpMethod.GET, "/person/{id}").hasRole("USER")
                                .requestMatchers(HttpMethod.POST, "/person/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/person/register").hasRole("ADMIN")

//                                .requestMatchers(HttpMethod.POST, "/store/register").hasRole("USER")
//                                .requestMatchers(HttpMethod.PUT, "/store/update/{id}").hasRole("USER")
//                                .requestMatchers(HttpMethod.GET, "/store/person/{id}").hasRole("USER")

                                .requestMatchers(HttpMethod.POST, "/product/register").hasRole("USER")
                                .requestMatchers(HttpMethod.PUT, "/product/update/{id}").hasRole("USER")
                                .requestMatchers(HttpMethod.GET, "/product/person/all/{id}").hasRole("USER")
                                .requestMatchers(HttpMethod.GET, "/product/person/zero-stock/{id}").hasRole("USER")
                                .requestMatchers(HttpMethod.GET, "/product/person/low-stock/{id}").hasRole("USER")
                                .requestMatchers(HttpMethod.PUT, "/product/update/price/{id}").hasRole("USER")
                                .requestMatchers(HttpMethod.POST, "/product/update/stock/{id}").hasRole("USER")

                                .requestMatchers(HttpMethod.POST, "/contrato/register").hasRole("USER")
                                .requestMatchers(HttpMethod.GET, "/contrato/user/{id}").hasRole("USER")
                                .requestMatchers(HttpMethod.GET, "/contrato/user/name/client/{id}").hasRole("USER")
                                .requestMatchers(HttpMethod.PUT, "/contrato/update/{id}").hasRole("USER")
                                .requestMatchers(HttpMethod.GET, "/contrato/user/findByData/{id}").hasRole("USER")

                                .requestMatchers(HttpMethod.POST, "/admin/unblock-user").hasRole("ADMIN")

                                .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

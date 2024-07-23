package com.system.athon_stock.service;

import com.system.athon_stock.domain.UnblockUserRequest;
import com.system.athon_stock.domain.excessoes.CredentialsException;
import com.system.athon_stock.repository.PersonRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginAttemptService {

    private final Map<String, Integer> attemptsCache;
    private final PersonRepository repository;

    public LoginAttemptService(PersonRepository repository) {
        this.repository = repository;
        this.attemptsCache = new HashMap<>();
    }

    public void loginSucceeded(String login) {
        attemptsCache.remove(login);
    }

    public void loginFailed(String login) {
        int attempts = 0;
        if (attemptsCache.containsKey(login)) {
            attempts = attemptsCache.get(login);
        }
        attempts++;
        attemptsCache.put(login, attempts);
    }

    public boolean isBlocked(String login) {
        int MAX_ATTEMPTS = 3;
        return attemptsCache.containsKey(login) && attemptsCache.get(login) >= MAX_ATTEMPTS;
    }

    private UserDetails getPerson(String login) {
        var person = repository.findByLogin(login);
        if(person == null) {
            throw new CredentialsException("Usuario nao existe no sistema");
        }
        return person;
    }

    public void resetAttempts(UnblockUserRequest login) {
        var username = getPerson(login.getLogin());
        attemptsCache.remove(username.getUsername());
    }
}
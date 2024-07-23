package com.system.athon_stock.controller;

import com.system.athon_stock.domain.UnblockUserRequest;
import com.system.athon_stock.service.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final LoginAttemptService loginAttemptService;

    @PostMapping("/unblock-user")
    public void unblockUser(@RequestBody UnblockUserRequest login) {
        loginAttemptService.resetAttempts(login);
    }
}


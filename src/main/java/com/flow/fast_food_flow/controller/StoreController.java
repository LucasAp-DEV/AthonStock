package com.flow.fast_food_flow.controller;

import com.flow.fast_food_flow.domain.store.RegisterStoreDTO;
import com.flow.fast_food_flow.domain.store.Store;
import com.flow.fast_food_flow.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("store")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/register")
    public void register(@RequestBody Store data) {
        storeService.registerStore(data);
    }
}

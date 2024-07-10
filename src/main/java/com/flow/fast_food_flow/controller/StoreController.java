package com.flow.fast_food_flow.controller;

import com.flow.fast_food_flow.domain.store.RegisterStoreDTO;
import com.flow.fast_food_flow.domain.store.ResponseListStoreDTO;
import com.flow.fast_food_flow.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("store")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterStoreDTO data) {
        storeService.registerStore(data);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable(value = "id")Long id, @RequestBody RegisterStoreDTO data) {
        storeService.updateStore(id, data);
    }

    @GetMapping("/person/{id}")
    public List<ResponseListStoreDTO> returnListPersonStore(@PathVariable(value = "id")Long id) {
        return storeService.returnListPersonstore(id);
    }
}

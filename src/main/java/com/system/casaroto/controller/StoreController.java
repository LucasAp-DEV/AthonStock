package com.system.casaroto.controller;

import com.system.casaroto.domain.store.RegisterStoreDTO;
import com.system.casaroto.domain.store.ResponseListStoreDTO;
import com.system.casaroto.service.StoreService;
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

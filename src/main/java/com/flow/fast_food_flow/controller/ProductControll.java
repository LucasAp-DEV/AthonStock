package com.flow.fast_food_flow.controller;

import com.flow.fast_food_flow.domain.product.RegisterProductDTO;
import com.flow.fast_food_flow.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("product")
public class ProductControll {

    private final ProductService productService;

    @PostMapping("/register")
    public void registerProduct(@RequestBody RegisterProductDTO data) {
        productService.saveProduct(data);
    }
}

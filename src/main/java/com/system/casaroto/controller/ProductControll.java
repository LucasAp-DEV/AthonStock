package com.system.casaroto.controller;

import com.system.casaroto.domain.product.RegisterProductDTO;
import com.system.casaroto.domain.product.UpdatePriceProduct;
import com.system.casaroto.domain.product.UpdateProduct;
import com.system.casaroto.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("product")
public class ProductControll {

    private final ProductService productService;

    @PostMapping("/register")
    public void registerProduct(@RequestBody RegisterProductDTO data) {
        productService.saveProduct(data);
    }

    @PutMapping("/update/{id}")
    public void updateProduct(@PathVariable(value = "id")Long id, @RequestBody UpdateProduct data){
        productService.updateProduct(id, data);
    }

    @PutMapping("/update/price/{id}")
    public void updatePrice(@PathVariable(value = "id")Long id, @RequestBody UpdatePriceProduct data){
        productService.updatePriceProduct(id, data);
    }
}

package com.system.athon_stock.controller;

import com.system.athon_stock.domain.product.*;
import com.system.athon_stock.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/person/all/{id}")
    public List<ReturnProduct> getAllProduct(@PathVariable(value = "id")Long id){
        return productService.returnProductAll(id);
    }
}

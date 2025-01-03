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
    public void updateProduct(@PathVariable(value = "id")Long id, @RequestBody UpdateProduct product){
        productService.updateProduct(id, product);
    }

    @PostMapping("/update/stock/{id}")
    public void updatePrice(@PathVariable(value = "id")Long id, @RequestBody UpdateStockProduct data){
        productService.updateStockProduct(id, data);
    }

    @GetMapping("/person/all/{id}")
    public List<ReturnProduct> getAllProduct(@PathVariable(value = "id")Long id){
        return productService.returnProductAll(id);
    }

    @GetMapping("/person/zero-stock/{id}")
    public List<ReturnProduct> getAllProductZeroStoke(@PathVariable(value = "id")Long id){
        return productService.returnProductZeroStock(id);
    }

    @GetMapping("/person/low-stock/{id}")
    public List<ReturnProduct> getAllProductLowStoke(@PathVariable(value = "id")Long id, Integer primeiroValor, Integer segundoValor){
        return productService.returnProductLowStock(id, primeiroValor, segundoValor);
    }
}

package com.movies.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductsController {
    @RequestMapping("/products")
    public List<Product> getProducts() {

        List<Product> products = new ArrayList<>();
        products.add(new Product("Milk"));
        products.add(new Product("Eggs"));
        products.add(new Product("Beer"));

        return products;
    }
}

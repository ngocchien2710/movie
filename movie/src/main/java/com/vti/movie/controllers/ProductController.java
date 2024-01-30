package com.vti.movie.controllers;

import com.vti.movie.modal.dtos.ProductDto;
import com.vti.movie.modal.entity.Product;
import com.vti.movie.modal.request.CreateProductRequest;
import com.vti.movie.modal.request.UpdateProductRequest;
import com.vti.movie.service.implement.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/get-all")
    public List<Product> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping("/create")
    public void create(@RequestBody CreateProductRequest request){

        service.create(request);
    }

    @PutMapping("/update")
    public Product update(@RequestBody UpdateProductRequest request){
        return service.update(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}

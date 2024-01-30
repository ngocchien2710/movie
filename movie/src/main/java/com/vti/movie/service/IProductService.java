package com.vti.movie.service;

import com.vti.movie.modal.dtos.ProductDto;
import com.vti.movie.modal.entity.Product;
import com.vti.movie.modal.request.CreateProductRequest;
import com.vti.movie.modal.request.UpdateProductRequest;

import java.util.List;

public interface IProductService {
    List<Product> getAll();

    ProductDto getById(int id);

    void create(CreateProductRequest request);

    Product update(UpdateProductRequest request);

    void delete(int id);
}

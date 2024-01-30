package com.vti.movie.service.implement;

import com.vti.movie.modal.dtos.ProductDto;
import com.vti.movie.modal.entity.Product;
import com.vti.movie.modal.entity.User;
import com.vti.movie.modal.request.CreateProductRequest;
import com.vti.movie.modal.request.UpdateProductRequest;
import com.vti.movie.repository.IUserRepository;
import com.vti.movie.repository.ProductRepository;
import com.vti.movie.service.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> getAll() {

        return repository.findAll();
    }

    @Override
    public ProductDto getById(int id) {
        Optional<Product> optional = repository.findById(id);
        if(optional.isPresent()){
            Product aProduct = optional.get();
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(aProduct, productDto);
            productDto.setCreatorId(aProduct.getCreatorId().getId());

            return productDto;
        }
        return null;
    }

    @Override
    public void create(CreateProductRequest request) {
        Product bProduct = new Product();
        BeanUtils.copyProperties(request, bProduct);

        Optional<User> userOptional = userRepository.findById(request.getCreatorId());

        if(userOptional.isPresent()){
            bProduct.setCreatorId(userOptional.get());

            repository.save(bProduct);
        }


    }

    @Override
    public Product update(UpdateProductRequest request) {
        Optional<Product> optionalProduct = repository.findById(request.getId());
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            BeanUtils.copyProperties(request,product);
            return repository.save(product);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}


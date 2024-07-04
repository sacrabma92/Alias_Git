package com.springdatajpa.service.Impl;

import com.springdatajpa.Dto.ProductDTO;
import com.springdatajpa.entity.Product;
import com.springdatajpa.repository.ProductRepository;
import com.springdatajpa.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Instancia de ModelMapper
    @Autowired
    private ModelMapper mapper;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        // Convertir de DTO a Entity
        Product product = mapToEntity(productDTO);
        // Guardamos el productp ingresado
        Product newProduct = productRepository.save(product);
        // Devolvemos el objeto que estamos creando
        ProductDTO productResponse = mapToDto(newProduct);
        return productResponse;
    }

    // Convertir Entity a Dto
    private ProductDTO mapToDto(Product product){
        ProductDTO productDTO = mapper.map(product, ProductDTO.class);
        return productDTO;
    }

    // Convertir Dto a Entity
    private Product mapToEntity(ProductDTO productDTO){
        Product product = mapper.map(productDTO, Product.class);
        return product;
    }

}

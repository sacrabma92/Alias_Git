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

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Long id) {
        // Obtenemos un producto por id, en caso que no exista lanzamos exception
        Product productById = productRepository.findById(id).orElseThrow();

        // Verificamos si el nuevo SKU ya existe y no pertenece al producto actual
        if (productDTO.getSku() != null && !productDTO.getSku().equals(productById.getSku())) {
            boolean exists = productRepository.existsBySku(productDTO.getSku());
        }

        // Actualizamos todos los campos excepto el SKU
        if (productDTO.getName() != null) {
            productById.setName(productDTO.getName());
        }
        if (productDTO.getDescription() != null) {
            productById.setDescription(productDTO.getDescription());
        }
        if (productDTO.getPrice() != null) {
            productById.setPrice(productDTO.getPrice());
        }
        if (productDTO.getActive() != null) {
            productById.setActive(productDTO.getActive());
        }
        if (productDTO.getImageUrl() != null) {
            productById.setImageUrl(productDTO.getImageUrl());
        }

        // Guardamos en BD
        Product updatedProduct = productRepository.save(productById);

        // Retornamos los datos almacenados en la BD
        return mapToDto(updatedProduct);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return mapToDto(product);
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

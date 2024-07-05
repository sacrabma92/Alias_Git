package com.springdatajpa.service.Impl;

import com.springdatajpa.Dto.ProductDTO;
import com.springdatajpa.entity.Product;
import com.springdatajpa.exceptions.ResourceNotFoundException;
import com.springdatajpa.repository.ProductRepository;
import com.springdatajpa.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Product productById = productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Product","id", id));

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
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Product","id", id));
        return mapToDto(product);
    }

    @Override
    public List<ProductDTO> saveAllProducts(List<ProductDTO> products) {
        // Convertir la lista de ProductDTO a una lista de entidades Product
        List<Product> entities = products.stream()
                // Utilizar el método mapToEntity para convertir cada ProductDTO en una entidad Product
                .map(this::mapToEntity)
                // Almacenamos los resultados en una lista
                .collect(Collectors.toList());

        // Guardar todas las entidades Product en la base de datos
        List<Product> savedEntities = productRepository.saveAll(entities);

        // Convertir la lista de entidades Product guardadas de nuevo a una lista de ProductDTO
        return savedEntities.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> mapToDto(product)).collect(Collectors.toList());
    }

    @Override
    public void deleteProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product","id",id));
        productRepository.delete(product);
    }

    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    @Override
    public long countProducts() {
        return productRepository.count();
    }

    @Override
    public boolean existsProductById(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    public void disableProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setActive(false);
        productRepository.save(product);
    }

    @Override
    public void enableProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setActive(true);
        productRepository.save(product);
    }

    @Override
    public List<ProductDTO> findAllEnabledProducts() {
        List<Product> enabledProducts = productRepository.findByActiveTrue();
        return enabledProducts.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> findProductByName(String name) {
        Optional<Product> product = productRepository.findByName(name);
        return product.map(this::mapToDto);
    }

    @Override
    public List<ProductDTO> findProductsByNameOrDescription(String search) {
        List<Product> products = productRepository.findByNameContainingOrDescriptionContaining(search, search);
        return products.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findDistinctProductsByName(String name) {
        List<Product> products = productRepository.findDistinctByName(name);
        return products.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findProductsByPriceGreaterThan(Double price) {
        List<Product> products = productRepository.findByPriceGreaterThan(price);
        return products.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findProductsByPriceLessThan(Double price) {
        List<Product> products = productRepository.findByPriceLessThan(price);
        return products.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findProductsByDescriptionContaining(String description) {
        List<Product> products = productRepository.findByDescriptionContaining(description);
        return products.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
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

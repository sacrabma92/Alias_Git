
package com.example.first_api_rest.service;

import com.example.first_api_rest.model.Producto;
import com.example.first_api_rest.repository.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRespository;
    
    public Producto saveProducto(Producto producto){
        return productoRespository.save(producto);
    }
    
    public List<Producto> getProductos(){
        return productoRespository.getAllProductos();
    }
    
    public Producto getProductoById(int id){
        return  productoRespository.findById(id);
    }
    
    public String deleProducto(int id){
        productoRespository.delete(id);
        return "Producto eliminado!!" + id;
    }
    
    public Producto updateProducto(Producto producto){
        return productoRespository.update(producto);
    }
}

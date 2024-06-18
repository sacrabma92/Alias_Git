
package com.example.first_api_rest.controller;

import com.example.first_api_rest.model.Producto;
import com.example.first_api_rest.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;
    
    @PostMapping
    public Producto agregarProducto(@RequestBody Producto producto){
        return productoService.saveProducto(producto);
    }
    
    @GetMapping
    public List<Producto> listarProductos(){
        return productoService.getProductos();
    }
    
    @GetMapping("/{id}")
    public Producto buscarProducto(@PathVariable int id){
        return productoService.getProductoById(id);
    } 
    
    @PutMapping
    public Producto actualizarProducto(@RequestBody Producto producto){
        return productoService.updateProducto(producto);
    }
    
    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable int id){
        return productoService.deleProducto(id);
    }
}

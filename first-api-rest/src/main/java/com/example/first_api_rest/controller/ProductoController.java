
package com.example.first_api_rest.controller;

import com.example.first_api_rest.service.ProductoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    
    private ProductoService productoService;
    
}

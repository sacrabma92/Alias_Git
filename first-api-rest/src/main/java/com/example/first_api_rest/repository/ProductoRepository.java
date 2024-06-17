package com.example.first_api_rest.repository;

import com.example.first_api_rest.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductoRepository {

    private List<Producto> productos = new ArrayList<>();

    // Creamos una lista de productos
    public void createProducts(){
        productos = List.of(
                new Producto(1, "Producto 01", 2, 5),
                new Producto(2, "Producto 02", 10, 900),
                new Producto(3, "Producto 03", 10, 800),
                new Producto(4, "Producto 04", 10, 600),
                new Producto(5, "Producto 05", 10, 700),
                new Producto(6, "Producto 06", 10, 400),
                new Producto(7, "Producto 07", 10, 500),
                new Producto(8, "Producto 08", 10, 300),
                new Producto(9, "Producto 09", 10, 200),
                new Producto(10, "Producto 10", 10, 100),
                new Producto(11, "Producto 11", 10, 140)
        );
    }

    // Listar todos los productos
    public List<Producto> getAllProductos(){
        return productos;
    }

    // Buscar un producto por id
    public Producto findById(int id){
        for(int i = 0; i < productos.size(); i++){
            if(productos.get(i).getId() == id){
                return productos.get(i);
            }
        }
        return null;
    }

    // Buscar producto por nombre
    public List<Producto> search(String nombre){
        return productos.stream()
                .filter(x -> x.getNombre)
    }
}

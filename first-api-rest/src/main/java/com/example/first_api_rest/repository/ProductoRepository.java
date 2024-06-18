package com.example.first_api_rest.repository;

import com.example.first_api_rest.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                .filter(x -> x.getNombre().startsWith(nombre))
                .collect(Collectors.toList());
    }

    // Guardar un producto
    public Producto save(Producto p){
        Producto producto = new Producto();
        producto.setId(p.getId());
        producto.setNombre(p.getNombre());
        producto.setCantidad(p.getCantidad());
        producto.setPrecio(p.getPrecio());

        productos.add(producto);
        return producto;
    }

    // Eliminar un producto
    public String delete(Integer id){
        productos.removeIf(x -> x.getId() == id);
        return null;
    }
    
    public Producto update(Producto producto){
        int idPos = 0;
        int id = 0;
        
        // Comprobamos si existe el producto y obtenermos la posicion y ID del producto
        for(int i = 0; i < productos.size(); i++){
            if(productos.get(i).getId() == (producto.getId())){
                id = producto.getId(); // Id del producto
                idPos = i; // posicion
            }
        }
        
        Producto producto1 = new Producto();
        producto1.setId(id);
        producto1.setNombre(producto.getNombre());
        producto1.setCantidad(producto.getCantidad());
        producto1.setPrecio(producto.getPrecio());
        productos.set(idPos, producto);
        return producto1;
    }
}


package com.api.hateoas.controller;

import com.api.hateoas.model.Cuenta;
import com.api.hateoas.model.Monto;
import com.api.hateoas.service.CuentaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public ResponseEntity<List<Cuenta>> listarCuentas(){
        List<Cuenta> cuentas = cuentaService.listAll();
        if(cuentas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        
        for(Cuenta cuenta:cuentas){
            cuenta.add(linkTo(methodOn(CuentaController.class).listarCuenta(cuenta.getId())).withSelfRel());
            cuenta.add(linkTo(methodOn(CuentaController.class).depositarDinero(cuenta.getId(), null)).withRel("deposito"));
            cuenta.add(linkTo(methodOn(CuentaController.class).listarCuentas()).withRel(IanaLinkRelations.COLLECTION));
        }
        
        CollectionModel<Cuenta> modelo = CollectionModel.of(cuentas);
        modelo.add(linkTo(methodOn(CuentaController.class).listarCuentas()).withSelfRel());
        
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> listarCuenta(@PathVariable Integer id){
        try {
            Cuenta cuenta = cuentaService.get(id);
            cuenta.add(linkTo(methodOn(CuentaController.class).listarCuenta(cuenta.getId())).withSelfRel());
            cuenta.add(linkTo(methodOn(CuentaController.class).depositarDinero(cuenta.getId(), null)).withRel("deposito"));
            cuenta.add(linkTo(methodOn(CuentaController.class).listarCuentas()).withRel(IanaLinkRelations.COLLECTION));
            
            return new ResponseEntity<>(cuenta, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Cuenta> guardarCuenta(@RequestBody Cuenta cuenta){
        Cuenta cuentaBBDD = cuentaService.save(cuenta);
        
        cuentaBBDD.add(linkTo(methodOn(CuentaController.class).listarCuenta(cuentaBBDD.getId())).withSelfRel());
        cuentaBBDD.add(linkTo(methodOn(CuentaController.class).depositarDinero(cuentaBBDD.getId(), null)).withRel("deposito"));
        cuentaBBDD.add(linkTo(methodOn(CuentaController.class).listarCuentas()).withRel(IanaLinkRelations.COLLECTION));
        
        return ResponseEntity.created(linkTo(methodOn(CuentaController.class).listarCuenta(cuentaBBDD.getId())).toUri()).body(cuentaBBDD);
    }
    
    @PutMapping
    public ResponseEntity<Cuenta> editarCuenta(@RequestBody Cuenta cuenta){
        Cuenta cuentaBBDD = cuentaService.save(cuenta);
        
        cuentaBBDD.add(linkTo(methodOn(CuentaController.class).listarCuenta(cuentaBBDD.getId())).withSelfRel());
        cuentaBBDD.add(linkTo(methodOn(CuentaController.class).depositarDinero(cuentaBBDD.getId(), null)).withRel("deposito"));
        cuentaBBDD.add(linkTo(methodOn(CuentaController.class).listarCuentas()).withRel(IanaLinkRelations.COLLECTION));
        
        return new ResponseEntity<>(cuenta, HttpStatus.OK);
    }
    
    @PatchMapping("/{id}/deposito")
    public ResponseEntity<Cuenta> depositarDinero(@PathVariable Integer id,@RequestBody Monto monto){
        Cuenta cuentaBBDD = cuentaService.depositar(monto.getMonto(), id);
        
        cuentaBBDD.add(linkTo(methodOn(CuentaController.class).listarCuenta(cuentaBBDD.getId())).withSelfRel());
        cuentaBBDD.add(linkTo(methodOn(CuentaController.class).depositarDinero(cuentaBBDD.getId(), null)).withRel("deposito"));
        cuentaBBDD.add(linkTo(methodOn(CuentaController.class).listarCuentas()).withRel(IanaLinkRelations.COLLECTION));
        
        return new ResponseEntity<>(cuentaBBDD,HttpStatus.OK);
    }
    
    @PatchMapping("/{id}/retiro")
    public ResponseEntity<Cuenta> retirarDinero(@PathVariable Integer id,@RequestBody Monto monto){
        Cuenta cuentaBBDD = cuentaService.retirar(monto.getMonto(), id);
        
        cuentaBBDD.add(linkTo(methodOn(CuentaController.class).listarCuenta(cuentaBBDD.getId())).withSelfRel());
        cuentaBBDD.add(linkTo(methodOn(CuentaController.class).depositarDinero(cuentaBBDD.getId(), null)).withRel("deposito"));
        cuentaBBDD.add(linkTo(methodOn(CuentaController.class).depositarDinero(cuentaBBDD.getId(), null)).withRel("retiros"));
        cuentaBBDD.add(linkTo(methodOn(CuentaController.class).listarCuentas()).withRel(IanaLinkRelations.COLLECTION));
        
        return new ResponseEntity<>(cuentaBBDD,HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCuenta(@PathVariable Integer id){
        try {
            cuentaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.api.rest.api_rest_encuentas.controller;

import com.api.rest.api_rest_encuentas.model.Encuesta;
import com.api.rest.api_rest_encuentas.repository.EncuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class EncuestaController {
   @Autowired
   private EncuestaRepository encuestaRepository;

   @GetMapping("/encuestas")
   public ResponseEntity<Iterable<Encuesta>> listarTodasLasEncuestas(){
      return new ResponseEntity<>(encuestaRepository.findAll(), HttpStatus.OK);
   }

   @PostMapping("/encuestas")
   public ResponseEntity<?> crearEncuetas(@RequestBody Encuesta encuesta){
      encuesta = encuestaRepository.save(encuesta);

      HttpHeaders httpHeaders = new HttpHeaders();
      URI newEncuestaUri = ServletUriComponentsBuilder
              .fromCurrentRequest()
              .path("/{id}")
              .buildAndExpand(encuesta.getId()).toUri();
      httpHeaders.setLocation(newEncuestaUri);

      return new ResponseEntity<>(null, HttpStatus.CREATED);
   }

   @GetMapping("/encuestas/{encuestaId}")
   public ResponseEntity<?> obtenerEncuesta(@PathVariable Long encuestaId){
      Optional<Encuesta> encuesta = encuestaRepository.findById(encuestaId);

      if(encuesta.isPresent()){
         return  new ResponseEntity<>(encuesta,HttpStatus.OK);
      }else{
         return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
      }
   }

   @PutMapping("/encuestas/{encuetaId}")
   public ResponseEntity<?> actualizarEncuesta(@RequestBody Encuesta encuesta, @PathVariable Long encuetaId){
      encuesta.setId(encuetaId);
      encuestaRepository.save(encuesta);
      return new ResponseEntity<>(HttpStatus.OK);
   }

   @DeleteMapping("/encuestas/{encuestaId}")
   public ResponseEntity<?> eliminarEncuesta(@PathVariable Long encuestaId){
      encuestaRepository.deleteById(encuestaId);
      return new ResponseEntity<>(HttpStatus.OK); 
   } 
}

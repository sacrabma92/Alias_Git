package com.api.rest.api_rest_encuentas.repository;

import com.api.rest.api_rest_encuentas.model.Encuesta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncuestaRepository extends CrudRepository<Encuesta,Long> {

}

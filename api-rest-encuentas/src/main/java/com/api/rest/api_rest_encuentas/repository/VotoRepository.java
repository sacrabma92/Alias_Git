package com.api.rest.api_rest_encuentas.repository;

import com.api.rest.api_rest_encuentas.model.Encuesta;
import com.api.rest.api_rest_encuentas.model.Voto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends CrudRepository<Voto,Long> {

}

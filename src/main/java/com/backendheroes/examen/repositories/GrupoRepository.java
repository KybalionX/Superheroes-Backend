package com.backendheroes.examen.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backendheroes.examen.models.GrupoModel;

@Repository
public interface GrupoRepository extends CrudRepository<GrupoModel, Long> {
	
	public abstract ArrayList<GrupoModel> findByNombre(String nombre);
	
	
}

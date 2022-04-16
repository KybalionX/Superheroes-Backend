package com.backendheroes.examen.repositories;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import com.backendheroes.examen.models.*;

@Repository
public interface SuperheroeRepository extends CrudRepository<SuperheroeModel, Long> {
	
	public abstract ArrayList<SuperheroeModel> findByNombre(String nombre);
	public abstract ArrayList<SuperheroeModel> findByGrupo(GrupoModel grupo);

}

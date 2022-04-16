package com.backendheroes.examen.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendheroes.examen.models.GrupoModel;
import com.backendheroes.examen.repositories.GrupoRepository;

@Service
public class GrupoService {
	
	@Autowired
	GrupoRepository grupoRepository;
	
	public ArrayList<GrupoModel> getGrupos(){
		return (ArrayList<GrupoModel>) grupoRepository.findAll();
	}
	
	public Optional<GrupoModel> getGrupoById(Long id){
		return grupoRepository.findById(id);
	}
	
}
package com.backendheroes.examen.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendheroes.examen.models.GrupoModel;
import com.backendheroes.examen.models.SuperheroeModel;
import com.backendheroes.examen.repositories.SuperheroeRepository;

@Service
public class SuperheroeService {
	
	@Autowired
	SuperheroeRepository superheroeRepository;
	
	public ArrayList<SuperheroeModel> getSuperheroes(){
		return (ArrayList<SuperheroeModel>) superheroeRepository.findAll();
	}
	
	public SuperheroeModel addSuperheroe(SuperheroeModel superheroe) {
		return superheroeRepository.save(superheroe);
	}
	
	public Optional<SuperheroeModel> getSuperheroeById(Long id){
		return superheroeRepository.findById(id);
	}
	
	public ArrayList<SuperheroeModel> getSuperheroeByNombre(String name){
		return superheroeRepository.findByNombre(name);
	}
	
	public ArrayList<SuperheroeModel> getSuperheroeByGrupo(GrupoModel grupo){
		return superheroeRepository.findByGrupo(grupo);
	}
	
	public boolean deleteSupeheroe(Long id) {
		try {
			superheroeRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	
}

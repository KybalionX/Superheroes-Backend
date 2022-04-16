package com.backendheroes.examen.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendheroes.examen.models.GrupoModel;
import com.backendheroes.examen.models.SuperheroeModel;
import com.backendheroes.examen.services.GrupoService;

@RestController
@CrossOrigin
@RequestMapping("/grupos")
public class GrupoController {
	
	@Autowired
	GrupoService grupoService;
	
	@GetMapping
	public ArrayList<GrupoModel> getGrupos(){
		return grupoService.getGrupos();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<GrupoModel> getGrupoById(@PathVariable("id") Long id){
		return grupoService.getGrupoById(id);
	}
	

}

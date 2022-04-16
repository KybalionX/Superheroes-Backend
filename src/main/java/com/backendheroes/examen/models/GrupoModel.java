package com.backendheroes.examen.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "grupos")
public class GrupoModel {
	
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	
	private String nombre;
	
	
	
	public GrupoModel(@NotNull Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public GrupoModel(@NotNull Long id) {
		super();
		this.id = id;
	}

	public GrupoModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}

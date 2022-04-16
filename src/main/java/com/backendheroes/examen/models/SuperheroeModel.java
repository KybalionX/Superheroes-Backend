package com.backendheroes.examen.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "superheroes")
public class SuperheroeModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	
	@NotNull
	@NotEmpty
	private String nombre;
	
	private int fuerza;
	private int inteligencia;
	private int velocidad;
	private int valentia;
	private int habilidad;
	
	@JsonFormat
	@JoinColumn(name = "grupo_id", referencedColumnName = "id")
	@OneToOne(cascade = CascadeType.DETACH)
	@NotNull(message = "Grupo no puede ser null")
	private GrupoModel grupo;
	

	public SuperheroeModel() {
		// TODO Auto-generated constructor stub
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

	public int getFuerza() {
		return fuerza;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	public int getInteligencia() {
		return inteligencia;
	}

	public void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getValentia() {
		return valentia;
	}

	public void setValentia(int valentia) {
		this.valentia = valentia;
	}

	public int getHabilidad() {
		return habilidad;
	}

	public void setHabilidad(int habilidad) {
		this.habilidad = habilidad;
	}

	public GrupoModel getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoModel grupo) {
		this.grupo = grupo;
	}
	
	
	
	

}

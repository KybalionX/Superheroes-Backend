package com.backendheroes.examen.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backendheroes.examen.models.GrupoModel;
import com.backendheroes.examen.models.SuperheroeModel;
import com.backendheroes.examen.repositories.GrupoRepository;
import com.backendheroes.examen.services.GrupoService;
import com.backendheroes.examen.services.SuperheroeService;
import com.github.underscore.U;
import com.google.gson.Gson;


@RestController
@CrossOrigin
@RequestMapping("/superheroes")
public class SuperheroeController {

	@Autowired
	SuperheroeService superheroeService;

	@Autowired
	GrupoService grupoService;
	
	Comparator<SuperheroeModel> comparadorHabilidad = (s1, s2) -> Integer.compare(s2.getHabilidad(), s1.getHabilidad());

	@GetMapping
	public ArrayList<SuperheroeModel> getSuperheroes() {
		return superheroeService.getSuperheroes();
	}
	
	@GetMapping(path="/batalla/grupos")
	public HashMap<String, ArrayList<SuperheroeModel>> getGruposOrderedByHabilidad() {
		HashMap<String, ArrayList<SuperheroeModel>> grupos = new HashMap<String, ArrayList<SuperheroeModel>>();
		ArrayList<SuperheroeModel> listaMarvel = superheroeService.getSuperheroeByGrupo(new GrupoModel(Long.valueOf(1)));
		ArrayList<SuperheroeModel> listaDC = superheroeService.getSuperheroeByGrupo(new GrupoModel(Long.valueOf(2)));
		
		listaMarvel.sort(comparadorHabilidad);
		listaDC.sort(comparadorHabilidad);
		
		grupos.put("equipoMarvel", listaMarvel);
		grupos.put("equipoDC", listaDC);
		
		return grupos;
	}
	
	
	@GetMapping(path="/batalla/enfrentamientos")
	public List<List<SuperheroeModel>> getEnfrentamientos() {
		
		ArrayList<SuperheroeModel> listaMarvel = superheroeService.getSuperheroeByGrupo(new GrupoModel(Long.valueOf(1)));
		ArrayList<SuperheroeModel> listaDC = superheroeService.getSuperheroeByGrupo(new GrupoModel(Long.valueOf(2)));
		listaMarvel.sort(comparadorHabilidad);
		listaDC.sort(comparadorHabilidad);
		
		List<List<SuperheroeModel>> modeler = U.zip(listaMarvel, listaDC);
		
		return modeler;
	}

	
	@GetMapping(path="/batalla/resultados")
	public Map<String, List> resultadosBatalla() {
		
		Map<String, List> resultados = new HashMap<String, List>();
		
		ArrayList<SuperheroeModel> listaMarvel = superheroeService.getSuperheroeByGrupo(new GrupoModel(Long.valueOf(1)));
		ArrayList<SuperheroeModel> listaDC = superheroeService.getSuperheroeByGrupo(new GrupoModel(Long.valueOf(2)));
		
		listaMarvel.sort(comparadorHabilidad);
		listaDC.sort(comparadorHabilidad);

		if(listaMarvel.size() == 0 || listaDC.size() == 0){
			ArrayList<String> response = new ArrayList<String>();
			response.add("Alguno de los grupos no tiene superheroes suficientes para disputar la batalla!");
			resultados.put("noBattle", response);
			return resultados;
		}
		
		
		List<SuperheroeModel> ganadores = new ArrayList<SuperheroeModel>();
		List<SuperheroeModel> perdedores = new ArrayList<SuperheroeModel>();
		List<List<SuperheroeModel>> empates = new ArrayList<List<SuperheroeModel>>();
		
		U.zip(listaMarvel, listaDC).forEach((e)->{
			try {
				
				int puntosSuperheroe1 = 0;
				int puntosSuperheroe2 = 0;
				SuperheroeModel s1 = e.get(0);
				SuperheroeModel s2 = e.get(1);
				
				if((s1.getFuerza() - s2.getFuerza()) > 4) {
					puntosSuperheroe1++;
				}else if((s2.getFuerza() - s1.getFuerza()) > 4) {
					puntosSuperheroe2++;
				}
				
				if((s1.getInteligencia() - s2.getInteligencia()) > 3 && (s1.getFuerza() - s2.getFuerza()) > 1) {
					puntosSuperheroe1++;
				}else if((s2.getInteligencia() - s1.getInteligencia()) > 3 && (s2.getFuerza() - s1.getFuerza()) > 1) {
					puntosSuperheroe2++;
				}
				
				if(s1.getHabilidad() > s2.getHabilidad()) {
					puntosSuperheroe1++;
				}else if(s2.getHabilidad() > s1.getHabilidad()) {
					puntosSuperheroe2++;
				}
				
				if(puntosSuperheroe1 > puntosSuperheroe2) {
					ganadores.add(s1);
					perdedores.add(s2);
				}else if(puntosSuperheroe2 > puntosSuperheroe1) {
					ganadores.add(s2);
					perdedores.add(s1);
				}else if(puntosSuperheroe1 == puntosSuperheroe2) {
					List<SuperheroeModel> empate = new ArrayList<SuperheroeModel>();
					empate.add(s1);
					empate.add(s2);
					empates.add(empate);
				}
				
			} catch (IndexOutOfBoundsException  exception) {
				
			}
		});
		
		resultados.put("ganadores", ganadores);
		resultados.put("perdedores", perdedores);
		resultados.put("empates", empates);
		
		
		final Map<String, Integer> resultadoGrupos = U.countBy(ganadores, new Function<SuperheroeModel, String>() {

			@Override
			public String apply(SuperheroeModel t) {
				return t.getGrupo().getNombre();
			}
		});

		if(resultadoGrupos.get("MARVEL") == null){
			resultadoGrupos.put("MARVEL", 0);
		}

		if(resultadoGrupos.get("DC COMICS") == null){
			resultadoGrupos.put("DC COMICS", 0);
		}
		
		List<Map<String, Integer>> marcador = new ArrayList<Map<String,Integer>>();
		marcador.add(resultadoGrupos);
		
		resultados.put("marcador", marcador);
		
		
		
		return resultados;
	}
	
	
	@PostMapping
	public HashMap<String, String> saveSuperheroe(@RequestBody SuperheroeModel superheroe) {

		HashMap<String, String> response = new HashMap<>();

		try {
			superheroeService.addSuperheroe(superheroe);
			response.put("title", "Hecho!");
			response.put("text", "Has guardado al superheroe " + superheroe.getNombre() + " correctamente!");
			response.put("icon", "success");
		} catch (Exception e) {
			response.put("title", "Ups!");
			response.put("text", "Ha sucedido un error al intentar crear el superheroe: " + e.getMessage());
			response.put("icon", "error");
		}

		return response;

	}

	@GetMapping(path = "/{id}")
	public Optional<SuperheroeModel> getSuperheroeById(@PathVariable("id") Long id) {
		return superheroeService.getSuperheroeById(id);
	}

	@GetMapping(path = "/")
	public ArrayList<SuperheroeModel> getSuperheroeByName(@RequestParam("name") String name) {
		return superheroeService.getSuperheroeByNombre(name);
	}

	@DeleteMapping(path = "/{id}")
	public HashMap<String, String> deleteSuperheroeById(@PathVariable("id") Long id) {
		HashMap<String, String> response = new HashMap<>();
		try {
			superheroeService.deleteSupeheroe(id);
			response.put("title", "Hecho!");
			response.put("text", "Has eliminado al superheroe correctamente!");
			response.put("icon", "success");
		} catch (Exception e) {
			response.put("title", "Ups!");
			response.put("text", "Ha sucedido un error al intentar eliminar el superheroe: " + e.getMessage());
			response.put("icon", "error");
		}
		
		return response;
	}

}

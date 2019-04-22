package com.br.starwars.service;


import com.br.starwars.component.Messages;
import com.br.starwars.controller.PlanetController;
import com.br.starwars.exception.ObjectNotFoundException;
import com.br.starwars.helper.PlanetHelper;
import com.br.starwars.model.Planet;
import com.br.starwars.repository.PlanetRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.br.starwars.component.Messages.PLANET_NOT_FOUND;
import static java.util.stream.Collectors.toList;


/**
 * Funcionalidades desejadas:
 *
 * - Adicionar um planeta (com nome, clima e terreno) ok
 * - Listar planetas ok
 * - Buscar por nome
 * - Buscar por ID
 * - Remover planeta
 */

@Service
@AllArgsConstructor
public class PlanetService {


    private static final Logger logger = LogManager.getLogger(PlanetController.class);

    private final PlanetSwapiService planetSwapiService;

    private final PlanetRepository planetRepository;

    private final PlanetHelper planetHelper;

    public Planet savePlanet(Planet planet){
        planetHelper.planetIsValid(planet);
        planet.setApparitionCount(planetSwapiService.getApparitionCountFilms(planet.getName()));
        planet = planetRepository.insert(planet);
        return planet;
    }

    public ResponseEntity<List<?>> listPlanets(){

        List<Planet> planets = planetRepository.findAll().stream().peek( planet -> planet.setApparitionCount(planetSwapiService.getApparitionCountFilms(planet.getName()))).collect(toList());
        logger.info("[SERVICE] - Listagem dos planetas planets={}",planets);
        return planets.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(planets);
    }


    public Planet findById(String id) {
        return planetRepository.findById(id).map(p -> {p.setApparitionCount(planetSwapiService.getApparitionCountFilms(p.getName())); return p;})
                .orElseThrow(() -> new ObjectNotFoundException(PLANET_NOT_FOUND));
    }

    public Planet findByIdOrName(String id, String name){
        return planetRepository.findByIdOrName(id,name).map(planet -> {planet.setApparitionCount(planetSwapiService.getApparitionCountFilms(planet.getName()));return planet;})
                .orElseThrow(()-> new ObjectNotFoundException(Messages.PLANET_NOT_FOUND));
    }

    public void deleteByIdOrByName(String id,String name){
        Planet planet = planetRepository.findByIdOrName(id,name).orElseThrow( () -> new ObjectNotFoundException(Messages.PLANET_NOT_FOUND));
        planetRepository.deleteByIdOrName(planet.getId(),planet.getName());
    }

}

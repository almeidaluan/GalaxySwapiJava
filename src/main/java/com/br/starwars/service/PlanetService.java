package com.br.starwars.service;


import com.br.starwars.exception.GenericException;
import com.br.starwars.helper.PlanetHelper;
import com.br.starwars.model.Planet;
import com.br.starwars.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.br.starwars.component.Messages.PLANET_NOT_FOUND;


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
public class PlanetService {

    @Autowired
    private PlanetSwapiService planetSwapiService;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private PlanetHelper planetHelper;

    public Planet savePlanet(Planet planet){
        planetHelper.planetIsValid(planet);
        planet.setApparitionCount(planetSwapiService.getApparitionCountFilms(planet.getName()));
        planet = planetRepository.insert(planet);
        return planet;
    }

    public ResponseEntity<List<Planet>> listPlanets(){

        List<Planet> planets = planetRepository.findAll();
        return planets.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(planets);
    }


    public Planet findById(String id) {
        return planetRepository.findById(id).map(p -> {p.setApparitionCount(planetSwapiService.getApparitionCountFilms(p.getName())); return p;})
                .orElseThrow(() -> new GenericException(PLANET_NOT_FOUND));
    }

}

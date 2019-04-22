package com.br.starwars.controller;

import com.br.starwars.model.Planet;

import com.br.starwars.service.PlanetService;

import lombok.RequiredArgsConstructor;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.ws.Response;

import static com.br.starwars.component.AppConstants.PLANET;
import static com.br.starwars.component.AppConstants.PLANETS;
import static com.br.starwars.component.AppConstants.PLANET_PARAM_;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(PLANETS)
@RequiredArgsConstructor
public class PlanetController {

    private final PlanetService planetService;
    private static final Logger logger = LogManager.getLogger(PlanetController.class);

    @PostMapping(PLANET)
    public ResponseEntity<Planet> createPlanet(@RequestBody Planet body){

        Planet planet =  planetService.savePlanet(body);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(planet.getId()).toUri(); //adiciono location do planeta
        return created(uri).body(planet);
    }

    @GetMapping(PLANET)
    public ResponseEntity<List<?>> findAll(){

        logger.info("[CONTROLLER] - Iniciando o fluxo de listagem de planetas");
        return planetService.listPlanets();

    }

    @GetMapping("/planets/{id}")
    public ResponseEntity<Planet> findById(@PathVariable String id){

        Planet planet = planetService.findById(id);

        return ok().body(planet);
    }


    @GetMapping(PLANET + PLANET_PARAM_)
    public ResponseEntity<Planet> findByIdOrName(@RequestParam(value="id",defaultValue = "") String id, @RequestParam(value="name",defaultValue = "") String name){
        Planet planet = planetService.findByIdOrName(id,name);
        return ResponseEntity.ok().body(planet);
    }

    @DeleteMapping("/planet")
    @ResponseStatus(NO_CONTENT)
    public void deleteByIdOrByName(@RequestParam(value = "id",defaultValue = "") String id,@RequestParam(value = "name",defaultValue = "") String name){
        planetService.deleteByIdOrByName(id,name);
    }


}

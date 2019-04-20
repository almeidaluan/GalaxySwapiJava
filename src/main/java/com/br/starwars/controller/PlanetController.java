package com.br.starwars.controller;

import com.br.starwars.model.Planet;

import com.br.starwars.service.PlanetService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PlanetController {

    private final PlanetService planetService;

    @PostMapping("/planets")
    public ResponseEntity<Planet> createPlanet(@RequestBody Planet body){

        Planet planet =  planetService.savePlanet(body);
        return status(CREATED).body(planet);
    }

    @GetMapping("/planets")
    public ResponseEntity<List<Planet>> findAll(){


        return planetService.listPlanets();

    }

    @GetMapping("/planet/{id}")
    public ResponseEntity<Planet> findById(@PathVariable String id){

        Planet planet = planetService.findById(id);

        return ok().body(planet);
    }

}

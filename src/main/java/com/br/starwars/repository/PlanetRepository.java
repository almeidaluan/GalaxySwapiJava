package com.br.starwars.repository;

import com.br.starwars.model.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PlanetRepository extends MongoRepository<Planet,String> {

    Planet findByName(String name);

    Optional<Planet> findById(String id);
}

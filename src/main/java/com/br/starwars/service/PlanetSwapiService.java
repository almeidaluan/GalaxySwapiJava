package com.br.starwars.service;

import com.br.starwars.client.PlanetSwapiClient;
import com.br.starwars.exception.ObjectNotFoundException;
import com.br.starwars.model.PlanetSwapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.br.starwars.component.Messages.PLANET_NOT_FOUND;

@Service
public class PlanetSwapiService {

    @Autowired
    private  PlanetSwapiClient planetSwapiClient;

    //Pegando o planeta e verifica a quantidade de filmes que ele possui no serviço
    //Classe PlanetSwapi eh apenas para mapear os resultados do serviço e retornar
    // A classe persistida vai ser a planet
    public Integer getApparitionCountFilms(String name){
        //pega o planeta do serviço
        PlanetSwapi.Result result= planetSwapiClient.getPlanet(name).getResults().stream()
                .filter(f -> f.getName().equals(name)).findFirst()
                .orElseThrow(() -> new ObjectNotFoundException(PLANET_NOT_FOUND));
        //pega a quantidade de filmes do planeta
        return result.getFilms().size();
    }
}

package com.br.starwars.client;

import com.br.starwars.model.PlanetSwapi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name="planetSwapi", url="${planetswapi.url.planets}")
public interface PlanetSwapiClient {

    @RequestMapping(
            method = GET,
            headers = {
                    "User-Agent=IdentificadorUSerAgent"
            },
            produces = "application/json",
            value = "?search={name}")
    PlanetSwapi getPlanet(@RequestParam("name") String name);
}

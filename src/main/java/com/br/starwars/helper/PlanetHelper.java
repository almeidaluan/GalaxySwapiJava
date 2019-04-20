package com.br.starwars.helper;


import com.br.starwars.exception.GenericException;
import com.br.starwars.model.Planet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import static com.br.starwars.component.Messages.PLANET_INVALIDE_CLIMATE;
import static com.br.starwars.component.Messages.PLANET_INVALIDE_NAME;
import static com.br.starwars.component.Messages.PLANET_INVALIDE_TERRAIN;

@Component
public class PlanetHelper {

    /**
     * IsBlank Validate if is empty,null or whitespace only
     * @param planet
     */
    public void planetIsValid(Planet planet){
        if(StringUtils.isBlank(planet.getName())) throw new GenericException(PLANET_INVALIDE_NAME);
        if(StringUtils.isBlank(planet.getClimate())) throw new GenericException(PLANET_INVALIDE_CLIMATE);
        if(StringUtils.isBlank(planet.getTerrain())) throw new GenericException(PLANET_INVALIDE_TERRAIN);
    }
}

package com.br.starwars.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanetSwapi {

    private List<Result> results;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Result{
        private String name;
        private List<String> films;

    }
}

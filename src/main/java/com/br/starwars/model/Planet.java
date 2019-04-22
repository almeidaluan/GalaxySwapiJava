package com.br.starwars.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Planet {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private String climate;

    private String terrain;

    @Transient
    private Integer apparitionCount;

}

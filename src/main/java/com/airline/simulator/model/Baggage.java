package com.airline.simulator.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Entity class for Baggage Entity.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Baggage {

    private int id;
    private int weight;
    private String weightUnit;
    private int pieces;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", weight=" + weight +
                ", weightUnit='" + weightUnit + '\'' +
                ", pieces=" + pieces +
                '}';
    }
}

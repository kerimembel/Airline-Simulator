package com.airline.simulator.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test of {@link Cargo} Entity.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

public class CargoTest {

    private Cargo cargo;
    private Baggage baggage;
    private List<Baggage> baggageList;

    @Before
    public void setUp() {
        baggageList = new ArrayList<>();
        cargo = new Cargo();

        baggage = new Baggage();
        baggage.setId(0);
        baggage.setWeight(814);
        baggage.setWeightUnit("lb");
        baggage.setPieces(581);
        baggageList.add(baggage);

        cargo.setCargo(baggageList);

        baggage = new Baggage();
        baggage.setId(1);
        baggage.setWeight(1);
        baggage.setWeightUnit("lb");
        baggage.setPieces(1);
        baggageList.add(baggage);

        cargo.setFlightId(0);
        cargo.setBaggage(baggageList);
    }

    @Test
    public void setTest(){

        Cargo testEntity = new Cargo();

        testEntity.setFlightId(0);
        testEntity.setCargo(baggageList);
        testEntity.setBaggage(baggageList);

        Assert.assertEquals(cargo,testEntity);
    }

    @Test
    public void baggageWeightTest(){

        Assert.assertEquals(815,cargo.getBaggageWeight());
    }

    @Test
    public void cargoWeightTest(){

        Assert.assertEquals(814,cargo.getCargoWeight());
    }

    @Test
    public void totalWeightTest(){

        Assert.assertEquals(1629,cargo.getTotalWeight());
    }

}
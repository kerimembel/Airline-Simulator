package aero.smart4aviation.task.model;

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
        cargo = new Cargo();
        baggage = new Baggage();
        baggageList = new ArrayList<>();

        baggage.setId(0);
        baggage.setWeight(814);
        baggage.setWeightUnit("lb");
        baggage.setPieces(581);
        baggageList.add(baggage);

        cargo.setFlightId(0);
        cargo.setCargo(baggageList);
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

}
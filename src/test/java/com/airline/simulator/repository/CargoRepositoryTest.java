package com.airline.simulator.repository;

import com.airline.simulator.model.Baggage;
import com.airline.simulator.model.Cargo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Unit test for {@link CargoRepository}
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */
public class CargoRepositoryTest {

    private CargoRepository repository;
    private List<Cargo> cargos;
    private Cargo cargo;
    private Baggage baggage;
    private List<Integer> flightIds;

    @Before
    public void setUp() {

        configureEntity();
        repository = new CargoRepository();
        cargos = repository.readJson();
    }

    @Test
    public void readJsonTest(){

        Assert.assertEquals(cargo.getFlightId(),cargos.get(0).getFlightId());
        Assert.assertEquals(baggage,cargos.get(0).getBaggage().get(0));
    }

    @Test
    public void fileNotFoundTest(){

        URL url = getClass().getResource("NOT_EXISTING_FILE");
        repository = new CargoRepository(url);
        cargos = repository.readJson();

        Assert.assertNull(cargos);
    }

    @Test
    public void findCargoTest(){

        Assert.assertNotNull(repository.findCargoByFlightId(0));
        Assert.assertNotNull(repository.findCargoByFlightId(flightIds));

    }

    @Test
    public void findCargoNullTest(){

        flightIds.clear();
        flightIds.add(-1);
        Assert.assertNull(repository.findCargoByFlightId(-1));
        Assert.assertEquals(Collections.emptyList(),repository.findCargoByFlightId(flightIds));

    }



    private void configureEntity(){

        baggage = new Baggage();
        flightIds = new ArrayList<>();

        cargo = new Cargo();
        cargo.setFlightId(0);
        baggage.setId(0);
        baggage.setWeight(814);
        baggage.setWeightUnit("lb");
        baggage.setPieces(581);

        flightIds.add(0);
        flightIds.add(1);
    }

}
package aero.smart4aviation.task.repository;

import aero.smart4aviation.task.model.Baggage;
import aero.smart4aviation.task.model.Cargo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
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
    }

    @Test
    public void findCargoNullTest(){

        Assert.assertNull(repository.findCargoByFlightId(-1));
    }

    private void configureEntity(){

        cargo = new Cargo();
        baggage = new Baggage();

        cargo.setFlightId(0);

        baggage.setId(0);
        baggage.setWeight(814);
        baggage.setWeightUnit("lb");
        baggage.setPieces(581);
    }

}
package aero.smart4aviation.task.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Unit test for {@link CargoRepository}
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */
public class CargoRepositoryTest {

    private CargoRepository repository;

    @Before
    public void setUp(){

        repository = new CargoRepository();
    }

    @Test
    public void test(){
        Assert.assertNotNull(repository);
    }




}
package aero.smart4aviation.task.model;

import org.hibernate.mapping.Bag;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test of {@link Baggage} Entity.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */
public class BaggageTest {

    private Baggage baggage;

    @Before
    public void setUp() {

        baggage = new Baggage();
        baggage.setId(0);
        baggage.setWeight(814);
        baggage.setWeightUnit("lb");
        baggage.setPieces(581);
    }

    @Test
    public void setTest(){

        Baggage testEntity = new Baggage();
        testEntity.setId(0);
        testEntity.setWeight(814);
        testEntity.setWeightUnit("lb");
        testEntity.setPieces(581);

        Assert.assertEquals(baggage,testEntity);
    }

}
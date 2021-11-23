package aero.smart4aviation.task.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Unit test of {@link Flight} Entity.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */
public class FlightTest {

    Flight flight;

    @Before
    public void setUp(){

        flight = new Flight();
        flight.setFlightId(0);
        flight.setFlightNumber(1166);
        flight.setDepartureAirportIATACode("SEA");
        flight.setArrivalAirportIATACode("MIT");
        flight.setDepartureDate(ZonedDateTime.parse("2019-02-05T08:39:00-03:00"));
        System.out.println(flight.getDepartureDate());
    }

    @Test
    public void setTest(){

        Flight testEntity = new Flight();
        testEntity.setFlightId(0);
        testEntity.setFlightNumber(1166);
        testEntity.setDepartureAirportIATACode("SEA");
        testEntity.setArrivalAirportIATACode("MIT");
        testEntity.setDepartureDate(ZonedDateTime.parse("2019-02-05T08:39:00-03:00"));

        Assert.assertEquals(flight,testEntity);
    }

}
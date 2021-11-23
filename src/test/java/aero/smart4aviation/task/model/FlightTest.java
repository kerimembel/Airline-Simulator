package aero.smart4aviation.task.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
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
    DateTimeFormatter parser;

    @Before
    public void setUp(){

        parser = DateTimeFormatter.ISO_DATE_TIME;

        flight = new Flight();
        flight.setFlightId(0);
        flight.setFlightNumber(1166);
        flight.setArrivalAirportIATACode("SEA");
        flight.setArrivalAirportIATACode("MIT");
        flight.setDepartureDate(LocalDateTime.parse("2019-02-05T08:39:00-03:00", parser));
    }

    @Test
    public void setTest(){

        Flight testEntity = new Flight();
        testEntity.setFlightId(0);
        testEntity.setFlightNumber(1166);
        testEntity.setArrivalAirportIATACode("SEA");
        testEntity.setArrivalAirportIATACode("MIT");
        testEntity.setDepartureDate(LocalDateTime.parse("2019-02-05T08:39:00-03:00", parser));

        Assert.assertEquals(flight,testEntity);
    }

}
package aero.smart4aviation.task.repository;

import aero.smart4aviation.task.model.Flight;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Unit test of {@link FlightRepository}
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */
public class FlightRepositoryTest {

    private FlightRepository repository;
    private List<Flight> flights;
    private Flight flight;

    DateTimeFormatter parser;

    @Before
    public void setUp() {

        parser = DateTimeFormatter.ISO_DATE_TIME;
        configureEntity();

        repository = new FlightRepository();
        flights = repository.readJson();
    }

    @Test
    public void readJsonTest(){

        Assert.assertEquals(flight,flights.get(0));
    }

    @Test
    public void fileNotFoundTest(){

        URL url = getClass().getResource("NOT_EXISTING_FILE");
        repository = new FlightRepository(url);
        flights = repository.readJson();

        Assert.assertNull(flights);
    }

    private void configureEntity(){

        flight = new Flight();
        flight.setFlightId(0);
        flight.setFlightNumber(1166);
        flight.setDepartureAirportIATACode("SEA");
        flight.setArrivalAirportIATACode("MIT");
        flight.setDepartureDate(ZonedDateTime.parse("2019-02-05T08:39:00-03:00"));
    }




}
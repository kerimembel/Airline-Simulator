package com.airline.simulator.repository;

import com.airline.simulator.model.Flight;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.time.ZonedDateTime;
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
    private ZonedDateTime dateTime;

    @Before
    public void setUp() {

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

    @Test
    public void findFlightByNumberAndDateTest(){

        Assert.assertNotNull(repository.findFlightByNumberAndDate(1166,dateTime.toString()));
        Assert.assertNull(repository.findFlightByNumberAndDate(11,dateTime.toString()));
    }

    @Test
    public void findFlightByNumberTest(){

        Assert.assertNotNull(repository.findFlightByNumber(1166));
        Assert.assertNull(repository.findFlightByNumber(11));
    }

    @Test
    public void findFlightByDateTest(){

        Assert.assertNotNull(repository.findFlightByDate(dateTime.toString()));
        Assert.assertNull(repository.findFlightByDate(""));
    }

    private void configureEntity(){

        dateTime = ZonedDateTime.parse("2019-02-05T08:39:00-03:00");

        flight = new Flight();
        flight.setFlightId(0);
        flight.setFlightNumber(1166);
        flight.setDepartureAirportIATACode("SEA");
        flight.setArrivalAirportIATACode("MIT");
        flight.setDepartureDate(dateTime);
    }




}
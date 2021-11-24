package aero.smart4aviation.task.service;

import aero.smart4aviation.task.model.*;
import aero.smart4aviation.task.repository.CargoRepository;
import aero.smart4aviation.task.repository.FlightRepository;
import org.hibernate.mapping.Bag;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Unit test for {@link FlightService}
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */
@RunWith(MockitoJUnitRunner.class)
public class FlightServiceTest {

    private FlightService service;
    private Flight flight;
    private Cargo cargo;
    private Baggage baggage;
    private List<Baggage> baggageList;
    private ZonedDateTime dateTime;

    @Mock
    private FlightRepository flightRepository;
    @Mock
    private CargoRepository cargoRepository;

    @Before
    public void setUp(){

        configureEntities();

        when(flightRepository.findFlightByNumberAndDate(1166,dateTime.toString()))
                .thenReturn(flight);

        when(cargoRepository.findCargoByFlightId(0)).thenReturn(cargo);

        service = new FlightService(flightRepository,cargoRepository);
    }

    @Test
    public void flightInformationTest(){

        FlightResponse response = service.flightInformation(1166,dateTime.toString());

        Assert.assertEquals(cargo.getCargoWeight(),response.getCargoWeight());
        Assert.assertEquals(cargo.getBaggageWeight(),response.getBaggageWeight());
        Assert.assertEquals(cargo.getTotalWeight(),response.getTotalWeight());
        Assert.assertEquals("Success",response.getDetail().getMessage());
        Assert.assertEquals(false,response.getDetail().isError());
    }

    @Test
    public void flightInformationNotFoundTest(){

        when(flightRepository.findFlightByNumberAndDate(0000,dateTime.toString()))
                .thenReturn(null);

        FlightResponse response = service.flightInformation(0000,dateTime.toString());

        Assert.assertEquals(0,response.getCargoWeight());
        Assert.assertEquals(0,response.getBaggageWeight());
        Assert.assertEquals(0,response.getTotalWeight());
        Assert.assertEquals(true,response.getDetail().isError());

    }


    public void configureEntities(){

        dateTime = ZonedDateTime.parse("2019-02-05T08:39:00-03:00");

        flight = new Flight();
        flight.setFlightId(0);
        flight.setFlightNumber(1166);
        flight.setDepartureAirportIATACode("SEA");
        flight.setArrivalAirportIATACode("MIT");
        flight.setDepartureDate(dateTime);

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
}
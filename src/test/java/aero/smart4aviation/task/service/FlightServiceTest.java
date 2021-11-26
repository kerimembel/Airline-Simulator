package aero.smart4aviation.task.service;

import aero.smart4aviation.task.model.*;
import aero.smart4aviation.task.repository.CargoRepository;
import aero.smart4aviation.task.repository.FlightRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
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
    private List<Flight> flightList;
    private Cargo cargo;
    private List<Cargo> cargoList;
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

        when(flightRepository.findFlightByNumberAndDate(anyInt(), anyString()))
                .thenReturn(flight);

        when(flightRepository.findFlightByNumber(anyInt()))
                .thenReturn(flight);

        when(flightRepository.findFlightByAirportCode(anyString()))
                .thenReturn(flightList);

        when(flightRepository.findFlightByAirportCodeAndDate(eq("SEA"), anyString()))
                .thenReturn(flightList);

        when(cargoRepository.findCargoByFlightId(0)).thenReturn(cargo);
        when(cargoRepository.findCargoByFlightId(anyList())).thenReturn(cargoList);


        service = new FlightService(flightRepository,cargoRepository);
    }

    @Test
    public void weightInformationTest(){

        WeightResponse response = service.weightInformation(1166,dateTime.toString());

        Assert.assertEquals(cargo.getCargoWeight(),response.getCargoWeight());
        Assert.assertEquals(cargo.getBaggageWeight(),response.getBaggageWeight());
        Assert.assertEquals(cargo.getTotalWeight(),response.getTotalWeight());
        Assert.assertEquals("Success",response.getDetail().getMessage());
        Assert.assertEquals(false,response.getDetail().isError());
    }


    @Test
    public void weightInformationByFlightNumberTest(){

        when(flightRepository.findFlightByNumber(anyInt()))
                .thenReturn(flight);

        WeightResponse response = service.weightInformation(1166,null);

        Assert.assertEquals(cargo.getCargoWeight(),response.getCargoWeight());
        Assert.assertEquals(cargo.getBaggageWeight(),response.getBaggageWeight());
        Assert.assertEquals(cargo.getTotalWeight(),response.getTotalWeight());
        Assert.assertEquals("Success",response.getDetail().getMessage());
        Assert.assertEquals(false,response.getDetail().isError());
    }

    @Test
    public void countInformationTest(){

        CountResponse response = service.countInformation("SEA",dateTime.toString());

        Assert.assertEquals(0,response.getArrivingBaggageCount());
        Assert.assertEquals(0,response.getArrivingFlightCount());
        Assert.assertEquals(581,response.getDepartingBaggageCount());
        Assert.assertEquals(1,response.getDepartingFlightCount());
        Assert.assertEquals("Success",response.getDetail().getMessage());
        Assert.assertEquals(false,response.getDetail().isError());
    }


    @Test
    public void countInformationByFlightNumberTest(){

        when(flightRepository.findFlightByAirportCode(anyString()))
                .thenReturn(flightList);

        CountResponse response = service.countInformation("SEA",null);

        Assert.assertEquals(0,response.getArrivingBaggageCount());
        Assert.assertEquals(0,response.getArrivingFlightCount());
        Assert.assertEquals(581,response.getDepartingBaggageCount());
        Assert.assertEquals(1,response.getDepartingFlightCount());
        Assert.assertEquals("Success",response.getDetail().getMessage());
        Assert.assertEquals(false,response.getDetail().isError());
    }

    @Test
    public void flightInformationNotFoundTest(){

        when(flightRepository.findFlightByNumberAndDate(0000,dateTime.toString()))
                .thenReturn(null);

        WeightResponse response = service.weightInformation(0000,dateTime.toString());

        Assert.assertEquals(0,response.getCargoWeight());
        Assert.assertEquals(0,response.getBaggageWeight());
        Assert.assertEquals(0,response.getTotalWeight());
        Assert.assertEquals(true,response.getDetail().isError());
    }

    @Test
    public void getBaggageCountTest(){

        long result = service.getBaggageCount(cargoList,flight.getFlightId());

        Assert.assertEquals(581, result);
    }

    @Test
    public void getFlightByNumberTest(){

        Flight result = service.getFlight(flight.getFlightId(), dateTime.toString());

        Assert.assertEquals(flight,result);

        result = service.getFlight(flight.getFlightId(), null);

        Assert.assertEquals(flight,result);
    }

    @Test
    public void getFlightByAirportCodeTest(){

        List<Flight> result = service.getFlight(flight.getDepartureAirportIATACode(),dateTime.toString());

        Assert.assertEquals(flight, result.get(0));

        result = service.getFlight(flight.getArrivalAirportIATACode(), null);

        Assert.assertEquals(flight, result.get(0));
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

        flightList = new ArrayList<>();
        cargoList = new ArrayList<>();
        flightList.add(flight);
        cargoList.add(cargo);
    }
}
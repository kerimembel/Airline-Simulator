package com.airline.simulator.service;

import com.airline.simulator.model.Cargo;
import com.airline.simulator.model.CountResponse;
import com.airline.simulator.model.Flight;
import com.airline.simulator.model.WeightResponse;
import com.airline.simulator.repository.CargoRepository;
import com.airline.simulator.repository.FlightRepository;
import com.airline.simulator.constants.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for flight operations.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public class FlightService {

    private static final Logger logger = Logger.getLogger(FlightService.class);

    private FlightRepository flightRepository;
    private CargoRepository cargoRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository,CargoRepository cargoRepository){

        this.flightRepository = flightRepository;
        this.cargoRepository = cargoRepository;
    }

    public WeightResponse weightInformation(int flightNumber, String departureDate){

        WeightResponse response = new WeightResponse();

        Flight flight = getFlight(flightNumber,departureDate);

        if(flight == null){
            logger.error(String.format(Constants.FLIGHT_NUMBER_NOT_FOUND, flightNumber, departureDate));
            response.setIsError(true);
            response.setMessage(String.format(Constants.FLIGHT_NUMBER_NOT_FOUND, flightNumber, departureDate));
            response.setReturnCode(404);
            return response;
        }

        Cargo cargo = cargoRepository.findCargoByFlightId(flight.getFlightId());
        if(cargo == null){
            logger.error(String.format(Constants.CARGO_NOT_FOUND,flight.getFlightId()));
            response.setIsError(true);
            response.setMessage(String.format(Constants.CARGO_NOT_FOUND,flight.getFlightId()));
            response.setReturnCode(404);
            return response;
        }

        response.setCargoWeight(cargo.getCargoWeight());
        response.setBaggageWeight(cargo.getBaggageWeight());
        response.setTotalWeight(cargo.getTotalWeight());

        return response;
    }

    public CountResponse countInformation(String airportCode, String departureDate) {

        CountResponse response = new CountResponse();

        List<Flight> flights  = getFlight(airportCode, departureDate);
        if(flights.isEmpty()){
            logger.error(String.format(Constants.FLIGHT_AIRPORT_CODE_NOT_FOUND, departureDate , airportCode));
            response.setIsError(true);
            response.setMessage(String.format(Constants.FLIGHT_AIRPORT_CODE_NOT_FOUND, departureDate , airportCode));
            response.setReturnCode(404);
            return response;
        }

        List<Integer> flightIDs = flights.stream().map(Flight::getFlightId).collect(Collectors.toList());
        List<Cargo> cargos = cargoRepository.findCargoByFlightId(flightIDs);
        if(cargos.isEmpty()){
            logger.error(String.format(Constants.CARGO_NOT_FOUND,flightIDs));
            response.setIsError(true);
            response.setMessage(String.format(Constants.CARGO_NOT_FOUND,flightIDs));
            response.setReturnCode(404);
            return response;
        }

        int arrivingFlightCount = 0;
        int departingFlightCount = 0;
        long departingBaggageCount = 0;
        long arrivingBaggageCount = 0;

        for(Flight flight: flights){

            if(flight.getArrivalAirportIATACode().equalsIgnoreCase(airportCode)){

                arrivingFlightCount += 1;
                arrivingBaggageCount += getBaggageCount(cargos,flight.getFlightId());

            } else if(flight.getDepartureAirportIATACode().equalsIgnoreCase(airportCode)){

                departingFlightCount += 1;
                departingBaggageCount += getBaggageCount(cargos,flight.getFlightId());
            }
        }

        response.setArrivingFlightCount(arrivingFlightCount);
        response.setDepartingFlightCount(departingFlightCount);
        response.setDepartingBaggageCount(departingBaggageCount);
        response.setArrivingBaggageCount(arrivingBaggageCount);

        return response;
    }

    public long getBaggageCount(List<Cargo> cargos, int flightID){

        try{

            return cargos.stream()
                    .filter(cargo -> flightID == cargo.getFlightId())
                    .findFirst()
                    .orElse(null)
                    .getBaggageCount();
        } catch (NullPointerException ex){
            logger.error(String.format(Constants.CARGO_NOT_FOUND, flightID));
            return 0;
        }
    }

    public Flight getFlight(int flightNumber, String departureDate){

       if(departureDate == null)
            return flightRepository.findFlightByNumber(flightNumber);

       else
            return flightRepository.findFlightByNumberAndDate(flightNumber,departureDate);
    }


    public List<Flight> getFlight(String airportCode, String departureDate) {

      if(departureDate == null)
            return flightRepository.findFlightByAirportCode(airportCode);

      else
            return flightRepository.findFlightByAirportCodeAndDate(airportCode,departureDate);
    }


}

package aero.smart4aviation.task.service;

import aero.smart4aviation.task.model.Cargo;
import aero.smart4aviation.task.model.Flight;
import aero.smart4aviation.task.model.FlightResponse;
import aero.smart4aviation.task.repository.CargoRepository;
import aero.smart4aviation.task.repository.FlightRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

import static aero.smart4aviation.task.constants.Constants.CARGO_NOT_FOUND;
import static aero.smart4aviation.task.constants.Constants.FLIGHT_NOT_FOUND;

/**
 * Service for flight informations.
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


    public FlightResponse flightInformation(int flightNumber, String departureDate){

        FlightResponse response = new FlightResponse();

        Flight flight = flightRepository.findFlightByNumberAndDate(flightNumber,departureDate);
        if(flight == null){
            logger.error(String.format(FLIGHT_NOT_FOUND, flightNumber, departureDate));
            response.setIsError(true);
            response.setMessage(String.format(FLIGHT_NOT_FOUND, flightNumber, departureDate));
            return response;
        }

        Cargo cargo = cargoRepository.findCargoByFlightId(flight.getFlightId());
        if(cargo == null){
            logger.error(String.format(CARGO_NOT_FOUND,flight.getFlightId()));
            response.setIsError(true);
            response.setMessage(String.format(CARGO_NOT_FOUND,flight.getFlightId()));
            return response;
        }

        response.setCargoWeight(cargo.getCargoWeight());
        response.setBaggageWeight(cargo.getBaggageWeight());
        response.setTotalWeight(cargo.getTotalWeight());

        return response;
    }


}

package aero.smart4aviation.task.repository;

import aero.smart4aviation.task.model.Flight;
import aero.smart4aviation.task.repository.contract.IRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static aero.smart4aviation.task.constants.Constants.*;

/**
 * Repository implementation for Flight Entity.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */
@Repository
public class FlightRepository implements IRepository<Flight> {

    private static Logger logger = Logger.getLogger(FlightRepository.class);

    private URL fileUrl;
    private List<Flight> flights;

    public FlightRepository(){

        this.fileUrl = getClass().getResource(FLIGHT_FILE);
        flights = readJson();
    }

    public FlightRepository(URL url){

        this.fileUrl = url;
        flights = readJson();
    }

    public Flight findFlightByNumberAndDate(int flightNumber, String _departureDate){

        ZonedDateTime departureDate;
        try{

            departureDate = ZonedDateTime.parse(_departureDate);
        } catch (DateTimeParseException ex){

            logger.error(String.format(DATETIME_FORMAT_ERROR, _departureDate));
            return null;
        }

        return flights.stream()
                .filter(flight -> flightNumber == flight.getFlightNumber())
                .filter(flight -> departureDate.equals(flight.getDepartureDate()))
                .findAny()
                .orElse(null);
    }

    public Flight findFlightByNumber(int flightNumber){

        return flights.stream()
                .filter(flight -> flightNumber == flight.getFlightNumber())
                .findAny()
                .orElse(null);
    }

    public Flight findFlightByDate(String _departureDate){

        ZonedDateTime departureDate;
        try{

            departureDate = ZonedDateTime.parse(_departureDate);
        } catch (DateTimeParseException ex){

            logger.error(String.format(DATETIME_FORMAT_ERROR, _departureDate));
            return null;
        }

        return flights.stream()
                .filter(flight -> departureDate.equals(flight.getDepartureDate()))
                .findAny()
                .orElse(null);
    }

    public List<Flight> findFlightByAirportCodeAndDate(String airportCode, String _departureDate){

        ZonedDateTime departureDate;
        try{

            departureDate = ZonedDateTime.parse(_departureDate);
        } catch (DateTimeParseException ex){

            logger.error(String.format(DATETIME_FORMAT_ERROR, _departureDate));
            return Collections.emptyList();
        }

        return flights.stream()
                .filter(flight -> airportCode.equalsIgnoreCase(flight.getArrivalAirportIATACode()) ||
                        airportCode.equalsIgnoreCase(flight.getDepartureAirportIATACode()))
                .filter(flight -> departureDate.equals(flight.getDepartureDate()))
                .collect(Collectors.toList());
    }

    public List<Flight> findFlightByAirportCode(String airportCode){

        return flights.stream()
                .filter(flight -> airportCode.equalsIgnoreCase(flight.getArrivalAirportIATACode()) ||
                        airportCode.equalsIgnoreCase(flight.getDepartureAirportIATACode()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> readJson(){

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);

        try {

            return Arrays.asList(mapper.readValue(fileUrl, Flight[].class));
        } catch (IllegalArgumentException e) {

            logger.error(String.format(JSON_FILE_NOT_FOUND, fileUrl));
            return null;
        } catch (IOException e) {

            logger.error(String.format(IO_EXCEPTION_WHILE_READING, fileUrl.getFile()));
            return null;
        }
    }


}

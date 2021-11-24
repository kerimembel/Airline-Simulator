package aero.smart4aviation.task.repository;

import aero.smart4aviation.task.model.FlightResponse;
import aero.smart4aviation.task.repository.contract.IRepository;
import aero.smart4aviation.task.model.Flight;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static aero.smart4aviation.task.constants.Constants.FLIGHT_FILE;

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

    @Override
    public List<Flight> readJson(){

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);

        try {

            return Arrays.asList(mapper.readValue(fileUrl, Flight[].class));
        } catch (IllegalArgumentException e) {

            logger.error(String.format("Can not found the file '%s' ", fileUrl));
            return null;
        } catch (IOException e) {

            logger.error(String.format("IO Exception occured while reading from file '%s'",fileUrl.getFile()));
            return null;
        }
    }

    public Flight findFlightByNumberAndDate(int flightNumber, String _departureDate){

        ZonedDateTime departureDate = ZonedDateTime.parse(_departureDate);

        return flights.stream()
                .filter(flight -> flightNumber == flight.getFlightNumber())
                .filter(flight -> departureDate.equals(flight.getDepartureDate()))
                .findAny()
                .orElse(null);
    }


}

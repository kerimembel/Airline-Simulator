package aero.smart4aviation.task.data;

import aero.smart4aviation.task.model.Flight;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URL;
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
public class FlightRepository implements Repo<Flight> {

    private static Logger logger = Logger.getLogger(FlightRepository.class);

    private URL fileUrl;

    public FlightRepository(){

        this.fileUrl = getClass().getResource(FLIGHT_FILE);
    }

    public FlightRepository(URL url){

        this.fileUrl = url;
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

            logger.error(String.format("IO Exception occured while reading from file '%s'",fileUrl));
            return null;
        }
    }


}

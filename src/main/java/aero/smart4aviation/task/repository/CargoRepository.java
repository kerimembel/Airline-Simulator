package aero.smart4aviation.task.repository;

import aero.smart4aviation.task.repository.contract.IRepository;
import aero.smart4aviation.task.model.Cargo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static aero.smart4aviation.task.constants.Constants.CARGO_FILE;


/**
 * Repository implementation for Cargo Entity.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */
@Repository
public class CargoRepository implements IRepository<Cargo> {

    private static Logger logger = Logger.getLogger(CargoRepository.class);

    private URL fileUrl;
    private List<Cargo> cargos;

    public CargoRepository(){

        this.fileUrl = getClass().getResource(CARGO_FILE);
        cargos = readJson();
    }

    public CargoRepository(URL url){

        this.fileUrl = url;
        cargos = readJson();
    }

    @Override
    public List<Cargo> readJson() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {

            return Arrays.asList(mapper.readValue(fileUrl,Cargo[].class));
        } catch (IllegalArgumentException e) {

            logger.error(String.format("Can not found the file '%s' ", fileUrl));
            return null;
        } catch (IOException e) {

            logger.error(String.format("IO Exception occured while reading from file '%s'",fileUrl));
            return null;
        }
    }

    public Cargo findCargoByFlightId(int flightId){

        return cargos.stream()
                .filter(cargo -> flightId == cargo.getFlightId())
                .findAny()
                .orElse(null);
    }

    public List<Cargo> findCargoByFlightId(List<Integer> flightIds){

        return cargos.stream()
                .filter(cargo -> flightIds.stream()
                        .anyMatch(flightId ->
                                flightId == cargo.getFlightId()))
                .collect(Collectors.toList());
    }
}

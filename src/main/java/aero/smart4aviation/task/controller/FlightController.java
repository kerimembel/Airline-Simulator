package aero.smart4aviation.task.controller;

import aero.smart4aviation.task.model.CountResponse;
import aero.smart4aviation.task.model.WeightResponse;
import aero.smart4aviation.task.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller for {@link FlightService}
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */
@RestController
@RequestMapping("/api/flight")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService){
        this.flightService = flightService;
    }

    @GetMapping(value = "/weights")
    public ResponseEntity<WeightResponse> getFlightWeights(
            @RequestParam(value = "flightNumber") Integer flightNumber,
            @RequestParam(value = "departureDate", required = false) String departureDate){

        WeightResponse response;

        response = flightService.weightInformation(flightNumber,departureDate);


        if(response.getDetail().getReturnCode() == 404){
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/counts")
    public ResponseEntity<CountResponse> getFlightCounts(
            @RequestParam(value = "airportCode") String airportCode,
            @RequestParam(value = "departureDate", required = false) String departureDate){

        CountResponse response;

        response = flightService.countInformation(airportCode, departureDate);

        if(response.getDetail().getReturnCode() == 404){
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}

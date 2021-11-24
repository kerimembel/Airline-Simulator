package aero.smart4aviation.task.controller;

import aero.smart4aviation.task.model.FlightResponse;
import aero.smart4aviation.task.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

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

    @GetMapping(value = "")
    public ResponseEntity<FlightResponse> getFlightWeights(
            @RequestParam int flightNumber,
            @RequestParam String departureDate){

        FlightResponse response = flightService.flightInformation(flightNumber,departureDate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

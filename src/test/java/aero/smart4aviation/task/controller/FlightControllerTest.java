package aero.smart4aviation.task.controller;


import aero.smart4aviation.task.model.CountResponse;
import aero.smart4aviation.task.model.Flight;
import aero.smart4aviation.task.model.RestResponse;
import aero.smart4aviation.task.model.WeightResponse;
import aero.smart4aviation.task.service.FlightService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit test for {@link FlightController}
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */
@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @MockBean
    FlightService flightService;

    @Autowired
    MockMvc mockMvc;

    WeightResponse weightResponse;
    CountResponse countResponse;
    RestResponse detail;

    @Before
    public void setUp() {

        configureEntities();
        Mockito.when(flightService.weightInformation(Mockito.anyInt(),Mockito.anyString())).thenReturn(weightResponse);
        Mockito.when(flightService.countInformation(Mockito.anyString(),Mockito.anyString())).thenReturn(countResponse);

    }

    @Test
    public void flightWeightsTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/flight/weights")
                        .param("flightNumber","1111")
                        .param("departureDate","date"))
                .andExpect(status().isOk());
    }


    @Test
    public void flightWeightsParamNullTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/flight/weights"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void flightCountsTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/flight/counts")
                        .param("airportCode","1111")
                        .param("departureDate","date"))
                .andExpect(status().isOk());
    }


    @Test
    public void flightCountsParamNullTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/flight/counts"))
                .andExpect(status().isBadRequest());
    }


    private void configureEntities(){

        weightResponse = new WeightResponse();
        countResponse = new CountResponse();
        detail = new RestResponse();

        weightResponse.setBaggageWeight(10);
        weightResponse.setCargoWeight(10);
        weightResponse.setTotalWeight(20);
        weightResponse.setWeightUnit("kg");
        weightResponse.setDetail(detail);

        countResponse.setDepartingFlightCount(1);
        countResponse.setArrivingFlightCount(1);
        countResponse.setDepartingBaggageCount(1);
        countResponse.setArrivingBaggageCount(1);
        countResponse.setDetail(detail);
    }

}
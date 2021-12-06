package com.airline.simulator.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import static com.airline.simulator.constants.Constants.DEFAULT_WEIGHT_UNIT;

/**
 * Object to standardize Response.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Getter
@Setter
@EqualsAndHashCode
public class WeightResponse{

    private long cargoWeight;
    private long baggageWeight;
    private long totalWeight;
    private String weightUnit;
    private RestResponse detail;

    public WeightResponse(){

        this.cargoWeight = 0;
        this.baggageWeight = 0;
        this.totalWeight = 0;
        this.weightUnit = DEFAULT_WEIGHT_UNIT;
        this.detail = new RestResponse();
    }

    public void setIsError(boolean isError){

        this.detail.setError(isError);
    }

    public void setMessage(String message){

        this.detail.setMessage(message);
    }

    public void setReturnCode(int returnCode){

        this.detail.setReturnCode(returnCode);
    }
}

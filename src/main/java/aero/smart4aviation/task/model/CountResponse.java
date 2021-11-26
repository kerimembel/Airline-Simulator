package aero.smart4aviation.task.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CountResponse {

    private int departingFlightCount;
    private int arrivingFlightCount;
    private long arrivingBaggageCount;
    private long departingBaggageCount;
    private RestResponse detail;

    public CountResponse(){

        this.departingFlightCount = 0;
        this.arrivingFlightCount = 0;
        this.arrivingBaggageCount = 0;
        this.departingBaggageCount = 0;
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

package aero.smart4aviation.task.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class RestResponse {

    private Timestamp timestamp;
    private boolean isError;
    private String message;

    public RestResponse(){

        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.isError = false;
        this.message = "Success";
    }
}

package aero.smart4aviation.task.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class RestResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Timestamp timestamp;
    private boolean isError;
    private String message;
    private int returnCode;

    public RestResponse(){

        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.isError = false;
        this.message = "Success";
        this.returnCode = 200;
    }

    public RestResponse(int returnCode, String message){

        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.isError = true;
        this.message = message;
        this.returnCode = returnCode;
    }
}

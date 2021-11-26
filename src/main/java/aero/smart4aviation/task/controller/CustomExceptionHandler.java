package aero.smart4aviation.task.controller;

import aero.smart4aviation.task.model.CountResponse;
import aero.smart4aviation.task.model.RestResponse;
import aero.smart4aviation.task.model.WeightResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

/**
 * Custom exception handler for {@link FlightController}
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestResponse> unknownException(Exception ex, WebRequest req) {

        RestResponse detail = new RestResponse(400, ex.getMessage());

        if("/api/flight/counts".equalsIgnoreCase(((ServletWebRequest)req).getRequest().getRequestURI())){
            CountResponse response = new CountResponse();
            response.setDetail(detail);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);

        } else{
            WeightResponse response = new WeightResponse();
            response.setDetail(detail);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);

        }
    }

}

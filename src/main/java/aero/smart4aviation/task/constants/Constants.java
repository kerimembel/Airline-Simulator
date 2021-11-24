package aero.smart4aviation.task.constants;

/**
 * Class to keep constants for project.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */
public class Constants {

    public static final String CARGO_FILE = "/cargos.json";

    public static final String FLIGHT_FILE = "/flights.json";

    public static final String FLIGHT_NOT_FOUND = "Flight %s with a departure date of %s could not be found!";

    public static final String CARGO_NOT_FOUND = "The cargo information of the flight with ID %s could not be found.";

    public static final String DEFAULT_WEIGHT_UNIT = "kg";



    public Constants() throws IllegalAccessException {
        throw new IllegalAccessException("This is a Constant class!");
    }


}

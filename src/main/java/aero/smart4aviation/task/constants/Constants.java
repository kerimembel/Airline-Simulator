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

    public static final String FLIGHT_NUMBER_NOT_FOUND = "Flight %s with a departure date of %s could not be found!";

    public static final String FLIGHT_AIRPORT_CODE_NOT_FOUND = "No flights with the departure date %s were found at the airport with the code %s!";

    public static final String CARGO_NOT_FOUND = "The cargo information of the flight with ID %s could not be found.";

    public static final String CARGOS_NOT_FOUND = "The cargo information of the flight with ID [%s] could not be found.";

    public static final String DATETIME_FORMAT_ERROR = "The provided datetime format is incorrect : '%s'";

    public static final String KG = "kg";

    public static final String LB = "lb";

    public static final String DEFAULT_WEIGHT_UNIT = KG;


    public static final String JSON_FILE_NOT_FOUND = "Can not found the file '%s' ";
    public static final String IO_EXCEPTION_WHILE_READING ="IO Exception occured while reading from file '%s'";


    public Constants() throws IllegalAccessException {
        throw new IllegalAccessException("This is a Constant class!");
    }


}

package aero.smart4aviation.task.data;

import java.io.IOException;
import java.util.List;

/**
 * Interface definiton for Repositories.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */
public interface Repo<T> {

    List<T> readJson();
}

package com.airline.simulator.repository.contract;

import java.util.List;

/**
 * Interface definiton for Repositories.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */
public interface IRepository<T> {

    List<T> readJson();
}

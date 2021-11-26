package aero.smart4aviation.task.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Entity class for Cargo Entity.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Cargo {

    private int flightId;
    private List<Baggage> baggage;
    private List<Baggage> cargo;

    public long getCargoWeight(){
        //TODO Conversion between lb and kg (lb = kg * 2.2046)
        return cargo.stream().mapToLong(c -> c.getWeight()).sum();
    }

    public long getBaggageWeight(){
        //TODO Conversion between lb and kg (lb = kg * 2.2046)
        return baggage.stream().mapToLong(b -> b.getWeight()).sum();
    }

    public long getTotalWeight(){

        return getBaggageWeight() + getCargoWeight();
    }

    public long getBaggageCount(){

        return baggage.stream().mapToLong(Baggage::getPieces).sum();
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "flightId=" + flightId +
                ", baggage=" + baggage +
                ", cargo=" + cargo +
                '}';
    }
}

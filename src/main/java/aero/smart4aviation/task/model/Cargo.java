package aero.smart4aviation.task.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.Bag;

import java.util.List;

import static aero.smart4aviation.task.constants.Constants.*;

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
        return convertWeightUnits(cargo);
    }

    public long getBaggageWeight(){
        return convertWeightUnits(baggage);
    }

    public long getTotalWeight(){

        return getBaggageWeight() + getCargoWeight();
    }

    public long getBaggageCount(){

        return baggage.stream().mapToLong(Baggage::getPieces).sum();
    }

    private long convertWeightUnits(List<Baggage> list){

        double baggageWeight = 0;

        if(DEFAULT_WEIGHT_UNIT.equals(KG)){

            for(Baggage baggage : list){

                if(baggage.getWeightUnit().equalsIgnoreCase(LB))
                    baggageWeight += baggage.getWeight() / 2.2046;
                else if(baggage.getWeightUnit().equalsIgnoreCase(KG))
                    baggageWeight += baggage.getWeight();
            }
        } else{

            for(Baggage baggage : list){

                if(baggage.getWeightUnit().equalsIgnoreCase(KG))
                    baggageWeight += baggage.getWeight() * 2.2046;
                else if(baggage.getWeightUnit().equalsIgnoreCase(LB))
                    baggageWeight += baggage.getWeight();
            }
        }


        return (long) baggageWeight;
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

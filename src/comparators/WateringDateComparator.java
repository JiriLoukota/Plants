package comparators;

import plant.Plant;
import java.util.Comparator;

public class WateringDateComparator implements Comparator<Plant> {
    @Override
    public int compare(Plant plantOne, Plant plantTwo) {
        return plantOne.getLastWatering().compareTo(plantTwo.getLastWatering());
    }
}

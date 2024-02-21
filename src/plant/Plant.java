package plant;
import exceptions.PlantException;
import java.time.LocalDate;

public class Plant implements Comparable<Plant>{
    //Attributes
    private String name;
    private String description;
    private LocalDate planted;
    private LocalDate lastWatering;
    private int frequencyOfWatering;
    //Constructors
    public Plant(String name, String description, LocalDate planted, LocalDate lastWatering, int frequencyOfWatering) throws PlantException {
        this.name = name;
        this.description = description;
        this.planted = planted;
        this.setLastWatering(lastWatering);
        this.setFrequencyOfWatering(frequencyOfWatering);
    }
    public Plant(String name, LocalDate planted, int frequencyOfWatering) throws PlantException {
        this(name, "", planted, LocalDate.now(), frequencyOfWatering);
    }
    public Plant(String name) throws PlantException {
        this(name, "", LocalDate.now(), LocalDate.now(), 7);
    }

    //region Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getLastWatering() {
        return lastWatering;
    }

    public void setLastWatering(LocalDate lastWatering) throws PlantException {
        if(lastWatering.isAfter(this.planted) || lastWatering.isEqual(this.planted)) this.lastWatering = lastWatering;
        else throw new PlantException("Last watering can´t be before date of planting.");
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if(frequencyOfWatering>0) this.frequencyOfWatering = frequencyOfWatering;
        else throw new PlantException("Frequency of watering must be greater than zero.");
    }

    //endregion
    //region Special methods
    //Returns info about watering as a String
    public String getWateringInfo(){
        return name + ": last watering:" + this.lastWatering + "; next watering: " + this.lastWatering.plusDays(this.frequencyOfWatering);
    }
    //Changes data into form used for saving into files
    public String toSavingFormat(){
        return name + "\t" + description + "\t" + frequencyOfWatering + "\t" + lastWatering + "\t" + planted;
    }
    //This method is used to compare two Plants by name, it doesn't prefer uppercase names over lowercase
    @Override
    public int compareTo(Plant secondPlant){
        String plantOne = this.name.toLowerCase(), plantTwo = secondPlant.name.toLowerCase();
        return plantOne.compareTo(plantTwo);
    }
    //To string method for printing info
    @Override
    public String toString() {
        return "Plant: " +
                "name=" + name +
                ", description=" + description +
                ", planted=" + planted +
                ", lastWatering=" + lastWatering +
                ", frequencyOfWatering=" + frequencyOfWatering;
    }
    //endregion
}

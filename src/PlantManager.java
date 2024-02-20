import java.util.List;

public class PlantManager {
    //Attributes
    private List<Plant> plantList;
    private String path;
    //Constructors

    public PlantManager(List<Plant> plantList) {
        this.plantList = plantList;
    }
    public PlantManager(String path){

    }
    //Special methods
    public void addPlant(Plant plant){
        plantList.add(plant);
    }
    public void addPlants(List<Plant> plants){
        plantList.addAll(plants);
    }
    public Plant get(int index){
        return plantList.get(index);
    }
    public void remove(int index){
        plantList.remove(index);
    }
    public void loadFromFile(String path){
        //loading from file, throws exception, one list per one file
    }
    public void updateFile(){

    }
}

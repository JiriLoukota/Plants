package plant;
import exceptions.FileException;
import exceptions.PlantException;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

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

    public void loadFromFile(String path) throws FileException {
        plantList.clear();
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(path)));
        } catch (FileNotFoundException e) {
            throw new FileException("Couldn't load file from location: " + path);
        }
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] parts = line.split("\t");
            if(parts.length==5){
                try {
                    Plant plant = new Plant(parts[0], parts[1],LocalDate.parse(parts[4]), LocalDate.parse(parts[3]), Integer.parseInt(parts[2]));
                    plantList.add(plant);
                } catch (PlantException e) {
                    throw new FileException("Couldn't read data from file: " + path + " because it is corrupted.");
                }
            }
        }
        this.path = path;
        //loading from file, throws exception, one list per one file
    }
    public void updateFile() throws FileException {
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(path)));
            for(int i =0; i < plantList.size(); i++){
                printWriter.println(String.valueOf(plantList.get(i)));
            }
        } catch (IOException e) {
            throw new FileException("Couldn't write to file: " + path + "because it couldn't be opened.");
        }
    }

}

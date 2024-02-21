package plant;

import comparators.WateringDateComparator;
import exceptions.FileException;

import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.*;

public class PlantManager {
    //Attributes
    private List<Plant> plantList;
    private String path;

    //Constructors
    public PlantManager(List<Plant> plantList) {
        this.plantList = plantList;
    }

    public PlantManager(String path) throws FileException {
        loadFromFile(path);
    }

    //Getters and setters
    public List<Plant> getPlantList() {
        return plantList;
    }

    public void setPlantList(List<Plant> plantList) {
        this.plantList = plantList;
    }
    //region Special methods

    //Adding Plant to end of the list
    public void addPlant(Plant plant) {
        plantList.add(plant);
    }

    //Adding list of Plants into the list
    public void addPlants(List<Plant> plants) {
        plantList.addAll(plants);
    }

    //Getting Plant from index
    public Plant get(int index) {
        return plantList.get(index);
    }

    //Removing Plant from index
    public void remove(int index) {
        plantList.remove(index);
    }

    //Sorting list by name
    public void sortByName() {
        Collections.sort(plantList);
    }

    //Sorting list by oldest last watering
    public void sortByLastWatering() {
        plantList.sort(new WateringDateComparator());
    }

    //Loading list from file
    public void loadFromFile(String path) throws FileException {
        if (plantList != null) plantList.clear();
        else plantList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\t");
                if (parts.length == 5) {
                    try {
                        Plant plant = new Plant(parts[0], parts[1], LocalDate.parse(parts[4]), LocalDate.parse(parts[3]), Integer.parseInt(parts[2]));
                        plantList.add(plant);
                    } catch (Exception e) {
                        throw new FileException("Couldn't read data from file: " + path + " because it is corrupted.");
                    }
                }
            }
            this.path = path;
        } catch (FileNotFoundException e) {
            throw new FileException("Couldn't load file from location: " + path);
        }
    }

    //Saving list into the file it was loaded from
    public void updateFile() throws FileException {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(path))) {
            for (Plant plant : plantList) {
                printWriter.println(plant.toSavingFormat());
            }
        } catch (IOException e) {
            throw new FileException("Couldn't write to file: " + path + "because it couldn't be opened,  or it doesn't exist.");
        }
    }

    //Saving list into defined file
    public void saveToFile(String path) throws FileException {
        this.path = path;
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(path))) {
            for (Plant plant : plantList) {
                printWriter.println(plant.toSavingFormat());
            }
        } catch (IOException e) {
            throw new FileException("Couldn't write to file: " + path + "because it couldn't be opened,  or it doesn't exist.");
        }
    }
    //endregion
}

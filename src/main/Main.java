package main;

import plant.Plant;
import plant.PlantManager;

public class Main {
    public static void main(String[] args) {
        try{
            //Načtení seznamu
            PlantManager plantManager = new PlantManager("PlantsFiles/kvetiny.txt");
            for(Plant plant : plantManager.getPlantList()){
                //Vypsání informací o zálivce
                System.out.println(plant.getWateringInfo());
            }
            //Provedení změn v seznamu
            plantManager.addPlant(new Plant("Kopretina"));
            plantManager.addPlant(new Plant("Slunečnice"));
            plantManager.remove(0);
            //Uložení seznamu
            plantManager.saveToFile("PlantsFiles/kvetiny2.txt");
        }catch (Exception e){
            System.err.println(e.getLocalizedMessage());
        }
        try{
            //Opětovné načtení seznamu
            PlantManager plantManager = new PlantManager("PlantsFiles/kvetiny2.txt");
            //Seřazení podle jména a vypsání na obrazovku
            plantManager.sortByName();
            plantManager.getPlantList().forEach(System.out::println);
            //Seřazení podle poslední zálivky a vypsání na obrazovku
            plantManager.sortByLastWatering();
            plantManager.getPlantList().forEach(System.out::println);
        }catch (Exception e){
            System.err.println(e.getLocalizedMessage());
        }
        //Pokus o načtení nefunkčního souboru
        try{
            PlantManager plantManager = new PlantManager("PlantsFiles/kvetiny-spatne-datum.txt");
        }catch (Exception e){
            System.err.println(e.getLocalizedMessage());
        }
        //Pokus o načtení druhého nefunkčního souboru
        try{
            PlantManager plantManager = new PlantManager("PlantsFiles/kvetiny-spatne-datum.txt");
        }catch (Exception e){
            System.err.println(e.getLocalizedMessage());
        }
    }
}
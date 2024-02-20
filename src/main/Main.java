package main;

import exceptions.PlantException;
import plant.Plant;

public class Main {
    public static void main(String[] args) {
        Plant plant;
        try {
            plant = new Plant("Rose");
            System.out.println(plant.getWateringInfo());
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
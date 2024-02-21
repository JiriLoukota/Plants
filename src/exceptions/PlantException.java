package exceptions;
public class PlantException extends Exception{
    public PlantException(String message){
        super("Plant exception: " + message);
    }
}

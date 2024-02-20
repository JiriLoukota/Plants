public class Main {
    public static void main(String[] args) {
        Plant plant = null;
        try {
            plant = new Plant("Rose");
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
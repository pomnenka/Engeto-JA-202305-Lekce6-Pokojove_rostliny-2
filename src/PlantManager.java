import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PlantManager {

    private static List<Plant> plantList
            = new ArrayList<>();

    public void loadDataFromFile(String filename, String delimiter) throws PlantException {
        String[] items = new String[0];
        String line = "";
        int lineNumber = 1;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                System.out.println(line);
                items = line.split(delimiter);
                if (items.length != 5)
                    throw new PlantException("Špatný počet položek na řádku: " + lineNumber + ": " + line);
                plantList.add(new Plant(items[0], items[1], LocalDate.parse(items[4]), LocalDate.parse(items[3]), Integer.valueOf(items[2])));
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            throw new PlantException("Soubor " + filename + " nebyl nalezen! " + e.getLocalizedMessage());
        } catch (NumberFormatException e) {
            throw new PlantException("Špatně zadané číslo " + items[2] + " na řádku:" + lineNumber + ":\n" + line + ". " + e.getLocalizedMessage());
        }
    }

    public void saveDataToFile(String filename) {
        try (PrintWriter outputWriter = new PrintWriter(new FileWriter(filename))) {
            // Každou osobu ze seznamu zapíšeme do souboru:
            for (Plant plant : plantList) {
                System.out.println("zapisuji do souboru: " + plant.toFile());
                outputWriter.println(plant.toFile());
            }
        } catch (Exception e) {
            // Odchytíme a vypíšeme případné chyby.
            e.printStackTrace();
        }
    }

    public void sort() {
        Collections.sort(plantList);
    }

    public void sortWateringDate() {
        Collections.sort(plantList, Plant::compareWateringDate);
    }

    public void add(Plant newPlant) {
        plantList.add(newPlant);
    }

    public void remove(Plant plant) {
        plantList.remove(plant);
    }

    public Plant get(int index) {
        return plantList.get(index);
    }

    public List<Plant> getPlantList() {
        return new ArrayList<>(plantList);
    }

    public int size() {
        return plantList.size();
    }

}
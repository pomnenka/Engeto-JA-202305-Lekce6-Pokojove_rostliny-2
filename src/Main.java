import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        PlantManager plantManager = new PlantManager();
        try {
            plantManager.loadDataFromFile(Settings.fileName(), Settings.delimiter());
        } catch (PlantException e) {
            System.err.println("Nepodařilo se načíst data ze souboru!!! " + e.getLocalizedMessage());
        }

        System.out.println("\nTestovací výpis:");

        int i = 0;
        while (i < plantManager.size()) {
            System.out.println(plantManager.get(i));  // testovací výpis
            i++;
        }

        System.out.println("\nInformace o zálivce:");
        i = 0;
        while (i < plantManager.size()) {
            System.out.println(plantManager.get(i).getWateringInfo());  // testovací výpis
            i++;
        }

//        přidám dvě květiny
        try {
            plantManager.add(new Plant("Tulipán", "různobarevné", LocalDate.parse("2023-03-12"), LocalDate.parse("2023-06-12"), 2));
            plantManager.add(new Plant("Orchidea", "bílá", LocalDate.parse("2023-04-21"), LocalDate.parse("2023-06-08"), 5));
        } catch (PlantException e) {
            System.err.println(e);
        }

        System.out.println("\nTestovací výpis po přidání dvou kytek:");
        i = 0;
        while (i < plantManager.size()) {
            System.out.println(plantManager.get(i));  // testovací výpis
            i++;
        }

// smazání druhé květiny
        plantManager.remove(plantManager.get(1));

        System.out.println("\nTestovací výpis po smazání druhé květiny:");
        i = 0;
        while (i < plantManager.size()) {
            System.out.println(plantManager.get(i));  // testovací výpis
            i++;
        }

        System.out.println("\nVytváření výstupního souboru:");
        try {
            plantManager.saveDataToFile(Settings.fileNameOut());
            System.out.println("Soubor zapsán úspěšně!");
        } catch (Exception e) {
            System.err.println("Nepodařilo se načíst data do souboru!!! " + e.getLocalizedMessage());
        }

        System.out.println("\nSeznam seřazen dle abecedy:");
        plantManager.sort();
        plantManager.getPlantList().forEach(n -> {
            System.out.println(n);
        });

        System.out.println("\nSeznam seřazen dle datumu zálivky:");
        plantManager.sortWateringDate();
        plantManager.getPlantList().forEach(n -> {
            System.out.println(n);
        });

        System.out.println("\nSeznam datumů výsadby:");
        Set<LocalDate> mnozinaDatumyVysadby = new HashSet<>();
        plantManager.getPlantList().forEach(n -> mnozinaDatumyVysadby.add(n.getPlantedDate()));
        mnozinaDatumyVysadby.forEach(n -> System.out.println(n));

        System.out.println("\nSeznam datumů výsadby za poslední 4 měsíce:");
        Set<LocalDate> mnozinaDatumyVysadby4mesice = new HashSet<>();
        plantManager.getPlantList().forEach(n -> {
            if (ChronoUnit.MONTHS.between(n.getPlantedDate(), LocalDate.now()) < 4) {
                mnozinaDatumyVysadby4mesice.add(n.getPlantedDate());
            }
        });
        mnozinaDatumyVysadby4mesice.forEach(n -> System.out.println(n));

    }
}
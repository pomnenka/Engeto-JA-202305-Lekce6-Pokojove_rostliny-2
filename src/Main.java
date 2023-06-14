import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        PlantManager plantManager = new PlantManager();
        try {
            plantManager.loadDataFromFile(Settings.fileName(), Settings.delimiter());
        } catch (PlantException e) {
            System.err.println("Nepodařilo se načíst data ze souboru!!! "+e.getLocalizedMessage());
        }

        System.out.println("\nTestovací výpis:");

        int i = 0;
        while(i < plantManager.size()) {
            System.out.println(plantManager.get(i));  // testovací výpis
            i++;
        }

        System.out.println("\nInformace o zálivce:");
        i = 0;
        while(i < plantManager.size()) {
            System.out.println(plantManager.get(i).getWateringInfo());  // testovací výpis
            i++;
        }

//        přidám dvě květiny
//        try {
//            plantManager.add(new Plant("Tulipán", "různobarevné", LocalDate.parse("2023-03-12"), LocalDate.parse("2023-06-12"), 2));
//            plantManager.add(new Plant("Orchidea", "bílá", LocalDate.parse("2023-04-21"), LocalDate.parse("2023-06-08"), 5));
//        } catch (PlantException e) {
//            System.err.println(e);
//        }
//
//        System.out.println("\nTestovací výpis po přidání dvou kytek:");
//        i = 0;
//        while(i < plantManager.size()) {
//            System.out.println(plantManager.get(i));  // testovací výpis
//            i++;
//        }

// smazání druhé květiny
//        plantManager.remove(plantManager.get(1));
//
//        System.out.println("\nTestovací výpis po smazání druhé květiny:");
//        i = 0;
//        while(i < plantManager.size()) {
//            System.out.println(plantManager.get(i));  // testovací výpis
//            i++;
//        }

        try {
            plantManager.saveDataToFile(Settings.fileNameOut());
            System.out.println("Soubor zapsán úspěšně!");
        } catch (Exception e) {
            System.err.println("Nepodařilo se načíst data do souboru!!! "+e.getLocalizedMessage());
        }


    }
}
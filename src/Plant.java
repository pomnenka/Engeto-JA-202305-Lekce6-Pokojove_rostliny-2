import java.time.LocalDate;

public class Plant implements Comparable<Plant> {
    private String name;
    private String notes;
    private LocalDate plantedDate;
    private LocalDate wateringDate;
    private int wateringFrequency;

    @Override
    public int compareTo(Plant second) {
        return this.name.compareTo(second.name);
    }

    public int compareWateringDate(Plant second) {
        return this.wateringDate.compareTo(second.wateringDate);
    }

    public Plant(String name, String notes, LocalDate plantedDate, LocalDate wateringDate, int wateringFrequency) throws PlantException {
        if (wateringFrequency <= 0) { throw new PlantException("Frekvence zálivky musí být větší než nula.");}
        if (wateringDate.isBefore(plantedDate)) { throw new PlantException("Datum zálivky musí být pozdější než datum výsadby.");}
        this.name = name;
        this.notes = notes;
        this.plantedDate = plantedDate;
        this.wateringDate = wateringDate;
        this.wateringFrequency = wateringFrequency;
    }

    public Plant(String name, LocalDate plantedDateDate, int wateringFrequency) throws PlantException {
        this(name, "", plantedDateDate, LocalDate.now(), wateringFrequency);
    }

    public Plant(String name) throws PlantException {
        this(name, LocalDate.now(), 7);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlantedDate() {
        return plantedDate;
    }

    public void setPlantedDate(LocalDate plantedDate) {
        this.plantedDate = plantedDate;
    }

    public LocalDate getWateringDate() {
        return wateringDate;
    }

    public void setWateringDate(LocalDate wateringDate) {
        this.wateringDate = wateringDate;
    }

    public int getWateringFrequency() {
        return wateringFrequency;
    }

    public void setWateringFrequency(int wateringFrequency) {
        this.wateringFrequency = wateringFrequency;
    }

    public String getWateringInfo() {
        return "Plant{" +
                "Název: '" + name + '\'' +
                ", poslední zálivka: " + wateringDate +
                ", doporučená zálivka: " + wateringDate.plusDays(wateringFrequency) +
                '}';
    }

    @Override
    public String toString() {
        return "Plant{" +
                "name='" + name + '\'' +
                ", notes='" + notes + '\'' +
                ", plantedDate=" + plantedDate +
                ", wateringDate=" + wateringDate +
                ", wateringFrequency=" + wateringFrequency +
                '}';
    }

    public String toFile() {
        return  name + '\t' +
                notes + '\t' +
                wateringFrequency + '\t' +
                wateringDate + '\t' +
                plantedDate;
    }
}

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class FileInputGenerator {
    // Configuration constants
    private static final int LOCATION_COUNT = 100;
    private static final int APPLIANCES_PER_LOCATION_LOW = 15;
    private static final int APPLIANCES_PER_LOCATION_HIGH = 20;
    private static final String OUTPUT_FILE = "appliances.csv";

    // Sample appliance types
    private static final String[] APPLIANCE_TYPES = {
        "Fridge", "Washer", "Dryer", "Oven", "Microwave",
        "TV", "Heater", "AC", "Fan", "Light", "Computer"
    };

    public static void main(String[] args) {
        Random rand = new Random();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
            for (int i = 0; i < LOCATION_COUNT; i++) {
                long location = 10000000L + rand.nextInt(90000000);

                // Random number of appliances per location
                int applianceCount = rand.nextInt(
                    APPLIANCES_PER_LOCATION_HIGH - APPLIANCES_PER_LOCATION_LOW + 1
                ) + APPLIANCES_PER_LOCATION_LOW;

                for (int j = 0; j < applianceCount; j++) {
                    String name = APPLIANCE_TYPES[rand.nextInt(APPLIANCE_TYPES.length)];
                    int onWattage = rand.nextInt(2000) + 1; // 1 to 2000 watts
                    int offWattage = rand.nextInt(200) + 1; // 1 to 200 watts
                    double probabilityOn = rand.nextDouble(); // 0.0 to 1.0

                    // Write line to file
                    writer.write(location + "," + name + "," + onWattage + "," + offWattage + "," + probabilityOn);
                    writer.newLine();
                }
            }

            System.out.println("File generated successfully: " + OUTPUT_FILE);

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}

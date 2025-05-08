import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class ApplianceManager {
    private ArrayList<Appliance> appliances;

    public ApplianceManager() {
        this.appliances = new ArrayList<>();
    }

    // Add an appliance to the manager
    public void addAppliance(Appliance appliance) {
        this.appliances.add(appliance);
    }

    // Add appliances from a file
    public void addAppliancesFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    long location = Long.parseLong(parts[0].trim());
                    String name = parts[1].trim();
                    int onWattage = Integer.parseInt(parts[2].trim());
                    int offWattage = Integer.parseInt(parts[3].trim());
                    double probOn = Double.parseDouble(parts[4].trim());

                    Appliance appliance = new Appliance(location, name, onWattage, offWattage, probOn);
                    this.addAppliance(appliance);
                }
            }
            System.out.println("Appliances loaded from file: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing appliance data: " + e.getMessage());
        }
    }

    // Get all appliances as a flat list
    public List<Appliance> getAllAppliances() {
        return new ArrayList<>(this.appliances);
    }

    // Print a summary report
    public void printSummaryReport() {
        String report = "\nAppliance Summary Report:\n";
        report += "Total appliances: " + this.appliances.size() + "\n";

        System.out.println(report);

        // Write to output file
        try (FileWriter writer = new FileWriter("appliance_summary.txt")) {
            writer.write(report);
            System.out.println("Summary report saved to appliance_summary.txt");
        } catch (IOException e) {
            System.out.println("Error writing summary report: " + e.getMessage());
        }
    }

    // Print all appliances
    public void printAllAppliances() {
        for (Appliance appliance : this.appliances) {
            System.out.println(appliance);
        }
    }

    // Print appliances by location
    public void printAppliancesByLocation(long location) {
        boolean found = false;
        for (Appliance appliance : this.appliances) {
            if (appliance.getLocation() == location) {
                System.out.println(appliance);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appliances found for location: " + location);
        }
    }

    // Print appliances by type
    public void printAppliancesByType(String type) {
        boolean found = false;
        for (Appliance appliance : this.appliances) {
            if (appliance.getName().equalsIgnoreCase(type)) {
                System.out.println(appliance);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appliances found of type: " + type);
        }
    }
}

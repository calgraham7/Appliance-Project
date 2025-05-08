import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ApplianceManager {
    private ArrayList<Appliance> appliances;

    // Constructor
    public ApplianceManager() {
        appliances = new ArrayList<>();
    }

    // Add a single appliance
    public void addAppliance(Appliance appliance) {
        appliances.add(appliance);
    }

    // Remove an appliance by ID
    public boolean removeAppliance(int applianceID) {
        for (int i = 0; i < appliances.size(); i++) {
            if (appliances.get(i).getApplianceID() == applianceID) {
                appliances.remove(i);
                return true;
            }
        }
        return false;
    }

    // Find an appliance by ID
    public Appliance findApplianceByID(int applianceID) {
        for (Appliance a : appliances) {
            if (a.getApplianceID() == applianceID) {
                return a;
            }
        }
        return null;
    }

    // Get all appliances for a specific location
    public ArrayList<Appliance> getAppliancesByLocation(long location) {
        ArrayList<Appliance> result = new ArrayList<>();
        for (Appliance a : appliances) {
            if (a.getLocation() == location) {
                result.add(a);
            }
        }
        return result;
    }

    // Get all appliances of a particular type
    public ArrayList<Appliance> getAppliancesByType(String type) {
        ArrayList<Appliance> result = new ArrayList<>();
        for (Appliance a : appliances) {
            if (a.getName().equalsIgnoreCase(type)) {
                result.add(a);
            }
        }
        return result;
    }

    // Load appliances from a CSV file
    public void loadAppliancesFromFile(String filename) {
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                String[] parts = line.split(",");

                if (parts.length == 5) {
                    try {
                        long location = Long.parseLong(parts[0].trim());
                        String name = parts[1].trim();
                        int onWattage = Integer.parseInt(parts[2].trim());
                        int offWattage = Integer.parseInt(parts[3].trim());
                        double probabilityOn = Double.parseDouble(parts[4].trim());

                        Appliance appliance = new Appliance(location, name, onWattage, offWattage, probabilityOn);
                        addAppliance(appliance);

                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid number format in line -> " + line);
                    }
                } else {
                    System.out.println("Error: Invalid line format -> " + line);
                }
            }
            fileScanner.close();
            System.out.println("File loaded successfully.");

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found -> " + filename);
        }
    }

    // Print a summary report
    public void printSummaryReport() {
        System.out.println("\nSummary Report:");
        System.out.println("Total Appliances: " + appliances.size());
        ArrayList<String> countedTypes = new ArrayList<>();
        for (Appliance a : appliances) {
            String name = a.getName();
            if (!countedTypes.contains(name)) {
                int count = 0;
                for (Appliance b : appliances) {
                    if (b.getName().equals(name)) {
                        count++;
                    }
                }
                System.out.println(name + ": " + count);
                countedTypes.add(name);
            }
        }
    }

    // Print all appliances
    public void printAllAppliances() {
        for (Appliance a : appliances) {
            System.out.println(a);
        }
    }
}

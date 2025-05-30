import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApplianceManager {
    private ArrayList<Appliance> appliances;

    public ApplianceManager() {
        this.appliances = new ArrayList<>();
    }

    // Add an appliance to the manager
    public void addAppliance(Appliance appliance) throws ApplianceException {
        if (appliance == null)
            throw new ApplianceException("Cannot add null appliance.");
        appliances.add(appliance);
    }

    public int removeAppliancesByName(String name) {
        int removedCount = 0;
        for (int i = appliances.size() - 1; i >= 0; i--) {
            if (appliances.get(i).getName().equalsIgnoreCase(name)) {
                appliances.remove(i);
                removedCount++;
            }
        }
        return removedCount;
    }

    // Add appliances from a file
    public void addAppliancesFromFile(String filename) throws ApplianceException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 7) {
                    long location = Long.parseLong(parts[0].trim());
                    String name = parts[1].trim();
                    int onWattage = Integer.parseInt(parts[2].trim());
                    int offWattage = Integer.parseInt(parts[3].trim());
                    double probOn = Double.parseDouble(parts[4].trim());
                    boolean isSmart = Boolean.parseBoolean(parts[5].trim());
                    double reducePercent = Double.parseDouble(parts[6].trim());
                    if (isSmart == true) {
                        SmartAppliance appliance = new SmartAppliance(location, name, onWattage, offWattage, probOn,
                                isSmart, reducePercent);
                        this.addAppliance(appliance);
                    } else {
                        NormalAppliance appliance = new NormalAppliance(location, name, onWattage, offWattage, probOn,
                                false, 0.0);
                        this.addAppliance(appliance);
                    }
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

    public void printSummaryReport() {
        System.out.println("\nSummary Report:");

        // Count unique locations
        ArrayList<Long> uniqueLocations = new ArrayList<Long>();
        for (int i = 0; i < appliances.size(); i++) {
            Appliance a = appliances.get(i);
            if (!uniqueLocations.contains(a.getLocation())) {
                uniqueLocations.add(a.getLocation());
            }
        }
        System.out.println("Total Locations: " + uniqueLocations.size());

        // Count appliance names by type
        ArrayList<String> smartNames = new ArrayList<String>();
        ArrayList<Integer> smartCounts = new ArrayList<Integer>();

        ArrayList<String> normalNames = new ArrayList<String>();
        ArrayList<Integer> normalCounts = new ArrayList<Integer>();

        for (int i = 0; i < appliances.size(); i++) {
            Appliance a = appliances.get(i);
            String name = a.getName();

            if (a instanceof SmartAppliance) {
                if (smartNames.contains(name)) {
                    int index = smartNames.indexOf(name);
                    smartCounts.set(index, smartCounts.get(index) + 1);
                } else {
                    smartNames.add(name);
                    smartCounts.add(1);
                }
            } else if (a instanceof NormalAppliance) {
                if (normalNames.contains(name)) {
                    int index = normalNames.indexOf(name);
                    normalCounts.set(index, normalCounts.get(index) + 1);
                } else {
                    normalNames.add(name);
                    normalCounts.add(1);
                }
            }
        }

        // Print smart appliance counts
        System.out.println("\nSmart Appliance Counts:");
        if (smartNames.isEmpty()) {
            System.out.println("None");
        } else {
            for (int i = 0; i < smartNames.size(); i++) {
                System.out.println(smartNames.get(i) + ": " + smartCounts.get(i));
            }
        }

        // Print normal appliance counts
        System.out.println("\nNormal Appliance Counts:");
        if (normalNames.size() == 0) {
            System.out.println("None");
        } else {
            for (int i = 0; i < normalNames.size(); i++) {
                System.out.println(normalNames.get(i) + ": " + normalCounts.get(i));
            }
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
        System.out.println("\nAppliances of type: " + type);
        int count = 0;

        for (Appliance a : appliances) {
            if (type.equalsIgnoreCase("Smart") && a instanceof SmartAppliance) {
                System.out.println(a);
                count++;
            } else if (type.equalsIgnoreCase("Normal") && a instanceof NormalAppliance) {
                System.out.println(a);
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No appliances of type '" + type + "' found.");
        } else {
            System.out.println("Total " + type + " appliances: " + count);
        }
    }

}

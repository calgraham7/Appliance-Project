import java.util.Scanner;

public class MainMenu {
    private ApplianceManager manager;
    private Scanner scanner;

    // Constructor
    public MainMenu() {
        manager = new ApplianceManager();
        scanner = new Scanner(System.in);
    }

    // Main menu loop
    public void run() {
        int choice;
        do {
            printMenu();
            choice = getIntInput("\nEnter your choice: ");

            switch (choice) {
                case 1 -> loadAppliancesFromFile();
                case 2 -> addAppliance();
                case 3 -> removeAppliance();
                case 4 -> findApplianceByID();
                case 5 -> listAppliancesByLocation();
                case 6 -> listAppliancesByType();
                case 7 -> manager.printSummaryReport();
                case 8 -> manager.printAllAppliances();
                case 9 -> runSimulation();
                case 10 -> System.out.println("\nExiting...");
                default -> System.out.println("\nInvalid choice. Please try again.");
            }
        } while (choice != 10);

        scanner.close();
    }

    // Print the main menu
    private void printMenu() {
        System.out.println("\n=== Power Usage Simulation System ===");
        System.out.println("1. Load appliances from file");
        System.out.println("2. Add a new appliance");
        System.out.println("3. Remove an appliance");
        System.out.println("4. Find an appliance by ID");
        System.out.println("5. List appliances by location");
        System.out.println("6. List appliances by type");
        System.out.println("7. Print summary report");
        System.out.println("8. Print all appliances");
        System.out.println("9. Run Simulation");       
        System.out.println("10. Exit");
    }
    
    // Run the simulation
    private void runSimulation() {
        int duration = getIntInput("\nEnter simulation duration (minutes): ");
        int interval = getIntInput("Enter time interval (minutes): ");
        int warningLevel = getIntInput("Enter wattage warning level: ");

        Simulator simulator = new Simulator(manager);
        simulator.run(duration, interval, warningLevel);
    }

    // Get integer input with validation
    private int getIntInput(String prompt) {
        int result = -1;
        while (result == -1) {
            try {
                System.out.print(prompt);
                result = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter a number.");
            }
        }
        return result;
    }

    // Menu option: Load appliances from file
    private void loadAppliancesFromFile() {
        System.out.print("\nEnter the filename: ");
        String filename = scanner.nextLine().trim();
        manager.loadAppliancesFromFile(filename);
    }

    // Menu option: Add a new appliance
    private void addAppliance() {
        try {
            long location = getLongInput("\nEnter location (8 digits): ");
            String name = getStringInput("Enter appliance name: ");
            int onWatts = getIntInput("Enter on wattage: ");
            int offWatts = getIntInput("Enter off wattage: ");
            double probOn = getDoubleInput("Enter probability (0.0 to 1.0): ");

            Appliance appliance = new Appliance(location, name, onWatts, offWatts, probOn);
            manager.addAppliance(appliance);
            System.out.println("\nAppliance added successfully.");

        } catch (Exception e) {
            System.out.println("\nError adding appliance: " + e.getMessage());
        }
    }

    // Menu option: Remove an appliance
    private void removeAppliance() {
        int applianceID = getIntInput("\nEnter appliance ID to remove: ");
        boolean removed = manager.removeAppliance(applianceID);
        System.out.println("\nAppliance removed: " + (removed ? "Success" : "Not Found"));
    }

    // Menu option: Find an appliance by ID
    private void findApplianceByID() {
        int applianceID = getIntInput("\nEnter appliance ID to find: ");
        Appliance found = manager.findApplianceByID(applianceID);
        System.out.println("\nAppliance: " + (found != null ? found : "Not Found"));
    }

    // Menu option: List appliances by location
    private void listAppliancesByLocation() {
        long location = getLongInput("\nEnter location (8 digits): ");
        manager.getAppliancesByLocation(location).forEach(System.out::println);
    }

    // Menu option: List appliances by type
    private void listAppliancesByType() {
        String type = getStringInput("\nEnter appliance type: ");
        manager.getAppliancesByType(type).forEach(System.out::println);
    }

    // Get long input with validation
    private long getLongInput(String prompt) {
        long result = -1;
        while (result == -1) {
            try {
                System.out.print(prompt);
                result = Long.parseLong(scanner.nextLine().trim());
                if (String.valueOf(result).length() != 8) {
                    System.out.println("\nLocation must be exactly 8 digits.");
                    result = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter an 8-digit number.");
            }
        }
        return result;
    }

    // Get double input with validation
    private double getDoubleInput(String prompt) {
        double result = -1;
        while (result == -1) {
            try {
                System.out.print(prompt);
                result = Double.parseDouble(scanner.nextLine().trim());
                if (result < 0.0 || result > 1.0) {
                    System.out.println("\nProbability must be between 0.0 and 1.0.");
                    result = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter a number between 0.0 and 1.0.");
            }
        }
        return result;
    }

    // Get string input with validation
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Main method to start the menu system
    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        menu.run();
    }
}

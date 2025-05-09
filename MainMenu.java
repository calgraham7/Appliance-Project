import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) throws ApplianceException {
        Scanner scanner = new Scanner(System.in);
        ApplianceManager manager = new ApplianceManager();
        boolean running = true;

        while (running) {
            System.out.println("\n--- Appliance Manager Main Menu ---");
            System.out.println("1. Add Appliance Manually");
            System.out.println("2. Delete Appliances By Name");
            System.out.println("3. Load Appliances from File");
            System.out.println("4. Print All Appliances");
            System.out.println("5. Print Summary Report");
            System.out.println("6. Print Appliances by Location");
            System.out.println("7. Print Appliances by Type");
            System.out.println("8. Run Simulation");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter location (8 digits): ");
                        long location = scanner.nextLong();
                        scanner.nextLine(); // Clear newline

                        System.out.print("Enter appliance name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter on wattage: ");
                        int onWattage = scanner.nextInt();

                        System.out.print("Enter off wattage: ");
                        int offWattage = scanner.nextInt();

                        System.out.print("Enter probability (0.0 to 1.0): ");
                        double probOn = scanner.nextDouble();

                        System.out.print("Is this a smart appliance? (true/false): ");
                        boolean isSmart = scanner.nextBoolean();

                        Appliance appliance;
                        if (isSmart) {
                            System.out.print("Enter reduce percentage (0.0 to 1.0): ");
                            double reducePercent = scanner.nextDouble();
                            appliance = new SmartAppliance(location, name, onWattage, offWattage, probOn,
                                    isSmart, reducePercent);
                        } else {
                            appliance = new NormalAppliance(location, name, onWattage, offWattage, probOn, isSmart, 0.0);
                        }

                        manager.addAppliance(appliance);
                        System.out.println("Appliance added successfully.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        scanner.nextLine(); // Clear buffer in case of input mismatch
                    }
                    break;

                case 2:
                    System.out.print("Enter the appliance name to delete: ");
                    String deleteName = scanner.nextLine();
                    int deleted = manager.removeAppliancesByName(deleteName);
                    System.out.println("Deleted " + deleted + " appliance(s) named '" + deleteName + "'.");
                    break;

                case 3:
                    // Load from file
                    System.out.print("Enter the filename to load appliances from: ");
                    String filename = scanner.nextLine();
                    manager.addAppliancesFromFile(filename);
                    break;

                case 4:
                    // Print all appliances
                    manager.printAllAppliances();
                    break;

                case 5:
                    // Print summary report
                    manager.printSummaryReport();
                    break;

                case 6:
                    // Print appliances by location
                    System.out.print("Enter location (8 digits): ");
                    long loc = scanner.nextLong();
                    manager.printAppliancesByLocation(loc);
                    break;

                case 7:
                    // Print appliances by type
                    System.out.print("Enter appliance type (Smart or Normal): ");
                    String type = scanner.nextLine();
                    manager.printAppliancesByType(type);
                    break;

                case 8:
                    // Run simulation
                    System.out.print("Enter simulation time length (minutes): ");
                    int simTime = scanner.nextInt();
                    System.out.print("Enter timing interval (minutes): ");
                    int interval = scanner.nextInt();
                    System.out.print("Enter wattage warning level: ");
                    int warningLevel = scanner.nextInt();
                    Simulator simulator = new Simulator(manager);
                    simulator.run(simTime, interval, warningLevel);
                    break;

                case 9:
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }

        scanner.close();
    }
}

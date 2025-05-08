public class ApplianceTest {
    public static void main(String[] args) {
        // Test 1: Valid appliance creation
        Appliance a1 = new Appliance(12345678L, "Fridge", 100, 10, 0.75);
        System.out.println("Test 1: " + a1);

        // Test 2: Invalid location (too short)
        Appliance a2 = new Appliance(123L, "Washer", 200, 15, 0.5);
        System.out.println("Test 2 (Invalid location): " + a2.getLocation() + " (Expected: 99999999)");

        // Test 3: Null name
        Appliance a3 = new Appliance(12345678L, null, 150, 20, 0.6);
        System.out.println("Test 3 (Null name): " + a3.getName() + " (Expected: UNKNOWN)");

        // Test 4: Negative onWattage
        Appliance a4 = new Appliance(12345678L, "Heater", -50, 10, 0.3);
        System.out.println("Test 4 (Negative onWattage): " + a4.getOnWatts() + " (Expected: 1)");

        // Test 5: Probability > 1
        Appliance a5 = new Appliance(12345678L, "Microwave", 1200, 5, 1.5);
        System.out.println("Test 5 (Probability > 1): " + a5.getProbOn() + " (Expected: 0.0)");

        // Test 6: equals() check (should be true)
        Appliance a6 = new Appliance(12345678L, "Fridge", 100, 10, 0.75);
        System.out.println("Test 6 (Equals a1 & a6): " + a1.equals(a6) + " (Expected: true)");

        // Test 7: compareTo() ordering
        Appliance a7 = new Appliance(12345678L, "TV", 50, 5, 0.4);
        Appliance a8 = new Appliance(12345678L, "AC", 150, 8, 0.9);
        System.out.println("Test 7 (compareTo a7 vs a8): " + a7.compareTo(a8) + " (Expected: > 0 since a8 has higher onWattage)");

        // Test 8: ID uniqueness
        System.out.println("Test 8: Appliance IDs -> " + a1.getApplianceID() + ", " + a2.getApplianceID() + ", " + a3.getApplianceID());

        //SmartAppliance Testing
        // Test 1: Valid smart appliance creation
        SmartAppliance sa1 = new SmartAppliance(12345678L, "Smart Fridge", 100, 10, 0.75, 0.25);
        System.out.println("Test 1b: " + sa1); // Expect Reduce%=0.25

        // Test 2: Invalid reduce percentage (> 1.0)
        SmartAppliance sa2 = new SmartAppliance(12345678L, "Smart Oven", 200, 15, 0.5, 1.5);
        System.out.println("Test 2b (Invalid reduce % > 1): " + sa2.getReducePercentage() + " (Expected: 1.0)");

        // Test 3: Invalid reduce percentage (< 0)
        SmartAppliance sa3 = new SmartAppliance(12345678L, "Smart Washer", 150, 20, 0.6, -0.1);
        System.out.println("Test 3b (Invalid reduce % < 0): " + sa3.getReducePercentage() + " (Expected: 1.0)");

        // Test 4: Reduce percentage at exact bounds
        SmartAppliance sa4 = new SmartAppliance(12345678L, "Smart Dryer", 180, 18, 0.4, 0.0);
        SmartAppliance sa5 = new SmartAppliance(12345678L, "Smart Dishwasher", 180, 18, 0.4, 1.0);
        System.out.println("Test 4a (Reduce % = 0.0): " + sa4.getReducePercentage() + " (Expected: 0.0)");
        System.out.println("Test 4b (Reduce % = 1.0): " + sa5.getReducePercentage() + " (Expected: 1.0)");

        // Test 5: equals() with same values
        SmartAppliance sa6 = new SmartAppliance(12345678L, "Smart Fridge", 100, 10, 0.75, 0.25);
        System.out.println("Test 5b (Equals sa1 & sa6): " + sa1.equals(sa6) + " (Expected: true)");

        // Test 6: equals() with different reduce percentage
        SmartAppliance sa7 = new SmartAppliance(12345678L, "Smart Fridge", 100, 10, 0.75, 0.50);
        System.out.println("Test 6b (Equals sa1 & sa7): " + sa1.equals(sa7) + " (Expected: false)");

        // Test 7: toString() output
        System.out.println("Test 7b (toString): " + sa1.toString());

        // Test 8: Appliance equality override check with Appliance
        Appliance ba1 = new Appliance(12345678L, "Smart Fridge", 100, 10, 0.75);
        System.out.println("Test 8b (SmartAppliance equals Appliance): " + sa1.equals(ba1) + " (Expected: false)");


                // Test program for Appliance Manager
        ApplianceManager manager = new ApplianceManager();

        // Test 1: Add appliances
        Appliance ma1 = new Appliance(12345678L, "Fridge", 100, 10, 0.75);
        Appliance ma2 = new Appliance(12345678L, "Oven", 150, 20, 0.5);
        Appliance ma3 = new Appliance(87654321L, "Fridge", 100, 10, 0.75);
        manager.addAppliance(ma1);
        manager.addAppliance(ma2);
        manager.addAppliance(ma3);
        System.out.println("Test 1c: Added 3 appliances.");
        manager.printAllAppliances();

        // Test 2: Find by ID
        Appliance found = manager.findApplianceByID(ma1.getApplianceID());
        System.out.println("Test 2c (Find by ID): " + (found != null && found.equals(a1)));

        // Test 3: Remove by ID
        boolean removed = manager.removeAppliance(ma2.getApplianceID());
        System.out.println("Test 3c (Remove by ID): " + removed + " (Expected: true)");

        // Test 4: Remove non-existent ID
        boolean removedInvalid = manager.removeAppliance(999999);
        System.out.println("Test 4c (Remove invalid ID): " + removedInvalid + " (Expected: false)");

        // Test 5: Get appliances by location
        System.out.println("Test 5c (Get by location 12345678):");
        for (Appliance ma : manager.getAppliancesByLocation(12345678L)) {
            System.out.println(ma);
        }

        // Test 6: Get appliances by type
        System.out.println("Test 6c (Get by type 'Fridge'):");
        for (Appliance ma : manager.getAppliancesByType("Fridge")) {
            System.out.println(ma);
        }

        // Test 7: Print summary report
        System.out.println("Test 7c (Summary Report):");
        manager.printSummaryReport();

        // Test 8: Print all appliances
        System.out.println("Test 8c (All Appliances):");
        manager.printAllAppliances();

        // Test 9: Load from invalid file
        System.out.println("Test 9c (Load from bad file):");
        manager.loadAppliancesFromFile("nonexistent.csv"); // Expect FileNotFound error message
    }
}

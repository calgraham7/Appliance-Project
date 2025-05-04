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
    }
}

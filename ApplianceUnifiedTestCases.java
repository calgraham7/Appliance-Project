public class ApplianceUnifiedTest {
    public static void main(String[] args) throws ApplianceException {
        System.out.println("=== Appliance Unified Test Cases ===");

        // ---------- Appliance Tests ----------
        Appliance a1 = new Appliance(12345678, "Fridge", 100, 10, 0.75);
        Appliance a2 = new Appliance(12345678, "Fridge", 100, 10, 0.75);
        Appliance a3 = new Appliance(12345678, "TV", 80, 5, 0.6);

        System.out.println("Appliance Test 1: " + a1);
        System.out.println("Appliance Test 2 (equals): " + a1.equals(a2)); // true
        System.out.println("Appliance Test 3 (compareTo): " + a3.compareTo(a1)); // > 0 if a1 has more onWatt

        // ---------- SmartAppliance Tests ----------
        SmartAppliance sa1 = new SmartAppliance(12345678, "SmartTV", 200, 20, 0.6, true, 0.5);
        SmartAppliance sa2 = new SmartAppliance(12345678, "SmartTV", 200, 20, 0.6, true, 0.5);

        System.out.println("SmartAppliance Test 1: Reduce% = " + sa1.getReducePercentage());
        System.out.println("SmartAppliance Test 2 (equals): " + sa1.equals(sa2)); // true
        System.out.println("SmartAppliance Test 3 (toString): " + sa1);

        // ---------- ApplianceManager Tests ----------
        ApplianceManager manager = new ApplianceManager();
        manager.addAppliance(a1);
        manager.addAppliance(a3);
        manager.addAppliance(sa1);

        System.out.println("ApplianceManager Test 1: Print All Appliances");
        manager.printAllAppliances();

        System.out.println("ApplianceManager Test 2: Summary Report");
        manager.printSummaryReport();

        System.out.println("ApplianceManager Test 3a: Filter by Smart Type");
        manager.printAppliancesByType("Smart");

        System.out.println("ApplianceManager Test 3b: Filter by Normal Type");
        manager.printAppliancesByType("Normal");

        // ---------- NormalAppliance Tests ----------
        try {
            NormalAppliance na1 = new NormalAppliance(12345678, "Lamp", 60, 5, 0.8, false, 0.0);
            NormalAppliance na2 = new NormalAppliance(12345678, "Heater", 150, 20, 0.7, false, 0.0);
            NormalAppliance na3 = new NormalAppliance(12345678, "Fan", 100, 10, 0.6, false, 0.0);
            NormalAppliance na4 = new NormalAppliance(12345678, "AC", 200, 15, 0.9, false, 0.0);

            System.out.println("NormalAppliance Test 1: " + na1);
            System.out.println(
                    "NormalAppliance Test 2: OnWatts = " + na2.getOnWatts() + ", OffWatts = " + na2.getOffWatts());
            System.out.println("NormalAppliance Test 3: Counter = " + na3.getNormalCounter()); // always 1 unless made
                                                                                               // static
            System.out.println("NormalAppliance Test 4: toString = " + na4.toString());

            manager.addAppliance(na1);
            manager.addAppliance(na2);
            manager.addAppliance(na3);
            manager.addAppliance(na4);

        } catch (Exception e) {
            System.out.println("Unexpected error in NormalAppliance tests: " + e.getMessage());
        }

        System.out.println("=== All Tests Completed Successfully ===");
    }
}

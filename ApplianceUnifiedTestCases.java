
// ApplianceUnifiedTestCases.java
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class ApplianceUnifiedTestCases {
    public static void main(String[] args) {
        testAppliance();
        testSmartAppliance();
        testNormalAppliance();
        testApplianceManager();
    }

    private static void testAppliance() {
        log("--- Appliance Class Tests ---");

        // Invalid probability
        try {
            new Appliance(12345678L, "Fridge", 100, 10, -0.2);
        } catch (ApplianceException e) {
            log("Invalid probability caught: " + e.getMessage());
        }

        try {
            Appliance alwaysOn = new Appliance(12345678L, "Light", 60, 5, 1.0);
            log("isOn(1.0): " + alwaysOn.isOnThisInterval());

            Appliance neverOn = new Appliance(12345678L, "Fan", 70, 5, 0.0);
            log("isOn(0.0): " + neverOn.isOnThisInterval());

            Appliance a = new Appliance(12345678L, "Microwave", 800, 100, 0.5);
            log("Formatted Report: " + a.toString());
        } catch (Exception e) {
            logException(e);
        }
    }

    private static void testSmartAppliance() {
        log("--- SmartAppliance Tests ---");
        try {
            SmartAppliance sa = new SmartAppliance(12345678L, "SmartAC", 1000, 50, 1.0, true, 0.6);
            log("getWattage with reduction: " + sa.getWattage(true));
            log("getWattage without reduction: " + sa.getWattage(false));
            log("isOn(1.0): " + sa.isOnThisInterval());

            SmartAppliance sa2 = new SmartAppliance(12345678L, "SmartHeater", 1000, 100, 0.0, true, 0.8);
            log("isOn(0.0): " + sa2.isOnThisInterval());
        } catch (Exception e) {
            logException(e);
        }
    }

    private static void testNormalAppliance() {
        log("--- NormalAppliance Tests ---");
        try {
            NormalAppliance na = new NormalAppliance(12345678L, "Toaster", 800, 80, 1.0, false, 0.0);
            log("getWattage: " + na.getWattage(true));
            log("isOn(1.0): " + na.isOnThisInterval());
        } catch (Exception e) {
            logException(e);
        }

        try {
            new NormalAppliance(12345678L, "Fan", 100, -10, 0.5, false, 0.0);
        } catch (ApplianceException e) {
            log("Caught invalid offWattage: " + e.getMessage());
        }

        try {
            NormalAppliance na3 = new NormalAppliance(12345678L, "Lamp", 100, 10, 0.0, false, 0.0);
            log("isOn(0.0): " + na3.isOnThisInterval());
        } catch (Exception e) {
            logException(e);
        }
    }

    private static void testApplianceManager() {
        log("--- ApplianceManager Tests ---");
        ApplianceManager manager = new ApplianceManager();

        try {
            Appliance a1 = new Appliance(12345678L, "Fridge", 100, 10, 0.75);
            Appliance a2 = new Appliance(12345678L, "Fridge", 100, 10, 0.75);
            manager.addAppliance(a1);
            manager.addAppliance(a2);

            log("Find by type 'Fridge':");
            manager.printAppliancesByType("Fridge");
        } catch (Exception e) {
            logException(e);
        }
    }

    private static void log(String message) {
        System.out.println(message);
    }

    private static void logException(Exception e) {
        try (FileWriter fw = new FileWriter("log.txt", true)) {
            fw.write(e.getMessage() + "\n");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}

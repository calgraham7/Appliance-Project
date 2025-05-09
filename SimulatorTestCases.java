import java.io.FileWriter;
import java.io.IOException;

public class SimulatorTestCases {
    public static void main(String[] args) {
        log("--- Simulator Tests ---");
        ApplianceManager manager = new ApplianceManager();
        try {
            manager.addAppliance(new Appliance(12345678L, "Fridge", 100, 10, 0.75));
            manager.addAppliance(new Appliance(12345678L, "Fridge", 100, 10, 0.75));
            Simulator sim = new Simulator(manager);
            sim.run(10, 2, 100);
        } catch (Exception e) {
            logException(e);
        }
    }

    private static void log(String message) {
        System.out.println(message);
    }

    private static void logException(Exception e) {
        try (FileWriter fw = new FileWriter("log.txt", true)) {
            fw.write("[Simulator] " + e.getMessage() + "\n");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
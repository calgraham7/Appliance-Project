import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Simulator {
    private ApplianceManager manager;

    public Simulator(ApplianceManager manager) {
        this.manager = manager;
    }

    public void run(int duration, int interval, int warningLevel) {
        int totalIntervals = duration / interval;
        int[] affectedPerInterval = new int[totalIntervals];
        int maxAffected = 0;

        try (PrintWriter writer = new PrintWriter(new FileWriter("detailed_report.txt"))) {
            for (int i = 0; i < totalIntervals; i++) {
                int minute = i * interval;
                HashMap<Long, Integer> locationWattage = new HashMap<>();
                HashMap<Long, ArrayList<Appliance>> affectedLocations = new HashMap<>();

                // 1. Compute usage
                for (Appliance a : manager.getAllAppliances()) {
                    boolean isOn = Math.random() < a.getProbOn();
                    int watts = isOn ? a.getOnWatts() : a.getOffWatts();

                    if (isOn && a instanceof SmartAppliance) {
                        SmartAppliance sa = (SmartAppliance) a;
                        watts = (int) (watts * sa.getReducePercentage()); // Smart reduction
                    }

                    long loc = a.getLocation();
                    locationWattage.put(loc, locationWattage.getOrDefault(loc, 0) + watts);
                }

                // 2. Identify affected locations
                for (Long loc : locationWattage.keySet()) {
                    if (locationWattage.get(loc) > warningLevel) {
                        affectedPerInterval[i]++;
                        if (!affectedLocations.containsKey(loc)) {
                            affectedLocations.put(loc, new ArrayList<>());
                        }
                        for (Appliance a : manager.getAllAppliances()) {
                            if (a.getLocation() == loc) {
                                affectedLocations.get(loc).add(a);
                            }
                        }
                    }
                }

                // 3. Write interval details to file
                writer.println("Interval " + (i + 1) + " - Minute " + minute);
                for (Long loc : affectedLocations.keySet()) {
                    writer.println("  Location " + loc + " (Exceeded Limit)");
                    for (Appliance a : affectedLocations.get(loc)) {
                        writer.println("    " + a.toString());
                    }
                }
                writer.println();

                // Update max tracker
                if (affectedPerInterval[i] > maxAffected) {
                    maxAffected = affectedPerInterval[i];
                }
            }

            // 4. Summary to screen
            System.out.println("\n--- Simulation Summary Report ---");
            for (int i = 0; i < totalIntervals; i++) {
                System.out.println("Interval " + (i + 1) + ": " + affectedPerInterval[i] + " locations affected");
            }
            System.out.println("Max affected locations in an interval: " + maxAffected);
            System.out.println("Detailed report saved to 'detailed_report.txt'");

        } catch (IOException e) {
            System.out.println("Error writing detailed report: " + e.getMessage());
        }
    }
}

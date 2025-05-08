import java.util.ArrayList;
import java.util.Random;

public class Simulator {
    private ApplianceManager manager;
    private Random random;

    public Simulator(ApplianceManager manager) {
        this.manager = manager;
        this.random = new Random();
    }

    // Run the simulation
    public void run(int duration, int interval, int warningLevel) {
        int currentTime = 0;

        System.out.println("\nRunning Simulation...");

        while (currentTime < duration) {
            int totalWattage = 0;
            ArrayList<Appliance> activeAppliances = new ArrayList<>();

            // Calculate the total wattage for this interval
            for (Appliance appliance : manager.getAllAppliances()) {
                if (isApplianceOn(appliance)) {
                    totalWattage += appliance.getOnWatts();
                    activeAppliances.add(appliance);
                }
            }

            // Check for overload
            if (totalWattage > warningLevel) {
                System.out.println("WARNING: Grid overload at minute " + currentTime + ". Total wattage: " + totalWattage);
                manageOverload(activeAppliances, totalWattage, warningLevel);
            }

            currentTime += interval;
        }

        System.out.println("\nSimulation complete.");
    }

    // Determine if an appliance is on based on its probability
    private boolean isApplianceOn(Appliance appliance) {
        return random.nextDouble() < appliance.getProbOn();
    }

    // Manage grid overload by cycling off appliances
    private void manageOverload(ArrayList<Appliance> activeAppliances, int totalWattage, int warningLevel) {
        activeAppliances.sort((a, b) -> Integer.compare(b.getOnWatts(), a.getOnWatts()));

        for (Appliance appliance : activeAppliances) {
            totalWattage -= appliance.getOnWatts();
            System.out.println("  Cycling off appliance ID " + appliance.getApplianceID() + " to reduce load.");
            if (totalWattage <= warningLevel) {
                break;
            }
        }
    }
}

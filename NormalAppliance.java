public class NormalAppliance extends Appliance {

    private int normalCounter = 0;
    private boolean isSmart = false;

    public NormalAppliance() throws ApplianceException {
        super(0, null, 0, 0, 0);
        normalCounter++;
    }

    public NormalAppliance(long newLocation, String newName, int newOnWatt, int newOffWatt, double newProbability,
            boolean newIsSmart,
            double newReducePercentage) throws ApplianceException {
        super(newLocation, newName, newOnWatt, newOffWatt, newProbability);
        newIsSmart = getIsSmart();
        normalCounter++;
    }

    public boolean getIsSmart() {
        return isSmart;
    }

    public int getNormalCounter() {
        return normalCounter;
    }

    public String toString() {
        return super.toString();
    }

}

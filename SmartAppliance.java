public class SmartAppliance extends Appliance {
    private double reducePercentage;
    private static final double DEFAULT_REDUCE_PERCENTAGE = 1.0;
    private boolean isSmart;

    public SmartAppliance(long location, String name, int onWatt, int offWatt,
                      double probOn, boolean isSmart, double reducePercentage)
        throws ApplianceException {
    super(location, name, onWatt, offWatt, probOn);
        if (reducePercentage < 0.0 || reducePercentage > 1.0)
        throw new ApplianceException("Reduce percentage must be between 0.0 and 1.0");
        setIsSmart();
        setReducePercentage(reducePercentage);
    }

    public double getReducePercentage() {
        return reducePercentage;
    }

    public void setIsSmart() {
        isSmart = true;
    }

    public void setReducePercentage(double newReducePercentage) {
        if (newReducePercentage > 1.0 || newReducePercentage < 0) {
            reducePercentage = DEFAULT_REDUCE_PERCENTAGE;
        } else
            reducePercentage = newReducePercentage;
    }

    public boolean equals(SmartAppliance x) {
        if (this.getName().equals(x.getName()) && this.getOnWatts() == x.getOnWatts()
                && this.getOffWatts() == x.getOffWatts() && this.getProbOn() == x.getProbOn()
                && this.getReducePercentage() == x.getReducePercentage()) {
            return true;
        } else
            return false;
    }

    public boolean equals(Appliance a) {

        return false;
    }

    public String toString() {
        return super.toString() + " Reduce%=" + reducePercentage;
    }
}

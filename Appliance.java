public class Appliance implements Comparable<Appliance> {
	
	String name = "";
	private int onWattage, offWattage;
	private double probabilityOn;
	private long location;
	private static int nextID = 1;
	private int applianceID = 0;

	// class constants
	private static final String DEFAULT_NAME = "UNKNOWN";
	private static final int DEFAULT_ON_WATTAGE = 1;
	private static final int DEFAULT_OFF_WATTAGE = 0;
	private static final double DEFAULT_PROBABILITY = 0.0;
	private static final long DEFAULT_LOCATION = 99999999;

	public Appliance(long newLocation, String newName, int newOnWatt, int newOffWatt, double newProbability) {
		setLocation(newLocation);
		setName(newName);
		setOnWatts(newOnWatt);
		setOffWatts(newOffWatt);
		setProbOn(newProbability);
		this.applianceID = nextID++;
	}

	public int getApplianceID() {
		return applianceID;
	}

	public long getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	public int getOnWatts() {
		return onWattage;
	}

	public int getOffWatts() {
		return offWattage;
	}

	public double getProbOn() {
		return probabilityOn;
	}

	public void setLocation(long newLocation) {
		if (newLocation > 99999999 || newLocation < 10000000) {
			location = DEFAULT_LOCATION;
		} else {
			location = newLocation;
		}
	}

	public void setName(String newName) {
		if (newName == null || newName.equals("")) {
			name = DEFAULT_NAME;
		} else {
			name = newName;
		}
	}

	public void setOnWatts(int newOnWatts) {
		if (newOnWatts < 0) {
			onWattage = DEFAULT_ON_WATTAGE;
		} else {
			onWattage = newOnWatts;
		}
	}

	public void setOffWatts(int newOffWatts) {
		if (newOffWatts <= 0) {
			offWattage = DEFAULT_OFF_WATTAGE;
		} else {
			offWattage = newOffWatts;
		}
	}

	public void setProbOn(double newProbOn) {
		if (newProbOn > 1.0 || newProbOn < 0.0) {
			probabilityOn = DEFAULT_PROBABILITY;
		} else {
			probabilityOn = newProbOn;
		}
	}

	public boolean equals(Appliance x) {
		if (this.getName().equals(x.getName()) && this.getOnWatts() == x.getOnWatts()
				&& this.getOffWatts() == x.getOffWatts() && this.getProbOn() == x.getProbOn()) {
			return true;
		} else
			return false;
	}

	public boolean equals(SmartAppliance a) {

		return false;
	}

	public int compareTo(Appliance other) {
    if (this.getOnWatts() != other.getOnWatts()) {
        return Integer.compare(other.getOnWatts(), this.getOnWatts()); // Descending
    }
    return Integer.compare(this.getOffWatts(), other.getOffWatts()); // Ascending
}

	public String toString() {
		return this.getApplianceID() + " Loc=" + location + " Name=" + name + " OnW=" + onWattage + " OffW=" + offWattage
				+ " ProbOn=" + probabilityOn;
	}
}

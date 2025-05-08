public class NormalAppliance extends Appliance {


private int normalCounter = 0;
public NormalAppliance() {
        super(0, null, 0, 0, 0);
            normalCounter++;
        }
       
    
public NormalAppliance(long newLocation, String newName, int newOnWatt, int newOffWatt, double newProbability,
double newReducePercentage) throws ApplianceException {
        super(newLocation, newName, newOnWatt, newOffWatt, newProbability); 
        normalCounter++;
    }

public int getNormalCounter (){
        return normalCounter;
    }

public String toString() {
		return  super.toString();
	}

}


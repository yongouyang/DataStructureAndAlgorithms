package fun.rivercrossing;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

public class RiverState {

    private SortedSet<RiverRole> westBank;
    private SortedSet<RiverRole> eastBank;
    private BoatLocation boatLocation;

    public RiverState(Collection<RiverRole> westBank, Collection<RiverRole> eastBank, BoatLocation boatLocation) {
        this.westBank = new TreeSet<RiverRole>(westBank);
        this.eastBank = new TreeSet<RiverRole>(eastBank);
        this.boatLocation = boatLocation;
    }

    public SortedSet<RiverRole> getWestBank() {
        return new TreeSet<RiverRole>(westBank);
    }

    public SortedSet<RiverRole> getEastBank() {
        return new TreeSet<RiverRole>(eastBank);
    }

    public BoatLocation getBoatLocation() {
        return boatLocation;
    }

    @Override
    public String toString() {
        return "State{westBank=" + westBank + ", eastBank=" + eastBank + ", boatLocation=" + boatLocation + "}";
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RiverState)) return false;

        RiverState riverState = (RiverState) o;

        return eastBank.equals(riverState.eastBank) &&
               westBank.equals(riverState.westBank) &&
               boatLocation.equals(riverState.boatLocation);
    }

    public int hashCode() {
        int result;
        result = westBank.hashCode();
        result = 31 * result + eastBank.hashCode();
        result = 31 * result + boatLocation.hashCode();
        return result;
    }
}

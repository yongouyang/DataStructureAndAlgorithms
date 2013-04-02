package fun.rivercrossing;

public class RiverRole implements Comparable<RiverRole> {

    public final String name;
    public final boolean canSailTheBoat;

    public RiverRole(String name, boolean canSailTheBoat) {
        this.name = name;
        this.canSailTheBoat = canSailTheBoat;
    }

    @Override
    public int compareTo(RiverRole o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
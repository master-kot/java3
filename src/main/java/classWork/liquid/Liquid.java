package classWork.liquid;

public class Liquid {

    private double volume, weight, ro;

    public Liquid(double volume, double weight) {
        this.volume = volume;
        this.weight = weight;
        ro = weight / volume;
    }

    public double get() {
        return ro;
    }

}

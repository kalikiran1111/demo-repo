package Bin;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Bin {
    ArrayList<Double> objects;

    double maxWeight;

    double totalWeight;

    Bin() {
        this.maxWeight = 10.00;
        this.totalWeight = 0.00;
        this.objects = new ArrayList<>();
    }

    Bin(double maxWeight) {
        this.maxWeight = maxWeight;
        this.totalWeight = 0.00;
        this.objects = new ArrayList<>();
    }

    public boolean addItem(double weight) {
        if ((weight <= this.maxWeight) && ((this.totalWeight + weight) <= this.maxWeight)) {
            this.totalWeight = this.totalWeight + weight;
            objects.add(weight);
            return true;
        }
        return false;
    }

    public int getNumberOfObjects() {
        return objects.size();
    }

    @Override
    public String toString() {
        return "contains objects with weight " + objects.stream()
                .mapToInt(Double::intValue)
                .mapToObj(x -> "" + x)
                .collect(Collectors.joining(" "));
    }
}

package data;

import java.util.Arrays;

public class IrisSpecies {
    public int id;
    public double [] measurements;
    public double [] speciesValue;

    public IrisSpecies(int id, double sepalLength, double sepalWidth, double petalLength, double petalWidth, String species) {
        this.id = id;
        this.measurements = new double[]{sepalLength, sepalWidth, petalLength, petalWidth};
        switch (species) {
            case "Iris-setosa":
                speciesValue = new double[]{1.0};
                break;
            case "Iris-versicolor":
                speciesValue = new double[]{2.0};
                break;
            case "Iris-virginica":
                speciesValue = new double[]{3.0};
                break;
            default:
                throw new IllegalArgumentException("Unknown species: " + species);
        }
    }

    public String toString() {
        return "IrisSpecies{id=" + id + ", measurements=" + Arrays.toString(measurements) + ", speciesValue=" + Arrays.toString(speciesValue) + "}";
    }
}

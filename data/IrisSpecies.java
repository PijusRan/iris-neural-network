package data;

public class IrisSpecies {
    public int id;
    public double sepalLength;
    public double sepalWidth;
    public double petalLength;
    public double petalWidth;
    public String species;

    public IrisSpecies(int id, double sepalLength, double sepalWidth, double petalLength, double petalWidth, String species) {
        this.id = id;
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
        this.species = species;
    }
}

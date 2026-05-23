
import data.IrisSpecies;
import data.dataHandler;
import java.util.ArrayList;
import model.Network;
import training.evolutionCycle;
import training.fitnessTest;

public class Main {
    public static void main(String[] args) {
        // Create sample data
        ArrayList<IrisSpecies> sampleData = new ArrayList<>();
        dataHandler.readData("data/Iris.csv", sampleData);

        // Create first network
        Network network = new Network();
        network.inputLayer.setInputValues(sampleData.get(0).measurements);
        network.run();
        double oldFitness = fitnessTest.testFitness(network, sampleData);

        System.out.println("Before training:");
        System.out.println("Fitness: " + oldFitness);

        // Run evolution cycle
        network = evolutionCycle.Run(sampleData, 10000, network);

        // Rerun test with best network
        network.inputLayer.setInputValues(sampleData.get(0).measurements);
        network.run();
        double newFitness = fitnessTest.testFitness(network, sampleData);

        System.out.println("After training:");
        System.out.println("Fitness: " + newFitness);
    }
}

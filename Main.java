
import data.IrisSpecies;
import data.dataHandler;
import java.util.ArrayList;
import model.Network;
import training.evolutionCycle;
import training.fitnessCalc;

public class Main {
    public static void main(String[] args) {
        // Create sample data
        ArrayList<IrisSpecies> sampleData = new ArrayList<>();
        dataHandler.readData("data/Iris.csv", sampleData);

        // Create first network
        Network network = new Network();
        network.inputLayer.setInputValues(sampleData.get(0).measurements);
        network.run();
        double[] output = network.getResult();
        network.fitness = fitnessCalc.calculateFitness(output, sampleData.get(0).speciesValue);

        // Run evolution cycle
        evolutionCycle.Run(sampleData, 10000, network);

        // Rerun test with best network
        network.inputLayer.setInputValues(sampleData.get(0).measurements);
        network.run();
        output = network.getResult();
        System.out.println("Fitness: " + network.fitness);
        System.out.println("Output: " + output[0]);
        System.out.println("Expected: " + sampleData.get(0).speciesValue[0]);
    }
}

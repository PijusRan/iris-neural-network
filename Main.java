import data.IrisSpecies;
import data.dataHandler;
import java.util.ArrayList;
import java.util.Scanner;
import model.Network;
import training.evolutionCycle;
import training.fitnessTest;

public class Main {
    static int SAMPLE_ID = 0;
    static int TRAINING_LOOPS = 100000;

    public static void main(String[] args) {
        // Create sample data
        ArrayList<IrisSpecies> sampleData = new ArrayList<>();
        dataHandler.readData("data/Iris.csv", sampleData);
        System.out.println("Sample input: " + sampleData.get(SAMPLE_ID).measurements[0] + ", " + sampleData.get(SAMPLE_ID).measurements[1] + ", " + sampleData.get(SAMPLE_ID).measurements[2] + ", " + sampleData.get(SAMPLE_ID).measurements[3]);
        System.out.println("Expected output: " + sampleData.get(SAMPLE_ID).speciesValue[0]);
        
        // Create first network
        Network network = new Network();
        network.inputLayer.setInputValues(sampleData.get(SAMPLE_ID).measurements);
        network.run();
        
        System.out.println("\n === INITIAL NETWORK === \n");
        System.out.println(network.inputLayer.toString()+"\n");
        System.out.println(network.hiddenLayer.toString()+"\n");
        System.out.println("Initial output: " + network.getResult()[0]);

        double initialFitness = fitnessTest.getMSE(network, sampleData);
        
        // Run evolution cycle
        network = evolutionCycle.Run(sampleData, TRAINING_LOOPS, network);

        // Rerun test with best network
        network.inputLayer.setInputValues(sampleData.get(SAMPLE_ID).measurements);
        network.run();

        System.out.println(" === AFTER TRAINING === \n");
        System.out.println(network.inputLayer.toString()+"\n");
        System.out.println(network.hiddenLayer.toString()+"\n");
        System.out.println("Final output: " + network.getResult()[0]);

        double finalFitness = fitnessTest.getMSE(network, sampleData);
        
        // Fitness comparison
        System.out.println("\n === FITNESS COMPARISON === \n");
        System.out.println("Initial MSE: " + initialFitness);
        System.out.println("Final MSE: " + finalFitness + "\n");

        // Test
        System.out.println(" === TEST === \n");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter sepal length: ");
        double sepalLength = scanner.nextDouble();
        System.out.print("Enter sepal width: ");
        double sepalWidth = scanner.nextDouble();
        System.out.print("Enter petal length: ");
        double petalLength = scanner.nextDouble();
        System.out.print("Enter petal width: ");
        double petalWidth = scanner.nextDouble();

        double[] userInput = {sepalLength, sepalWidth, petalLength, petalWidth};
        dataHandler.normalizeData(userInput, sampleData);

        network.setInput(userInput);
        network.run();
        double[] userOutput = network.getResult();

        System.out.println("\nPredicted output: " + userOutput[0]);
    }
}

import data.IrisSpecies;
import data.dataHandler;
import java.util.ArrayList;
import model.Network;
import training.evolutionCycle;

public class Main {
    static int SAMPLE_ID = 100;
    static int TRAINING_LOOPS = 1000;

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
        
        System.out.println(" === INITIAL NETWORK === \n");
        System.out.println(network.inputLayer.toString()+"\n");
        System.out.println(network.hiddenLayer.toString()+"\n");
        System.out.println("Initial output: " + network.getResult()[0]);
        

        // Run evolution cycle
        network = evolutionCycle.Run(sampleData, TRAINING_LOOPS, network);

        // Rerun test with best network
        network.inputLayer.setInputValues(sampleData.get(SAMPLE_ID).measurements);
        network.run();

        System.out.println(" === AFTER TRAINING === \n");
        System.out.println(network.inputLayer.toString()+"\n");
        System.out.println(network.hiddenLayer.toString()+"\n");
        System.out.println("Final output: " + network.getResult()[0]);
    }
}

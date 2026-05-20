package training ;

import data.IrisSpecies;
import java.util.ArrayList;
import model.Network;

public class evolutionCycle {
    public static void Run(ArrayList<IrisSpecies> sampleData, int loops, Network network){
        for (int i = 0; i < loops; i++) {
            for (int j = 0; j < sampleData.size(); j++) {
                // Create sample data
                IrisSpecies sample = sampleData.get(j);
                double[] sampleInput = sample.measurements;
                double[] sampleOutput = sample.speciesValue;

                // Generate random weights and biases
                double[][] newWeights = valueGen.generateRandomValues(network.inputLayer.in, network.inputLayer.out, -0.1, 0.1);
                double[] newBiases = valueGen.generateRandomValues(network.inputLayer.out, 1, -0.1, 0.1)[0];

                // Create new network with values
                Network newNetwork = network.clone();
                newNetwork.inputLayer.setWeights(newWeights);
                newNetwork.inputLayer.setBiases(newBiases);

                // Run the network and get output
                newNetwork.setInput(sampleInput);
                newNetwork.run();
                double[] output = newNetwork.getResult();
    
                // Calculate fitness
                newNetwork.fitness = fitnessCalc.calculateFitness(output, sampleOutput);

                // Compare fitness and keep the best network
                if (newNetwork.fitness > network.fitness) {
                    network = newNetwork;
                }
            }
        }
    }
}

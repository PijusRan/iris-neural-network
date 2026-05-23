package training ;

import data.IrisSpecies;
import java.util.ArrayList;
import model.Network;

/*
    1. Get sample input and expected output
    2. Find the fitness of the current network
    3. Generate random weights and biases for a new network
    4. Run the new network and find its fitness
    5. If the new network has better fitness, replace the current network with the new
*/

public class evolutionCycle {
    public static Network Run(ArrayList<IrisSpecies> sampleData, int loops, Network network){
        Network bestNetwork = network.clone();
        
        for (int i = 0; i < loops; i++) {
            // Generate random weights and biases
            double[][] newInputWeights = valueGen.generateRandomValues(network.inputLayer.in, network.inputLayer.out, -0.25, 0.25);
            double[] newInputBiases = valueGen.generateRandomValues(network.inputLayer.out, 1, -0.25, 0.25)[0];
            double[][] newHiddenWeights = valueGen.generateRandomValues(network.hiddenLayer.in, network.hiddenLayer.out, -0.25, 0.25);
            double[] newHiddenBiases = valueGen.generateRandomValues(network.hiddenLayer.out, 1, -0.25, 0.25)[0];

            // Create new network with values
            Network newNetwork = bestNetwork.clone();
            newNetwork.inputLayer.alterWeights(newInputWeights);
            newNetwork.inputLayer.alterBiases(newInputBiases);
            newNetwork.hiddenLayer.alterWeights(newHiddenWeights);
            newNetwork.hiddenLayer.alterBiases(newHiddenBiases);

            // Calculate fitness
            double oldMSE = fitnessTest.getMSE(network, sampleData);
            double newMSE = fitnessTest.getMSE(newNetwork, sampleData);

            // Compare fitness and keep the best network
            if (newMSE < oldMSE) {
                bestNetwork = newNetwork;
            }
        }
        return bestNetwork;
    }
}

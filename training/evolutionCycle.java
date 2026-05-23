package training ;

import data.IrisSpecies;
import java.util.ArrayList;
import model.Network;

public class evolutionCycle {
    public static Network Run(ArrayList<IrisSpecies> sampleData, int loops, Network network){
        Network bestNetwork = network.clone();

        for (int i = 0; i < loops; i++) {
            for (int j = 0; j < sampleData.size(); j++) {
                double[] sampleInput = sampleData.get(j).measurements;
                double[] sampleOutput = sampleData.get(j).speciesValue;

                // Generate random weights and biases
                double[][] newInputWeights = valueGen.generateRandomValues(network.inputLayer.in, network.inputLayer.out, -0.25, 0.25);
                double[] newInputBiases = valueGen.generateRandomValues(network.inputLayer.out, 1, -0.25, 0.25)[0];
                double[][] newHiddenWeights = valueGen.generateRandomValues(network.hiddenLayer.in, network.hiddenLayer.out, -0.25, 0.25);
                double[] newHiddenBiases = valueGen.generateRandomValues(network.hiddenLayer.out, 1, -0.25, 0.25)[0];

                // Create new network with values
                Network newNetwork = bestNetwork.clone();
                newNetwork.inputLayer.setWeights(newInputWeights);
                newNetwork.inputLayer.setBiases(newInputBiases);
                newNetwork.hiddenLayer.setWeights(newHiddenWeights);
                newNetwork.hiddenLayer.setBiases(newHiddenBiases);

                // Run the new network
                newNetwork.inputLayer.setInputValues(sampleInput);
                newNetwork.run();

                // Calculate fitness
                double oldFitness = fitnessTest.testSingleFitness(bestNetwork.getResult(), sampleInput, sampleOutput);
                double newFitness = fitnessTest.testSingleFitness(newNetwork.getResult(), sampleInput, sampleOutput);

                // Compare fitness and keep the best network
                if (newFitness > oldFitness) {
                    bestNetwork = newNetwork;
                }
            }
        }
        return bestNetwork;
    }
}

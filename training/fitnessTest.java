package training ;

import data.IrisSpecies;
import java.util.ArrayList;
import model.Network;

public class fitnessTest {
    public static double testSingleFitness(double[] networkAnswer, double[] input, double[] expected) {
        // Assuming networkAnswer is the output from the network
        double[] output = networkAnswer;

        double fitness = 0;
        for (int i = 0; i < output.length; i++) {
            fitness += Math.pow(output[i] - expected[i], 2);
        }
        return 1 / (fitness + 1);
    }

    public static double testFitness(Network network, ArrayList<IrisSpecies> sampleData) {
        double fitness = 0;
        for (IrisSpecies sample : sampleData) {
            double[] sampleInput = sample.measurements;
            double[] sampleOutput = sample.speciesValue;

            network.setInput(sampleInput);
            network.run();
            double[] output = network.getResult();

            for (int i = 0; i < output.length; i++) {
                fitness += Math.pow(output[i] - sampleOutput[i], 2);
            }
        }
        return 1 / (fitness + 1);
    }
}

package training ;

import data.IrisSpecies;
import java.util.ArrayList;
import model.Network;

public class fitnessCalculator {
    public static double getMSE(Network network, ArrayList<IrisSpecies> sampleData) {
        double error = 0;
        for (IrisSpecies sample : sampleData) {
            double[] sampleInput = sample.measurements;
            double[] expectedOutput = sample.speciesValue;

            network.setInput(sampleInput);
            network.run();
            double[] output = network.getResult();

            for (int i = 0; i < output.length; i++) {
                error += Math.pow(output[i] - expectedOutput[i], 2);
            }
        }
        return error / sampleData.size();
    }

    public static double getAccuracy(Network network, ArrayList<IrisSpecies> sampleData) {
        int correct = 0;
        for (IrisSpecies sample : sampleData) {
            double[] sampleInput = sample.measurements;
            double expectedOutput = sample.speciesValue[0];

            network.setInput(sampleInput);
            network.run();
            double output = network.getResult()[0];

            if(expectedOutput == 1 && output < 1.5) correct++; // Setosa
            else if(expectedOutput == 2 && output >= 1.5 && output < 2.5) correct++; // Versicolor
            else if(expectedOutput == 3 && output >= 2.5) correct++; // Virginica
        }
        return (double) correct / sampleData.size();
    }
}

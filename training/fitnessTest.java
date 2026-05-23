package training ;

import data.IrisSpecies;
import java.util.ArrayList;
import model.Network;

public class fitnessTest {
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
        return error / sampleData.size(); // Lower is better
    }
}

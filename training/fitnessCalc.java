package training ;

public class fitnessCalc {
    // MSE (Mean Squared Error) fitness function
    public static double calculateFitness(double[] output, double[] expected) {
        double fitness = 0;
        for (int i = 0; i < output.length; i++) {
            fitness += Math.pow(output[i] - expected[i], 2);
        }
        return 1 / (fitness + 1);
    }
}

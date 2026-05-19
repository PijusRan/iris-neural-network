package model;

public class Layer {
    public int in, out;
    public Neuron[] neuronsIn;
    public Neuron[] neuronsOut;
    public double[][] weights;

    public Layer(int in, int out) {
        this.in = in;
        this.out = out;
        this.neuronsIn = new Neuron[in];
        this.neuronsOut = new Neuron[out];

        for (int i = 0; i < in; i++) {
            this.neuronsIn[i] = new Neuron();
        }
        for (int i = 0; i < out; i++) {
            this.neuronsOut[i] = new Neuron();
        }
        this.weights = new double[out][in];

        initializeWeights();
    }

    public void initializeWeights() {
        for (int i = 0; i < out; i++) {
            for (int j = 0; j < in; j++) {
                weights[i][j] = Math.random() * 2 - 1; // Random weights between -1 and 1
            }
        }
    }

    public void calculateNeuronValue(int index) {
        double sum = 0;
        for (int j = 0; j < in; j++) {
            sum += neuronsIn[j].value * weights[index][j];
        }
        neuronsOut[index].value = Functions.sigmoidFunc(sum);
    }

    public void forwardPass() {
        for (int i = 0; i < out; i++) {
            calculateNeuronValue(i);
        }
    }

    public void setInputValues(double[] inputValues) {
        for (int i = 0; i < in; i++) {
            neuronsIn[i].value = inputValues[i];
        }
    }
    public Neuron[] getOutputNodes() {
        return neuronsOut.clone();
    }
}

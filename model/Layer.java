package model;

public class Layer {
    public int in, out;
    public Neuron[] neuronsIn;
    public Neuron[] neuronsOut;
    public double[][] weights;
    public double[] biases;

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
        this.biases = new double[out];

        initializeWeights();
        initializeBiases();
    }
    public void initializeWeights() {
        for (int i = 0; i < out; i++) {
            for (int j = 0; j < in; j++) {
                weights[i][j] = Math.random() * 2 - 1; // Random weights between -1 and 1
            }
        }
    }
    public void initializeBiases() {
        for (int i = 0; i < out; i++) {
            biases[i] = Math.random() * 2 - 1; // Random biases between -1 and 1
        }
    }

    // --- GET / SET ---

    public void setWeights(double[][] newWeights) {
        for (int i = 0; i < out; i++) {
            for (int j = 0; j < in; j++) {
                weights[i][j] = newWeights[i][j];
            }
        }
    }
    public void setBiases(double[] newBiases) {
        for (int i = 0; i < out; i++) {
            biases[i] = newBiases[i];
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

    // --- METHODS ---
    public void calculateSigmoidNeuronValue(int index) {
        double sum = 0;
        for (int j = 0; j < in; j++) {
            sum += neuronsIn[j].value * weights[index][j];
        }
        neuronsOut[index].value = Functions.sigmoidFunc(sum + biases[index]);
    }

    public void forwardSigmoidPass() {
        for (int i = 0; i < out; i++) {
            calculateSigmoidNeuronValue(i);
        }
    }

    public void calculateLinearNeuronValue(int index) {
        double sum = 0;
        for (int j = 0; j < in; j++) {
            sum += neuronsIn[j].value * weights[index][j];
        }
        neuronsOut[index].value = Functions.linearFunc(sum + biases[index]);
    }

    public void forwardLinearPass() {
        for (int i = 0; i < out; i++) {
            calculateLinearNeuronValue(i);
        }
    }

    public Layer clone() {
        Layer cloned = new Layer(this.in, this.out);
        for (int i = 0; i < out; i++) {
            for (int j = 0; j < in; j++) {
                cloned.weights[i][j] = this.weights[i][j];
            }
            cloned.biases[i] = this.biases[i];
        }
        return cloned;
    }
}

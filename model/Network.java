package model;

public class Network {
    public Layer inputLayer;
    public Layer hiddenLayer;
    public double fitness;

    // --- CONSTRUCTORS ---
    public Network() {
        inputLayer = new Layer(4, 6);
        hiddenLayer = new Layer(6, 1);
    }
    public Network(double[] inputValues) {
        inputLayer = new Layer(4, 6);
        hiddenLayer = new Layer(6, 1);
        setInput(inputValues);
    }

    public void setInput(double[] inputValues) {
        inputLayer.setInputValues(inputValues);
    }

    public void run() {
        if(inputLayer.neuronsIn == null) {
            throw new IllegalStateException("Input values must be set before running the network.");
        }

        inputLayer.forwardPass();
        hiddenLayer.neuronsIn = inputLayer.getOutputNodes();
        hiddenLayer.forwardPass();
    }

    public double[] getResult() {
        double[] result = new double[hiddenLayer.out];
        for (int i = 0; i < hiddenLayer.out; i++) {
            result[i] = hiddenLayer.neuronsOut[i].value;
        }
        return result;
    }

    public Network clone() {
        Network clonedNetwork = new Network();

        for (int i = 0; i < inputLayer.in; i++) {
            clonedNetwork.inputLayer.neuronsIn[i].value = this.inputLayer.neuronsIn[i].value;
        }
        for (int i = 0; i < inputLayer.out; i++) {
            clonedNetwork.inputLayer.neuronsOut[i].value = this.inputLayer.neuronsOut[i].value;
        }

        return clonedNetwork;
    }
}

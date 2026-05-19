package model;

public class Network {
    public Layer inputLayer;
    public Layer hiddenLayer;

    public Network() {
        inputLayer = new Layer(4, 6);
        hiddenLayer = new Layer(6, 1);
    }

    public void run() {
        inputLayer.setInputValues(new double[]{5.1, 3.5, 1.4, 0.2});
        inputLayer.forwardPass();
        hiddenLayer.neuronsIn = inputLayer.getOutputNodes();
        hiddenLayer.forwardPass();
    }

    public double getResult() {
        return hiddenLayer.neuronsOut[0].value;
    }
}

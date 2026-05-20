package training ;

public class valueGen {
    public static double[][] generateRandomValues(int l1, int l2, double min, double max) {
        double[][] values = new double[l2][l1];
        for (int i = 0; i < l2; i++) {
            for (int j = 0; j < l1; j++) {
                values[i][j] = min + (max - min) * Math.random();
            }
        }
        return values;
    }
}

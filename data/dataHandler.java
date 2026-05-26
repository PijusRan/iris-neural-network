package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class dataHandler {
    public static double[] globalMin = new double[4];
    public static double[] globalMax = new double[4];
    
    public static ArrayList<IrisSpecies> readData(String filePath) {
        ArrayList<IrisSpecies> irisData = new ArrayList<>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                IrisSpecies iris = new IrisSpecies(
                    Integer.parseInt(values[0]),
                    Double.parseDouble(values[1]),
                    Double.parseDouble(values[2]),
                    Double.parseDouble(values[3]),
                    Double.parseDouble(values[4]),
                    values[5]
                );

                irisData.add(iris);
            }

            br.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return irisData;
    }

    public static void normalizeData(ArrayList<IrisSpecies> data) {
        double[] min = {100.0, 100.0, 100.0, 100.0};
        double[] max = {-100.0, -100.0, -100.0, -100.0};

        for (IrisSpecies s : data)
            for (int i = 0; i < 4; i++) {
                if (s.measurements[i] < min[i]) min[i] = s.measurements[i];
                if (s.measurements[i] > max[i]) max[i] = s.measurements[i];
            }

        globalMin = min;
        globalMax = max;

        for (IrisSpecies s : data)
            for (int i = 0; i < 4; i++)
                s.measurements[i] = (s.measurements[i] - min[i]) / (max[i] - min[i]);
    }

    public static void normalizeData(double[] measurements, ArrayList<IrisSpecies> data) {
        for (int i = 0; i < 4; i++)
            measurements[i] = (measurements[i] - globalMin[i]) / (globalMax[i] - globalMin[i]);
    }
}

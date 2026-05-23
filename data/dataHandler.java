package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class dataHandler {
    public static void readData(String filePath, ArrayList<IrisSpecies> irisData) {
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

            normalizeData(irisData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void normalizeData(ArrayList<IrisSpecies> data) {
        double[] min = {Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE};
        double[] max = {-Double.MAX_VALUE, -Double.MAX_VALUE, -Double.MAX_VALUE, -Double.MAX_VALUE};
 
        for (IrisSpecies s : data)
            for (int i = 0; i < 4; i++) {
                if (s.measurements[i] < min[i]) min[i] = s.measurements[i];
                if (s.measurements[i] > max[i]) max[i] = s.measurements[i];
            }
 
        for (IrisSpecies s : data)
            for (int i = 0; i < 4; i++)
                s.measurements[i] = (s.measurements[i] - min[i]) / (max[i] - min[i]);
    }

    public static void normalizeData(double[] measurements, ArrayList<IrisSpecies> data) {
        double[] min = {Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE};
        double[] max = {-Double.MAX_VALUE, -Double.MAX_VALUE, -Double.MAX_VALUE, -Double.MAX_VALUE};
 
        for (IrisSpecies s : data)
            for (int i = 0; i < 4; i++) {
                if (s.measurements[i] < min[i]) min[i] = s.measurements[i];
                if (s.measurements[i] > max[i]) max[i] = s.measurements[i];
            }
 
        for (int i = 0; i < 4; i++)
            measurements[i] = (measurements[i] - min[i]) / (max[i] - min[i]);
    }
}

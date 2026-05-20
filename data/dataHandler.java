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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

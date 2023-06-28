package Controller;

import java.io.*;
public class CityGraph {
    Matrix city = new Matrix(1001, 1001);
    public CityGraph() throws IOException {
        File file = new File("F:\\telegram\\oop\\پروژه\\phase 1\\graph.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            String[] line = s.split("\\s+");
            if (line.length == 3) {
                this.city.m[Integer.parseInt(line[0])][Integer.parseInt(line[1])] = Integer.parseInt(line[2]);
                this.city.m[Integer.parseInt(line[1])][Integer.parseInt(line[0])] = Integer.parseInt(line[2]);
            }
        }
    }
}
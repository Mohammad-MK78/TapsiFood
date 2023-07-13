package com.example.Final.Controller;

import java.io.*;
public class CityGraph {
    Matrix city = new Matrix(1001, 1001);
    public CityGraph() throws IOException {
        File file = new File("\\F:\\java\\project\\Final\\src\\main\\resources\\graph.txt");
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
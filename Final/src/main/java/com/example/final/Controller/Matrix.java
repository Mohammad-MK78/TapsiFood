package com.example.Final.Controller;

public class Matrix {
    int rows;
    int cols;
    int[][] m;
    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.m = new int[rows][cols];
    }
}
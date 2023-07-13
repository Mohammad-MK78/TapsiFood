package com.example.Final.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class ShortestPath {
    public int[][] graph;
    public int numNodes;
    public ShortestPath(int[][] graph) {
        this.graph = graph;
        this.numNodes = graph.length;
    }
    public int shortestPath(int start, int dest) {
        boolean[] visited = new boolean[numNodes];
        int[] distance = new int[numNodes];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start+1] = 0;
        for (int i = 0; i < numNodes - 1; i++) {
            int mindist = findMinDistance(distance, visited);
            visited[mindist] = true;
            for (int j = 0; j < numNodes; j++) {
                if (!visited[j] && graph[mindist][j] != 0 && distance[mindist] != Integer.MAX_VALUE && distance[mindist] + graph[mindist][j] < distance[j]) {
                    distance[j] = distance[mindist] + graph[mindist][j];
                }
            }
        }
        return distance[dest];
    }
    public int findMinDistance(int[] distance, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < distance.length; i++) {
            if (!visited[i] && distance[i] <= min) {
                min = distance[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
    public ArrayList<Integer> findShortestPath(int start, int dest) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[numNodes];
        // Create an array to keep track of the weight to reach each node from the start node
        int[] cost = new int[numNodes];
        Arrays.fill(cost, Integer.MAX_VALUE);
        // Create an array to keep track of the path to reach each node from the start node
        ArrayList<Integer>[] path = new ArrayList[numNodes];
        for (int i = 0; i < numNodes; i++) {
            path[i] = new ArrayList<>();
        }
        // Add the start node to the queue with a weight of 0
        queue.offer(new Node(start+1, 0, new ArrayList<>(List.of(start+1))));
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            // If we have reached the destination node, return the path
            if (curr.num == dest+1) {
                return curr.path;
            }
            if (!visited[curr.num]) {
                visited[curr.num] = true;
                cost[curr.num] = curr.weight;
                path[curr.num] = curr.path;
                for (int i = 0; i < numNodes; i++) {
                    if (graph[curr.num][i] > 0 && !visited[i]) {
                        ArrayList<Integer> newPath = new ArrayList<>(curr.path);
                        newPath.add(i);
                        queue.offer(new Node(i, curr.weight + graph[curr.num][i], newPath));
                    }
                }
            }
        }
        return null;
    }
    static class Node implements Comparable<Node> {
        int num;
        int weight;
        ArrayList<Integer> path;
        public Node(int num, int weight, ArrayList<Integer> path) {
            this.num = num;
            this.weight = weight;
            this.path = path;
        }
        @Override
        public int compareTo(Node other) {
            return Integer.compare(weight, other.weight);
        }
    }
}
package com.example.projektkosze;

import java.util.LinkedList;
import java.util.Stack;

public class TSP {
    private int[][] graph;
    private int N1LenGraph;
    private Stack<Integer> stack = new Stack<>();
    private LinkedList<Integer> nodesIdx = new LinkedList<>();

    public TSP(final int arrayLen, String[] inputArray) {
        graph = new int[arrayLen][arrayLen];
        N1LenGraph = arrayLen;

        for (int i = 0; i < arrayLen; i++) {
            for (int j = 0; j < arrayLen; j++) {
                graph[i][j] = Integer.parseInt(inputArray[j + i * arrayLen]);
            }
        }
    }

    public void solveTSP() {
        N1LenGraph = graph[1].length - 1;
        int[] visited = new int[N1LenGraph + 1];
        visited[1] = 1;
        stack.push(0);
        int element, dst = 0, i;
        int min = Integer.MAX_VALUE;
        boolean minFlag = false;
        nodesIdx.add(1);

        while (!stack.isEmpty()) {
            element = stack.peek();
            i = 0;
            min = Integer.MAX_VALUE;
            while (i <= N1LenGraph) {
                if (graph[element][i] > 1 && visited[i] == 0) {
                    if (min > graph[element][i]) {
                        min = graph[element][i];
                        dst = i;
                        minFlag = true;
                    }
                }
                i++;
            }
            if (minFlag) {
                visited[dst] = 1;
                stack.push(dst);
                nodesIdx.add(dst);
                minFlag = false;
                continue;
            }
            stack.pop();
        }
    }

    public LinkedList<Integer> getNodesIdx() {
        return nodesIdx;
    }
}
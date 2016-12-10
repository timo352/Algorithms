package graph;

import java.util.*;

public class Dijkstra {

  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);
    int numNodes = in.nextInt();

    int[] dist = new int[numNodes];
    int[] par = new int[numNodes];
    boolean[] found = new boolean[numNodes];

    int[][] adj = new int[numNodes][numNodes];

    // initialize the arrays
    Arrays.fill(par, -1);
    Arrays.fill(dist, Integer.MAX_VALUE);
    for(int j=0; j<numNodes; j++){
      Arrays.fill(adj[j], Integer.MAX_VALUE);
    }

    // read in the adjacency matrix
    for (int i = 0; i < numNodes; i++) {
      for (int j = 0; j< numNodes; j++) {
        adj[i][j] = in.nextInt();
      } 
    }

    // prim's
    dist[0] = 0;
    for (int i = 0; i < numNodes; i++) {
      int k = getMin(dist, found);
      found[k] = true;
      for (int j = 0; j < numNodes; j++) {
        if (adj[k][j] < 999 && dist[j] > dist[k] + adj[k][j]) {
          dist[j] = dist[k] + adj[k][j];
          par[j] = k;
        }
      }
    }

    for (int i = 0; i < numNodes; i++) {
      System.out.println(((char) ('A' + i)) + " d:" + dist[i] + " p:" + ((par[i] != -1)?((char)(par[i] + 'A')) : '-'));
    }
  }

  static int getMin(int[] d, boolean[] f){
    int c = Integer.MAX_VALUE;
    int ind = -1;
    for(int i=0; i<d.length; i++){
      if(!f[i] && d[i] < c){
        c = d[i];
        ind = i;
      }
    }
    if(ind == -1) throw new RuntimeException("Can't remove from minimum. Every vertex is found.");
    return ind;
  }
}

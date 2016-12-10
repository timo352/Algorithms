package graph;

import java.util.*;

public class Prim {

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

		dist[0] = 0;

		// read in the adjacency matrix
		for (int j = 0; j < numNodes; j++) {
			for (int i = j + 1; i < numNodes; i++) {
				adj[j][i] = in.nextInt();
				adj[i][j] = adj[j][i];
			}
		}

		// prim's
		for (int i = 0; i < numNodes; i++) {
			int k = getMin(dist, found);
			for (int j = 0; j < numNodes; j++) {
				if (!found[j] && adj[k][j] < dist[j]) {
					dist[j] = adj[k][j];
					par[j] = k;
				}
			}			
			found[k] = true;
		}
		
		// calculate the cost and print out the tree
		int d = 0;
		for (int a : dist) {
			d += a;
		}
		System.out.println("\nMinimum spanning distance: " + d);

		for (int i = 0; i < numNodes; i++) {
			System.out.println(((char) ('A' + i)) + " d:" + dist[i] + " p:" + ((par[i] != -1)?((char)(par[i] + 'A')) : "NIL"));
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

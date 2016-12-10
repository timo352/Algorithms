package dynamicprogramming;

public class OptimalBST {

    static double[] p;
    static double[] q;
    static double[][] e;
    static int[][] root;
    static double[][] w;

    public static void Optimal_BST(double[] p, double[] q, int n) {
	e = new double[n + 1][n + 1];
	w = new double[n + 1][n + 1];
	root = new int[n][n];

	for (int i = 1; i <= n + 1; i++) {
	    e[i - 1][i - 1] = q[i - 1];
	    w[i - 1][i - 1] = q[i - 1];
	}
	for (int l = 1; l <= n; l++) {
	    for (int i = 1; i <= n - l + 1; i++) {
		int j = i + l - 1;
		e[i - 1][j] = Double.MAX_VALUE;
		w[i - 1][j] = w[i - 1][j - 1] + p[j] + q[j];
		for (int r = i; r <= j; r++) {
		    double t = e[i - 1][r - 1] + e[r + 1 - 1][j] + w[i - 1][j];
		    if (t < e[i - 1][j]) {
			e[i - 1][j] = t;
			root[i - 1][j - 1] = r;
		    }
		}
	    }
	}

    }

    public static void main(String[] args) {
	p = new double[]{0, 0.15, 0.10, 0.05, 0.10, 0.20};
	q = new double[]{0.05, 0.10, 0.05, 0.05, 0.05, 0.10};

	Optimal_BST(p, q, q.length - 1);

	System.out.println("\n\tE TABLE:");
	printTable(e);

	System.out.println("\n\tW TABLE:");
	printTable(w);

	System.out.println("\n\tROOT TABLE:");
	printTable(root);
    }

    public static void printTable(double[][] a) {
	for (int j = 0; j < a[0].length; j++) {
	    System.out.format("\t--%d--", j);
	}
	System.out.println();
	
	for (int i = 0; i < a.length; i++) {
	    for (int j = -1; j < a[i].length; j++) {
		if (j == -1) {
		    System.out.format("%5d|", i+1);
		}
		else if (j >= i) {
		    System.out.format("\t%3.2f", a[i][j]);
		} else {
		    System.out.format("\t");
		}
	    }
	    System.out.println();
	}
    }

    public static void printTable(int[][] a) {
	for (int j = 0; j < a[0].length; j++) {
	    System.out.format("\t--%d--", j+1);
	}
	System.out.println();

	for (int i = 0; i < a.length; i++) {
	    for (int j = -1; j < a[i].length; j++) {
		if (j == -1) {
		    System.out.format("%5d|", i+1);
		} else if (j >= i) {
		    System.out.format("\t%3d", a[i][j]);
		} else {
		    System.out.format("\t");
		}
	    }
	    System.out.println();
	}
    }

}

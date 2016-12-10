package dynamicprogramming;

public class MatrixMult {

	// declared globally to allow the functions to all use them
	public static int[][] m;
	public static int[][] s;
	
	// function to generate and print out the optimal matrix multiplication
	static void getOptimalMult(int[] P){
		
		System.out.println("OPTIMIZING MATRIX MULTIPLICATION...");
		
		// initialize m and s;
		m = new int[P.length+1][P.length+1];
		s = new int[P.length+1][P.length+1];
		
		for(int i=0; i<=P.length; i++){
			for(int j=0; j<=P.length; j++){
				m[i][j] = -1;
				s[i][j] = -1;
			}
		}
		
		// get the solution to the m[1,P.length-1]
		int solution = optimalMatrixMult(P, 1, P.length-1);
		
		// print out all the info
		printM();		
		printS();
		System.out.println("\nOPTIMAL MULTIPLICATION NUMBER:\n\t" + solution);
		System.out.println("\nOPTIMAL PARENTHESIZATION:\n\t" + recursivePrintBest(1, P.length-1));		
	}
	
	// function to print out the parenthesized optimal solution
	private static String recursivePrintBest(int i, int j) {
		// print out the array index by itself
		if (i == j) {
			return "A[" + i + "]";
		} else {
			// print out the entire array recursively
			return "(" + recursivePrintBest(i, s[i][j]) + recursivePrintBest(s[i][j] + 1, j) + ")";
		}
	}
	
	static int optimalMatrixMult(int[] P, int i, int j){
		// if we have already generated this one return it
		if(m[i][j] != -1){
			return m[i][j];
		}
		
		// if it is only one matrix, return 0 and assign values
		if(i == j){
			m[i][j] = 0;
			s[i][j] = 0;
			return 0;
		}	
		
		// loop through all k values and return the minimum
		int min = Integer.MAX_VALUE;
		int sVal = -1;
		
		// m[i,j] = min(m[i,k] + m[k+1,j] + p_i-1*p_k+p_j for k=[i,j-1])
		for(int k=i; k<j; k++){
			int temp = optimalMatrixMult(P,i,k) + optimalMatrixMult(P,k+1,j) + P[i-1]*P[k]*P[j];
			
			if(temp < min){
				sVal = k;
				min = temp;
			}
		}
		s[i][j] = sVal;
		m[i][j] = min;
		
		return min;
	}	
	
	// prints out the 2-D m array
	private static void printM(){
		
		System.out.println("\nMATRIX OF COSTS:");
		for(int i=1; i<m.length-1; i++){
			System.out.format("\t%4d",i);
		}
		System.out.println();
		for(int i=1; i<m.length-1; i++){
			System.out.format("\t-------",i);
		}
		
		System.out.println();
		for(int i=1; i<m.length-1; i++){
			for(int j=0; j<m.length-1; j++){
				if(j != 0){
					if (m[i][j] == -1) System.out.format("\t   -   ");
					else System.out.format("\t%6d", m[i][j]);
				}
				else System.out.format("%3d |", i);
			}
			System.out.println();
		}
	}
	
	// prints out the 2-D s array
	private static void printS(){
		
		System.out.println("\nMATRIX OF S VALUES:");
		for(int i=1; i<s.length-1; i++){
			System.out.format("\t%3d",i);
		}
		System.out.println();
		for(int i=1; i<s.length-1; i++){
			System.out.format("\t-----",i);
		}
		
		System.out.println();
		for(int i=1; i<s.length-1; i++){
			for(int j=0; j<s.length-1; j++){
				if(j != 0){
					if (s[i][j] == -1) System.out.format("\t  -   ");
					else System.out.format("\t%3d", s[i][j]);
				}
				else System.out.format("%3d |", i);
			}
			System.out.println();
		}
	}
	
		
	public static void main(String[] args) {
		int[] P = {5,10,2,6,14,3,30,10,2,7};
		
		getOptimalMult(P);
	}
}

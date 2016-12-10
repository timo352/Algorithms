package linearsorting;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Timothy
 */
public class Hoare {

	private static int Partition(int[] A, int p, int r) {

		int x = A[p];
		System.out.println(x);
		int i = p - 1;
		int j = r + 1;
		int count = 0;
		
		System.out.println("x = " + x + "; i = " + (i+1) + "; j = " + (j+1));
		
		while (true) {
			do {
				j--;
			} while (A[j] > x);
			do {
				i++;
			} while (A[i] < x);

			if (i < j) {
				int temp = A[i];
				A[i] = A[j];
				A[j] = temp;

				System.out.println("Loop #" + ++count);
				System.out.println("x = " + x + "\ni = " + (i+1) + "\nj = " + (j+1));
				LinearSorting.PrintArray(A);

			} else {

				System.out.println("Loop #" + ++count);
				System.out.println("x = " + x + "\ni = " + (i+1) + "\nj = " + (j+1));
				LinearSorting.PrintArray(A);

				return j;
			}
		}
	}

	public static void main(String[] args) {
		int[] A = {13, 19, 9, 5, 12, 8, 7, 4, 11, 2, 6, 21};
		System.out.println(Partition(A, 0, A.length - 1));
	}

}

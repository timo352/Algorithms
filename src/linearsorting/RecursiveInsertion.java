/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linearsorting;

import java.util.Random;

/**
 *
 * @author Timothy
 */
public class RecursiveInsertion {

	private static void Reg_InsertionSort(int[] A) {
		for (int i = 1; i < A.length; i++) {
			int j = i;
			while (j > 0 && A[j - 1] > A[j]) {
				int temp = A[j - 1];
				A[j - 1] = A[j];
				A[j] = temp;
				j--;
			}
		}
	}
	
	public static int count = 0;
	
	private static void Rec_InsertionSort(int[] A, int p){
		count++;
		if(p == 0){
			return;
		}		
		
		Rec_InsertionSort(A, p-1);
		
		int j = p;
		while(j > 0 && A[j-1] > A[j]){
			int temp = A[j-1];
			A[j-1] = A[j];
			A[j] = temp;
			j--;
		}
	}

	public static void main(String[] args) {
		Random r = new Random();
		int k = 10;
		int[] A = new int[k];
		
		for(int i=0; i<k; i++){
			A[i] = r.nextInt(k);
		}
		Rec_InsertionSort(A,A.length-1);
		System.out.println(count);
		
	}

}

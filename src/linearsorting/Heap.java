package linearsorting;

import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Timothy
 */
public class Heap {

		// bubble down for the HeapSort
	private static void Heapify(int[] A, int i, int size) {
		// Heapify
		int L = 2*i+1;
		int R = 2*i + 2;
		int largest;

		if (L < size && A[L] < A[i]) {
			largest = L;
		} else {
			largest = i;
		}

		if (R < size && A[R] < A[largest]) {
			largest = R;
		}

		if (largest != i) {
			int temp = A[i];
			A[i] = A[largest];
			A[largest] = temp;
			Heapify(A, largest, size);
		}
	}
	
		// n*lgn sort
	public static void HeapSort(int[] A) {
		// BuildHeap
		int heap_size = A.length;
		for (int i = A.length / 2; i >= 0; i--) {
			Heapify(A, i, heap_size);
		}
		
		/*for(int i=A.length-1; i>=0; i--){
			int temp = A[i];
			A[i] = A[0];
			A[0] = temp;
			heap_size--;
			Heapify(A,0,heap_size);
		}*/
	}

	public static void main(String[] args) {
		int[] A = {6,7,9,8,10,4,5,1,2,3};
		
		HeapSort(A);
		LinearSorting.PrintArray(A);
	}

}

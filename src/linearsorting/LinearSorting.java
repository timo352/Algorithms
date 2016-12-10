package linearsorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class LinearSorting {

	// n*n sort
	public static int[] InsertionSort(int[] A) {

		for (int i = 1; i < A.length; i++) {
			int j = i;
			while (j > 0 && A[j - 1] > A[j]) {
				int temp = A[j - 1];
				A[j - 1] = A[j];
				A[j] = temp;
				j--;
			}
		}
		return A;
	}
	
	public static int[] QuickSort(int[] A, int p, int r){
		if(p < r){
			int q = Partition(A, p, r);
			A = QuickSort(A, p, q-1);
			A = QuickSort(A, q+1, r);			
		}		
		return A;
	}
	
	private static int Partition(int[] A, int p, int r){
		
		int x = A[r];
		int i = p-1;
		for(int j=p; j<r; j++){
			if(A[j] >= x){
				i++;
				int temp = A[j];
				A[j] = A[i];
				A[i] = temp;
			}
		}
		int temp2 = A[r];
		A[r] = A[i+1];
		A[i+1] = temp2;
		
		return i+1;
	}
	
	// n*lgn sort
	public static int[] HeapSort(int[] A) {
		// BuildHeap
		int heap_size = A.length;
		for (int i = A.length / 2; i >= 0; i--) {
			A = Heapify(A, i, heap_size);
		}
		
		for(int i=A.length-1; i>=0; i--){
			int temp = A[i];
			A[i] = A[0];
			A[0] = temp;
			heap_size--;
			A = Heapify(A,0,heap_size);
		}
		
		return A;
	}

	// n sort
	public static int[] CountingSort(int[] A, int k, int place) {

		// declare new arrays
		int[] B = new int[A.length]; // stores sorted array
		int[] count = new int[k + 1]; // counts the number of each k

		// initialize counts
		for (int i = 0; i <= k; i++) {
			count[i] = 0;
		}

		// count each occurrence of C[i]
		for (int j = 0; j < A.length; j++) {
			count[GetDigit(A[j], place)]++;
		}

		// compute the running sum
		for (int i = 1; i <= k; i++) {
			count[i] += count[i - 1];
		}

		for (int j = A.length - 1; j >= 0; j--) {
			B[count[GetDigit(A[j], place)] - 1] = A[j]; // place the elements
			count[GetDigit(A[j], place)]--;
		}

		return B;
	}

	// n sort
	public static int[] RadixSort(int[] A, int d) {

		for (int i = 1; i <= d; i++) {
			A = CountingSort(A, 10, i);
		}
		return A;
	}

	// n sort - more overhead
	public static int[] BucketSort(int[] A) {

		// assign the biggest the smallest values
		int maxVal = A[0], minVal = A[0];
		for (int i : A) {
			if (i < minVal) {
				minVal = i;
			}
			if (i > maxVal) {
				maxVal = i;
			}
		}
		double interval = ((double) (maxVal - minVal + 1)) / A.length;

		ArrayList<Integer> B[] = new ArrayList[A.length];

		for (int i = 0; i < A.length; i++) {
			B[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < A.length; i++) {
			B[(int) ((A[i] - minVal) / interval)].add(A[i]);
		}

		for (int i = 0; i < A.length; i++) {

			int temp1[] = new int[B[i].size()];
			for (int j = 0; j < B[i].size(); j++) {
				temp1[j] = B[i].get(j);
			}

			temp1 = InsertionSort(temp1);

			Integer temp2[] = new Integer[temp1.length];
			for (int j = 0; j < B[i].size(); j++) {
				temp2[j] = new Integer(temp1[j]);
			}

			B[i] = new ArrayList(Arrays.asList(temp2));
		}

		int[] C = new int[A.length];
		int count = 0;
		for (int i = 0; i < B.length; i++) {
			for (int j = 0; j < B[i].size(); j++) {
				C[count] = B[i].get(j);
				count++;
			}
		}

		return C;
	}

	// Used to get a single digit of an integer for CountingSort/RadixSort
	private static int GetDigit(int i, int place) {
		if (place == 0) {
			return i;
		} else {
			place = (int) Math.pow(10, place - 1);

			return ((i / place) % 10);
		}
	}

	// bubble down for the HeapSort
	private static int[] Heapify(int[] A, int i, int size) {
		// Heapify
		int L = 2*i;
		int R = 2*i + 1;
		int largest;

		if (L < size && A[L] > A[i]) {
			largest = L;
		} else {
			largest = i;
		}

		if (R < size && A[R] > A[largest]) {
			largest = R;
		}

		if (largest != i) {
			int temp = A[i];
			A[i] = A[largest];
			A[largest] = temp;
			A = Heapify(A, largest, size);
		}
		
		return A;
	}
	
	// we can put this in another 
	public static void main(String[] args) {

		int[] kVals = {10};//, 500, 1000, 5000, 10000, 50000, 100000, 500000, 1000000, 5000000, 10000000, 100000000};
		long start, end;

		for (int j = 0; j < kVals.length; j++) {
			int k = kVals[j];
			System.out.println("Sorting " + k + " items.");

			int[] A = new int[k], C = new int[k], B, D;
			Random r = new Random(System.currentTimeMillis());

			for(int i=0; i<k; i++){
				A[i] = r.nextInt(k)+1;
			}
			
			start = System.currentTimeMillis();
			B = QuickSort(A, 0, A.length-1);
			end = System.currentTimeMillis();
			PrintArray(B);
// ********************************************************************************************
/*			// COUNTING SORT
			for (int i = 0; i < A.length; i++) {
				A[i] = r.nextInt(k) + 1;
			}

			start = System.currentTimeMillis();
			B = CountingSort(A, k, 0);
			end = System.currentTimeMillis();

			System.out.println("COUNTING SORT: " + (end - start) + " milliseconds.");
// ********************************************************************************************			
			// RADIX SORT
			for (int i = 0; i < A.length; i++) {
				A[i] = r.nextInt(10000000) + 1;
			}
			int d = String.valueOf(10000000).length(); // get the maximum number of characters

			start = System.currentTimeMillis();
			B = RadixSort(A, d);
			end = System.currentTimeMillis();

			System.out.println("RADIX SORT: " + (end - start) + " milliseconds.");
// ********************************************************************************************
			// BUCKET SORT
			for (int i = 0; i < A.length; i++) {
				A[i] = r.nextInt(10000000) + 1;
			}
			start = System.currentTimeMillis();
			B = BucketSort(A);
			end = System.currentTimeMillis();

			System.out.println("BUCKET SORT: " + (end - start) + " milliseconds.");
// ********************************************************************************************
			// HEAP SORT
			for (int i = 0; i < A.length; i++) {
				A[i] = r.nextInt(10000000) + 1;
			}
			start = System.currentTimeMillis();
			B = HeapSort(A);
			end = System.currentTimeMillis();

			System.out.println("HEAP SORT: " + (end - start) + " milliseconds.");
// ********************************************************************************************
			// INSERTION SORT
			for (int i = 0; i < A.length; i++) {
				A[i] = r.nextInt(10000000) + 1;
			}
			start = System.currentTimeMillis();
			B = InsertionSort(A);
			end = System.currentTimeMillis();

			System.out.println("INSERTION SORT: " + (end - start) + " milliseconds.");
			
			System.out.println();
		}*/		
		}
	}
	
	// used for printing the array to make sure it is sorted
	public static void PrintArray(int[] A) {
		for (int i=0; i<A.length && i<500; i++) {
			System.out.print(A[i] + " ");
		}
		System.out.println();
	}
}

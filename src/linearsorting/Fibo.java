/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linearsorting;

/**
 *
 * @author Timothy
 */
public class Fibo {
	
	private static long count = 0;
	
	public static long fibo(long n){
		
		count++;
		
		if(n == 1 || n == 2){
			return 1;
		} else{
			return fibo(n-1) + fibo(n-2);
		}
	}
	
	public static void main(String[] args){
		
		for(int i=1; i<100; i++){
			count = 0;
			fibo(i);
			System.out.println(count);
		}
	}
		
}

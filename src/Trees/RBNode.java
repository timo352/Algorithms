package Trees;

public class RBNode<T extends Comparable<? super T>> implements Comparable{
	
	public static final int RED = 1;
	public static final int BLACK = 0;
		
	T key;
	RBNode p, left, right;
	int color;
	
	public RBNode(Object val){
		key = (T) val;
		color = RED;
	}
	
	public RBNode(Object val, int c){
		key = (T) val;
		color = c;
	}
	
	public int compareTo(Object o) {
		return key.compareTo((T)((RBNode)o).key);
	}
	
	public String toString(){
		return key.toString();
	}
}

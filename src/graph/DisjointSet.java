package graph;

public class DisjointSet<T extends Comparable> {
	
	int rank;
	DisjointSet parent;
	T key;

	public DisjointSet(T k){
		parent = this;
		rank = 0;
		key = k;
	}
	
	public static void Union(DisjointSet x, DisjointSet y){
		Link(Find_Set(x), Find_Set(y));
	}
	
	public static void Link(DisjointSet x, DisjointSet y){
		if(x.rank > y.rank){
			y.parent = x;
		} else{
			x.parent = y;
			if(x.rank == y.rank){
				y.rank++;
			}
		}
	}
	
	public static DisjointSet Find_Set(DisjointSet x){
		if(x != x.parent){
			x.parent = Find_Set(x.parent);
		}
		return x.parent;
	}
	
	public static void main(String[] args){
		
		DisjointSet<String> s1 = new DisjointSet("YOYOYO");
		DisjointSet<String> s2 = new DisjointSet("HELLO");
		
		DisjointSet.Union(s1, s2);
		
		int x =1;
	}
}

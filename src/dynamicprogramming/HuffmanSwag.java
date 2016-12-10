package dynamicprogramming;

import java.util.*;
import java.io.*;
import java.math.*;

public class HuffmanSwag {

    private static class HuffNode {

	int count = 0;
	char c = '\0';

	HuffNode parent = null;
	HuffNode leftChild = null;
	HuffNode rightChild = null;

	String code = "";
	
	private HuffNode(char b, int a) {
	    count = a;
	    c = b;
	}

	private HuffNode(int a) {
	    count = a;
	}

	private HuffNode() {
	}
    }

    private static class HuffComparator implements Comparator {

	public int compare(Object o1, Object o2) {
	    return Integer.compare(((HuffNode) o1).count, ((HuffNode) o2).count);
	}

    }
    
    public static PriorityQueue<HuffNode> createFrequency(String str) {
	Map<Character, Integer> freqs = new HashMap();

	char[] s = str.toCharArray();
	for (char c : s) {
	    if (freqs.containsKey(c)) {
		freqs.put(c, freqs.get(c) + 1);
	    } else {
		freqs.put(c, 1);
	    }
	}

	PriorityQueue<HuffNode> heapFreq = new PriorityQueue(freqs.size(), new HuffComparator());

	for (Map.Entry<Character, Integer> e : freqs.entrySet()) {
	    HuffNode h = new HuffNode(e.getKey(), e.getValue());
	    heapFreq.add(h);
	}
	return heapFreq;
    }

    public static HuffNode createHuffTree(PriorityQueue<HuffNode> f) {

	int n = f.size();

	for (int i = 1; i < n; i++) {
	    HuffNode h = new HuffNode();
	    
	    h.leftChild = f.poll();
	    h.leftChild.parent = h;
	    
	    h.rightChild = f.poll();
	    h.rightChild.parent = h;
	    
	    h.count = h.leftChild.count + h.rightChild.count;
	    f.add(h);
	}

	return f.poll();
    }
    
    public static HashMap<Character, String> createCharMappings(HuffNode h){
	HashMap<Character, String> map = new HashMap();
	Stack<HuffNode> nodes = new Stack();
	
	nodes.push(h);
	
	while(!nodes.isEmpty()){
	    HuffNode n = nodes.pop();
	    
	    if(n.leftChild != null){
		nodes.push(n.leftChild);
		n.leftChild.code = n.code + "0";
	    }
	    if(n.rightChild != null){
		nodes.push(n.rightChild);
		n.rightChild.code = n.code + "1";
	    }
	    
	    if(n.c != '\0'){
		map.put(n.c, n.code);
	    }
	}
	
	return map;
    }
    
    public static String encodeString(String str, HashMap<Character, String> mapping){
	String output = "";
	char c[] = str.toCharArray();
	for(char cc : c){
	    output += mapping.get(cc);
	}
	return output;
    }
    
    public static String decodeString(String str, HashMap<Character, String> mapping){
	Map<String, Character> remap = new HashMap();
	for (Map.Entry<Character, String> entry : mapping.entrySet()) {
	    remap.put(entry.getValue(), entry.getKey());
	}
	
	String output = "";
	int base = 0;
	for(int i=0; i<str.length(); i++){
	    if(remap.containsKey(str.substring(base,i+1))){
		output += remap.get(str.substring(base,i+1));
		base = i+1;
	    }
	}
	return output;
    }

    public static void main(String[] args) {
	String str = "Huffman invented a greedy algorithm that constructs an optimal prefix code called a Huffman code.  In line with our observations in Section 16.2, its proof of correcness relies on the greedy-choice property and optimal substructure.  Rather than demonstrating that these properties hold and then developing pseudocode, we present the pseudocode first.  Doing so will help clarify how the algorithm makes greedy choices.";
	
	PriorityQueue<HuffNode> f = createFrequency(str);
	HuffNode root = createHuffTree(f);
	HashMap<Character, String> mapping = createCharMappings(root);
	
	for(Map.Entry<Character, String> e : mapping.entrySet()){
	    System.out.println(e.getKey() + " : " + e.getValue());
	}
	
	String compressed = encodeString(str, mapping);
	String output = decodeString(compressed, mapping);
	//System.out.println(compressed);
	//System.out.println(output);
	
    }

}


import java.util.*;

public class PermutationTreeSet {
	
	public static void main(String[] args) {
		String testedString = "eogkaisdid";
		List<String> test = singlePermutations(testedString);
		//lucasweispolesello = 31824 n
		System.out.println("\nLIST:: ");
		for(String s : test) {
			System.out.println(s);
		}
		System.out.println(test.size()+" possible combinations of "+testedString);
	}
	
    public static List<String> singlePermutations(String s) {
    	
        // aabb should be aabb, abab, abba, baab, baba, bbaa
    	//implement tree
    	
    	TreeSet<String> tree = new TreeSet<>();	

		List<String> lastAddElements = new ArrayList<>();
		
		int prevSize = tree.size()+1; //to start while-loop
		int currSize = tree.size();
		
    	while(currSize != prevSize) {
    		prevSize = tree.size();

        	List<String> elements = new ArrayList<>();
        	
    		if(currSize == 0) { lastAddElements.add(s); tree.add(s);}//if at the start, add first element;

    		
    		String root = tree.first();
    		
    		for(String child : tree.tailSet(root,true)) {
    			permute(child, root).forEach((pLeaf) -> {
    				if(! tree.contains(pLeaf)) {
    					elements.add(pLeaf);
    				}
    			});
    		}
    		
    		
    		lastAddElements = new ArrayList<>(elements);
    		tree.addAll(lastAddElements);
    		currSize = tree.size();
    	}
    	
        return new ArrayList<String>(tree);
    }
    
    public static List<String> permute(String s, String p){ // create a list of different permutation of a word.
    	
    	
		List<String> tempList = new ArrayList<>();
		
		for(int i=0; i<s.length()-1; i++) {
		   	StringBuilder currWord = new StringBuilder(s);			
			char curr = currWord.charAt(i);
			char next = currWord.charAt(i+1);
			currWord.setCharAt(i, next);
			currWord.setCharAt(i+1, curr);
			if(! currWord.toString().equals(p)) tempList.add(currWord.toString());
		}
		
		return tempList;
    }
}

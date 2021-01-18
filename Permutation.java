
import java.util.*;

public class Permutation {
	
	public static void main(String[] args) {
		
		List<String> test = singlePermutations("lucas");
		for(String s : test) {
			System.out.println(s);
		}
	}
	
    public static List<String> singlePermutations(String s) {
    	
        // aabb should be aabb, abab, abba, baab, baba, bbaa
    	//implement tree
    	
    	HashSet<String> uniqueList = new HashSet<>();
		List<List<String>> tree = new ArrayList<>();

    	int i=0;
    	int possibilities = s.length()*(s.length()-1); // get n of possible combinations.
    	
    	while(i < possibilities) {
    		System.out.println(i);
    		if(i == 0) tree.add(permute(s)); //if at the start, add first element;
    		
    		List<List<String>> remaining = new ArrayList<>();
    		
    		for(List<String> child : tree) {
    			for(String p : child) {
    				if(! uniqueList.contains(p)) {
    					uniqueList.add(p);
    					remaining.add(permute(p));
    				}
    			}
    		}
    		
    		remaining.forEach((rem) ->{
    			tree.add(rem);
    		});
    		i+=1;
    	}
    	
        return new ArrayList<String>(uniqueList);
    }

    public static List<String> permute(String s){ // create a list of possible permutation of a word.
    	
    	
		List<String> tempList = new ArrayList<>();
		
		for(int i=0; i<s.length()-1; i++) {
		   	StringBuilder currWord = new StringBuilder(s);			
			char curr = currWord.charAt(i);
			char next = currWord.charAt(i+1);
			currWord.setCharAt(i, next);
			currWord.setCharAt(i+1, curr);
			tempList.add(currWord.toString());
		}
		
		return tempList;
    }
}

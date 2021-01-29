import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.Test;

public class ObservedPin {
	
    public static List<String> getPINs(String observed) {
    	Set<String> uniqueResult = new HashSet<>();
    	uniqueResult.add(observed);
    	double numberOfPossibilities = 0;
    	if(observed.length() != 1) {
    		numberOfPossibilities = Math.pow(possibleNumbers(observed).size(), 2);
    	} else {
    		numberOfPossibilities = possibleNumbers(observed).size();
    	}
    	
    	for(int i=0; i<observed.length(); i++) {
			List<String> temporaryResult = new ArrayList<>(uniqueResult);
    		for(int k=0; k<temporaryResult.size(); k++) {
    			String curr = temporaryResult.get(k);
    			List<Integer> possibleDigits = new ArrayList<>(possibleNumbers(String.valueOf(curr.charAt(i))));
    			for(int digit : possibleDigits) {
    				StringBuilder sb = new StringBuilder(curr);
    				sb.setCharAt(i, String.valueOf(digit).charAt(0));
    				uniqueResult.add(sb.toString());
    			}
    		}
    	}
    	
        return new ArrayList<>(uniqueResult);
     } // getPINs
	
    public static Set<Integer> possibleNumbers(String pin){
    	
    	Set<Integer> numbers = new HashSet<>();
    	char[] AllPinDigits = pin.toCharArray();
    	
    	for(char cDigits : AllPinDigits) {
    		int digits = Integer.parseInt(String.valueOf(cDigits));
    		numbers.add(digits);
    		
    		if(digits == 0) {
    			numbers.add(0);
    			numbers.add(8);
    		} else {
    			if(digits == 8) numbers.add(0);
    			if(digits%3 == 1) { // 1 = Esquerda H+1
    				int hDigits = digits+1;
    				if(digits < 4) { //V+3 h+1
    					int vDigits = digits+3;
    					numbers.add(hDigits);
    					numbers.add(vDigits);
    				} else if (digits > 4){ //V-3 h+1
    					int vDigits = digits-3;
    					numbers.add(hDigits);
    					numbers.add(vDigits);
    				} else { //digits == 4 v 3/3 h+1
    					numbers.add(digits+3);
    					numbers.add(digits-3);
    					numbers.add(hDigits);
    				}
    			} else if (digits%3 == 0) { // 0 == Direita H-1
    				int hDigits = digits-1;
    				if(digits < 6) { // v+3 h-1
    					int vDigits = digits+3;
    					numbers.add(vDigits);
    					numbers.add(hDigits);
    				} else if(digits > 6){ // v-3 h-1
    					int vDigits = digits-3;
    					numbers.add(vDigits);
    					numbers.add(hDigits);
    				} else {
    					numbers.add(digits+3);
    					numbers.add(digits-3);
    					numbers.add(hDigits);
    				}
    			} else { //2 = central.
    				if(digits > 5) { //se maior que 5 V-3 H 1/1
    					int vDigits = digits-3;
    					int hDigits = digits+1;
    					numbers.add(vDigits);
    					numbers.add(hDigits);
    					numbers.add(hDigits-2);
    				} else if (digits < 5) { // se menor v+3  h1/1
    					int vDigits = digits+3;
    					int hDigits = digits+1;
    					numbers.add(vDigits);
    					numbers.add(hDigits);
    					numbers.add(hDigits-2);
    				} else { // se for 5, v 3/3 h 1/1
    					numbers.add(digits+3);
    					numbers.add(digits-3);
    					numbers.add(digits+1);
    					numbers.add(digits-1);
    				}
    			}
    		}
    	}
    	
    	return numbers;
    }
    
  //Se N=8, retorna 8.
  //Se mod%3 for 1 ou 0 então é um extremo,
  //x = mod%
  //se x=1 então esquerda, se x=0 então direita.
  //
  //Direita = -1;
  //Esquerda = +1;
//  		
  //Se n < 4 então Vertical +3
  //Se n > 6 então Vertical -3
  //
  //
  //Se mod%3 = 2 então é central
  //se n > 5 então
//  	V: -3
//  	H: -1/+1
  //	
  //se n < 5 então
//  	V: +3
//  	H -1/+1
    
    public static HashMap<String, String[]> expectations = new HashMap<String, String[]>() {
        {
            put("8", new String[]{"5", "7", "8", "9", "0"});
            put("11", new String[]{"11", "21", "41", "12", "22", "42", "14", "24", "44"});
            put("369", new String[]{"236", "238", "239", "256", "258", "259", "266", "268", "269", "296", "298", "299", "336", "338", "339", "356", "358", "359", "366", "368", "369", "396", "398", "399", "636", "638", "639", "656", "658", "659", "666", "668", "669", "696", "698", "699"});
         }
    };
    private final static Comparator<String> comp = (s1, s2) -> s1.compareTo(s2);

    @Test
    public void testPins() {
        for (String entered : expectations.keySet()) {
            test(entered, Arrays.asList(expectations.get(entered)), ObservedPin.getPINs(entered));
        }
    } // testPins

    private void test(String entered, List<String> expected, List<String> result) {
        result.sort(comp);
        expected.sort(comp);
        assertEquals("For observed PIN " + entered, expected, result);
    }
    // MELHOR SOLUÇÃO = simples. mapear K,V entre digito e possíveis adjacentes.
//    private static final Map<Character,String> ADJACENTS = new HashMap<Character,String>() {{
//        put('1', "124");
//        put('2', "2135");
//        put('3', "326");
//        put('4', "4157");
//        put('5', "54268");
//        put('6', "6953");
//        put('7', "748");
//        put('8', "87590");
//        put('9', "986");
//        put('0', "08");
//    }};
//
//    public static List<String> getPINs(String observed) {
//        
//        List<String> ans = Arrays.asList("");
//        
//        for (char c: observed.toCharArray()) {
//            
//            List<String> tmp = new ArrayList<String>();
//            for (char cc: ADJACENTS.get(c).toCharArray()) {
//                for (String s: ans) tmp.add(s+cc);
//            }
//            ans = tmp;
//        }
//        return ans;
//    }
}
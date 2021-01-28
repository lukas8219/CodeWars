import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class Abbreviator {

	  public String abbreviate(String string) {
		  
		  	StringBuilder sb = new StringBuilder(string);
		    String[] s = string.split("[^a-zA-Z]+");
		    
		    for(String word : s) {
		    	System.out.println(word);
		    	int wordIdx = sb.indexOf(word);
		    	sb.delete(wordIdx, wordIdx+word.length());
		    	sb.insert(wordIdx, modify(word));
		    }
		    
		    return sb.toString();
	  }
	  
	  public String modify(String s) {
		  
		  if(s.length() <= 4) return s;
		  
		  StringBuilder sb = new StringBuilder();
		  sb.append(s.charAt(0));
		  sb.append(s.length()-2);
		  sb.append(s.charAt(s.length()-1));
		  
		  return sb.toString();
	  }
	  
		@Test
		public void testInternationalization() {
			Abbreviator abbr = new Abbreviator();
			assertEquals("i18n", abbr.abbreviate("internationalization"));
			assertEquals("e6t-r3s are r4y fun!", abbr.abbreviate("elephant-rides are really fun!"));
			assertEquals("A11y", abbr.abbreviate("ACCESSIBILITy"));
		}
}

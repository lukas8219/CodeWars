import java.util.Arrays;

public class NextLower
{
  public static long nextSmaller(long n)
  {
	//loop from end to beginning  
	// get the lowest number thats not 0.  
	//shift it -1 index
	// if lower than N, return it
	//else if i=0; return -1;
	  
	  char[] numbers = Long.toString(n).toCharArray();
	  int lowestIdx = 0;
	  
	  for(int i=numbers.length-1; i>0; i--) {
		  char currNum = numbers[i];
		  if(currNum != '0') {
			  lowestIdx = i;
			  char ofLowestIdx = numbers[lowestIdx];
			  
			  for(int k=lowestIdx; k>0; k--) {
				  
				  char toChange = numbers[k-1]; //2
				  
				  numbers[k-1] = ofLowestIdx; // set the anterior to 1.
				  numbers[k] = toChange; // set the current to change.
				  long result = Long.parseLong(String.valueOf(numbers));
				  if(result < n) return result;
			  }
			  
			  numbers = Long.toString(n).toCharArray(); //Reset.
		  }
	  }

	  return -1;
  }
}
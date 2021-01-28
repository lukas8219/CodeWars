import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class AreSame {
	
	public static boolean comp(int[] a, int[] b) {
		
		if(a == null || b == null) return false;
		double sumB = Arrays.stream(b)
				.mapToDouble(i -> Math.round(Math.sqrt(i)))
				.sum();
		double sumA = Arrays.stream(a)
				.sum();
		
	    return sumA == sumB;
	  }
}

// BEST SOLUTION - @g964
//public static boolean comp(int[] a, int[] b) {
//    if ((a == null) || (b == null)){
//          return false;
//    }
//    int[] aa = Arrays.stream(a).map(n -> n * n).toArray();
//    Arrays.sort(aa);
//    Arrays.sort(b);
//    return (Arrays.equals(aa, b));
//    
//  }
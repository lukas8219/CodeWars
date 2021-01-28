import java.util.List;
import java.util.ArrayList;

public class Josephus {
  public static <T> List<T> josephusPermutation(final List<T> items, final int k) {
  
    List<T> result = new ArrayList<>();
    int i = 1;
    
    while(items.size() > 1 ) {
      //code
      int size = items.size();
      i = (i%size + (k-1))%size;
      
      if(i == 0){
        result.add(items.remove(size-1));
        i = 1;
      } else {
         result.add(items.remove(i-1));
      } 
    }
    
    if(items.size() != 0){
        result.add(items.remove(0));
    }
    
    return result;
    
  }
}
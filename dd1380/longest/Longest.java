package longest;

import java.util.ArrayList;
import java.util.HashSet;

public class Longest {
	
    private static int find_longest(ArrayList<Long> numbers){

        int list_length = numbers.size();
        if (list_length == 0) return 0;
        
        HashSet<Long> holder = new HashSet<Long>();
        int left = 0;
        int right = 0;
        long current;
        int highest_length = 0;

        while (right < list_length) {
            current = numbers.get(right);

            while (holder.contains(current)) {
                holder.remove(numbers.get(left));
                left ++;
            }

            holder.add(current);
            highest_length = Math.max(highest_length, right-left+1);
            right ++;
        }

        return highest_length;
    }
    
    public static void main(String[] args) {

        // ArrayList<Long> test = new ArrayList<>(Arrays.asList(1L,2L,3L,4L,5L,6L,2L,1L,3L,4L,5L,6L,7L,8L,9L));
        // System.out.println(find_longest(test));

        Kattio io = new Kattio(System.in, System.out);
        ArrayList<Long> numbers = new ArrayList<>();

        while (io.hasMoreTokens()) {
            numbers.add(io.getLong());
        }

        System.out.println(find_longest(numbers));
    }
}
import java.util.ArrayList;
import java.util.Arrays;

import morris.Kattio;

public class Multiadd {
    private static long compare_multi_add(ArrayList<Long> numbers) {
        int size = numbers.size();
        if (size == 0) return 0L;

        long multi_first = 0L;
        long add_first = 1L * numbers.get(0);

        for (int i = 0 ; i < size - 1; i++) {
            if (i % 2 == 0) {
                multi_first += 1L * (numbers.get(i) * numbers.get(i + 1));
            } else {
                add_first += 1L * (numbers.get(i) * numbers.get(i + 1));
            }
        }

        if (size % 2 == 0) {
            add_first += 1L * numbers.get(size - 1);
        } else {
            multi_first += 1L * numbers.get(size - 1);
        }

        return Math.max(multi_first, add_first);
    }

    public static void main(String[] args) {
        test_suite();

        Kattio io = new Kattio(System.in, System.out);
        ArrayList<Long> numbers = new ArrayList<>();

        while (io.hasMoreTokens()) {
            numbers.add(io.getLong());
        }

        System.out.println(compare_multi_add(numbers));
    }

    private static void test_suite() {
        ArrayList<Long> test_array_1 = new ArrayList<>(Arrays.asList(1L, 2L, 0L, 4L));
        System.out.println("Test 1: 5 - " + compare_multi_add(test_array_1));
        ArrayList<Long> test_array_2 = new ArrayList<>(Arrays.asList(1L, 2L, 3L, 4L, 5L));
        System.out.println("Test 2: 27 - " + compare_multi_add(test_array_2));

        ArrayList<Long> test_array_3 = new ArrayList<>(Arrays.asList(2L, 0L, 4L, 4L));
        System.out.println("Test 1: 16 - " + compare_multi_add(test_array_3));
        ArrayList<Long> test_array_4 = new ArrayList<>(Arrays.asList(4L, 2L, 0L, 0L, 6L));
        System.out.println("Test 2: 14 - " + compare_multi_add(test_array_4));

        ArrayList<Long> test_array_5 = new ArrayList<>(Arrays.asList(7L));
        System.out.println("Test 2: 7 - " + compare_multi_add(test_array_5));
    }
}

// 1 2 3 4 5
// 

// 1+2*3+4*5
// 1*2+3*4+5

// 1           2*3           4*5
// 0   1*2            3*4 
// 
// 
// 

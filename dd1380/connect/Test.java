package connect;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void suite() {

        // Test case 1 - single board
        ArrayList<String> layout_1 = new ArrayList<>(Arrays.asList("o"));
        System.out.println("Test 1: o - " + Connect.calculate_game(1, 1, layout_1));

        // Test case 2 - 2x2
        ArrayList<String> layout_2 = new ArrayList<>(Arrays.asList("xo", "xo"));
        System.out.println("Test 2: x - " + Connect.calculate_game(2, 2, layout_2));

        // Test case 3 - 2x2
        ArrayList<String> layout_3 = new ArrayList<>(Arrays.asList("ox", "ox"));
        System.out.println("Test 3: x - " + Connect.calculate_game(2, 2, layout_3));

        // Test case 4 - 5x6
        ArrayList<String> layout_4 = new ArrayList<>(Arrays.asList("oooooo",
                                                                        "xxxxxx",
                                                                        "oooooo",
                                                                        "xxxxxx",
                                                                        "oooxxx"
        ));
        System.out.println("Test 4: o - " + Connect.calculate_game(5, 6, layout_4));

        // Test case 5 - 5x6
        ArrayList<String> layout_5 = new ArrayList<>(Arrays.asList("xxxxxo",
                                                                        "ooooxo",
                                                                        "oxxxxo",
                                                                        "ooxooo",
                                                                        "ooxxxx"
        ));
        System.out.println("Test 5: x - " + Connect.calculate_game(5, 6, layout_5));

    }
}

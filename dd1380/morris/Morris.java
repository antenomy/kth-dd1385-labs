package morris;

import java.util.ArrayList;

public class Morris {  

    private static String find_next(String previous) {
        if (previous.isEmpty()) return "";

        StringBuilder return_item = new StringBuilder();

        char[] digit_array = previous.toCharArray();
        ArrayList<Character> digit_list = new ArrayList<>();
        for (char c : digit_array) {
            digit_list.add(c);
        }

        char save_digit = digit_array[0];
        int save_digit_count = 0;

        for (char digit : digit_list) {
            if (digit != save_digit) {
                return_item.append(String.valueOf(save_digit_count));
                return_item.append(String.valueOf(save_digit));
                save_digit = digit;
                save_digit_count = 1;
            } else {
                save_digit_count++;
            }
        }

        // Append the last digit type
        return_item.append(String.valueOf(save_digit_count));
        return_item.append(String.valueOf(save_digit));

        return return_item.toString();
    }
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int loop_count = io.getInt();
        String current_iteration = "1";

        for (int i = 1; i < loop_count; i++) {
            // System.out.println(current_iteration);
            current_iteration = find_next(current_iteration);
        }

        System.out.println(current_iteration);
    }
}

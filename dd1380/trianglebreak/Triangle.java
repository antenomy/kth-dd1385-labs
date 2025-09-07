import morris.Kattio;

public class Triangle {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int upper_input_length_limit = 100000000;
        
        // System.out.println("Enter your string: ");
        String S = io.getWord();
        
        int input_length = S.length();

        if (input_length < upper_input_length_limit) {
            int index = 0;
            int adder = 1;
            while (index < input_length) {
                int end = Math.min(index + adder, input_length);
                System.out.println(S.substring(idx, end));
                index = end;
                adder++;
            }
        } else {
            System.out.println("Input Too Long!");
        }
    }
}
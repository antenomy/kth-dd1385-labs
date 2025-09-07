package connect;

import java.util.ArrayList;
import java.util.ArrayDeque;

class Board {
    int x;
    int y;
    boolean[][] layout;
    boolean[][] visited;
    boolean cheating;

    public Board(int x, int y, ArrayList<String> rows) {
        boolean[][] board = new boolean[x][y];
        char[] row_split;
        int count_diff = 0;

        for (int i = 0; i < rows.size(); i++) {
            row_split = rows.get(i).toCharArray();

            for (int j = 0; j < row_split.length; j++) {
                board[i][j] = (row_split[j] == 'o');
                if (board[i][j]) {
                    count_diff ++;
                } else {
                    count_diff --;
                }
            }
        }

        this.x = x;
        this.y = y;
        this.layout = board;
        this.visited = new boolean[x][y];

        if ((x*y) % 2 == 0) {
            this.cheating = (count_diff != 0);
        } else {
            this.cheating = (count_diff != 1 && count_diff != -1);
        }
    }

    private boolean in_bounds(int check_x, int check_y) {
        if (check_x < 0 || check_y < 0 || check_x >= this.x || check_y >= this.y) {
            return false;
        } else {
            return true;
        }
    }


    public boolean general_forward_search(int start_x, int start_y, boolean player_bool) {

        ArrayDeque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{start_x, start_y});
        this.visited[start_x][start_y] = true;

        while (!stack.isEmpty()) {
            int[] pos = stack.pop();
            int center_x = pos[0], center_y = pos[1];

            if (!player_bool && center_x == this.x - 1) return true;
            if (player_bool && center_y == this.y - 1) return true;

            for (int[] dir : Config.DIRECTIONS) {
                int nx = center_x + dir[0], ny = center_y + dir[1];

                if (in_bounds(nx, ny)) {
                    if (!this.visited[nx][ny]) {
                        if (layout[nx][ny] == player_bool) {
                            stack.push(new int[]{nx, ny});
                        }
                        this.visited[nx][ny] = true;
                    }
                }
            }
        }
        return false;
    }
}


// x - number of rows
// y - length of rows
public class Connect {

    private static boolean find_connection(Board board, char player_symbol) {
        
        boolean player_bool = (player_symbol == 'o');

        if (player_bool) {
            for (int i = 0; i < board.x; i++) {
                if (Config.VERBOSE) System.out.println(i + " " + 0);

                if (board.general_forward_search(i, 0, player_bool)) {
                    if (Config.VERBOSE) System.out.println("path found on row " + i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < board.y; i++) {
                if (Config.VERBOSE) System.out.println(0 + " " + i);

                if (board.general_forward_search(0, i, player_bool)) {
                    if (Config.VERBOSE) System.out.println("path found on column " + i);
                    return true;
                }
            }
        }

        return false;
    }


    public static String calculate_game(int x, int y, ArrayList<String> rows) {

        // if (Config.VERBOSE) System.out.println(board.x + " " + board.y);

        Board board = new Board(x, y, rows);

        if (board.cheating) return "fusk";  
        if (board.x == 1 && board.y == 1) {
            if (board.layout[0][0]) {
                return "o";
            } else {
                return "x";
            }
        }

        boolean o_connected = find_connection(board, 'o');
        boolean x_connected;

        if (!o_connected) {
            board.visited = new boolean[x][y];
            x_connected = find_connection(board, 'x');
        } else {
            x_connected = false;
        }
 
        // Return output based on who is connected
        if (o_connected ^ x_connected) {
            if (o_connected) {
                return "o";
            } else if (x_connected) {
                return "x";
            }
        } 

        return "ingen";
    }

    public static void main(String[] args) {

        // if (Config.WITH_TESTS) {
        //     Test.suite();
        // }

        //Input
        if (Config.TAKE_INPUT) {
            Kattio io = new Kattio(System.in, System.out);

            int x = io.getInt();
            int y = io.getInt();

            ArrayList<String> rows = new ArrayList<>();

            while (io.hasMoreTokens()) {
                rows.add(io.getWord());
            }

            // Game Logic
            String result = calculate_game(x, y, rows);
            System.out.println(result);
        }
    }
}

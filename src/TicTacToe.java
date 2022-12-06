import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];

    public static void main(String[] args) {
        String player = "X";
        int row = 0;
        int col = 0;
        Scanner in = new Scanner(System.in);
        boolean done = false;
        boolean playing = true;
        int move = 0;
        final int MOVES_FOR_WIN = 5;
        final int MOVES_FOR_TIE = 7;
        do {
            player = "X";
            playing = true;
            move = 0;
            clearBoard();
            do {

                do {
                    showBoard();
                    System.out.println("Enter move for " + player);
                    row = SafeInput.getRangedInt(in, "Enter row", 0, 2);
                    col = SafeInput.getRangedInt(in, "Enter column", 0, 2);
                } while (!isValidMove(row, col));

                board[row][col] = player;
                move++;

                if (move >= MOVES_FOR_WIN) {
                    if (isWin(player)) {
                        showBoard();
                        System.out.println("Player " + player + " wins!");
                        playing = false;
                    }
                }
                if (move >= MOVES_FOR_TIE) {
                    if (isTie()) {
                        showBoard();
                        System.out.println("It's a Tie!");
                        playing = false;
                    }
                }
                if (player.equals("X")) {
                    player = "O";
                } else {
                    player = "X";
                }
            } while (playing);

            done = SafeInput.getYNConfirm(in, "Play again");
        } while (done);
    }

        private static void clearBoard() {
            for (int row = 0; row < ROW; row++) {
                for (int col = 0; col < COL; col++) {
                    board[row][col] = " ";
                }
            }
        }

        private static void showBoard () {
            for (int row = 0; row < ROW; row++) {
                System.out.print("| ");
                for (int col = 0; col < COL; col++) {
                    System.out.print(board[row][col] + " | ");
                }
                System.out.println();
            }
        }

        private static boolean isValidMove ( int row, int col){
            boolean retVal = false;
            if (board[row][col].equals(" ")) {
                retVal = true;
            } else {
                System.out.println("You can't make that move.");
            }
            return retVal;
        }

        private static boolean isWin (String player){
            if (isColWin(player) || isRowWin(player) || isDiagonalWin(player)) {
                return true;
            }

            return false;
        }

        private static boolean isRowWin (String player){
            for (int row = 0; row < ROW; row++) {
                if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                    return true;
                }
            }
            return false;
        }

        private static boolean isColWin (String player){
            for (int col = 0; col < COL; col++) {
                if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                    return true;
                }
            }
            return false;
        }

        private static boolean isDiagonalWin (String player){
            if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
                return true;
            } else if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
                return true;
            }
            return false;
        }

        private static boolean isTie() {
            boolean xFlag = false;
            boolean oFlag = false;
            for (int row = 0; row < ROW; row++) {
                if (board[row][0].equals("X") || board[row][1].equals("X") || board[row][2].equals("X")) {
                    xFlag = true;
                }
                if (board[row][0].equals("O") || board[row][1].equals("O") || board[row][2].equals("O")) {
                    oFlag = true;
                }
                if (!(xFlag && oFlag)) {
                    return false;
                }
            }
            for (int col = 0; col < COL; col++) {
                if (board[0][col].equals("X") || board[1][col].equals("X") || board[2][col].equals("X")) {
                    xFlag = true;
                }
                if (board[0][col].equals("O") || board[1][col].equals("O") || board[2][col].equals("O")) {
                    oFlag = true;
                }
                if (!(xFlag && oFlag)) {
                    return false;
                }
            }
            xFlag = oFlag = false;

            if (board[0][0].equals("X") || board[1][1].equals("X") || board[2][2].equals("X")) {
                xFlag = true;
            }
            if (board[0][0].equals("O") || board[1][1].equals("O") || board[2][2].equals("O")) {
                oFlag = true;
            }
            if (!(xFlag && oFlag)) {
                return false;
            }
            xFlag = oFlag = false;

            if (board[0][2].equals("X") || board[1][1].equals("X") || board[2][0].equals("X")) {
                xFlag = true;
            }
            if (board[0][2].equals("O") || board[1][1].equals("O") || board[2][0].equals("O")) {
                oFlag = true;
            }
            if (!(xFlag && oFlag)) {
                return false;
            }
            return true;
        }
}

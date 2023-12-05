import java.util.Scanner;

public class TicTacToe {
    // initialising empty board.
    private static char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    // initialising player symbol.
    private static char currentPlayer = 'X';


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        // main game loop start.
        while (true) {
            printBoard();
            // prompts player to pick a pos.
            playMove(scanner);

            // checks if game is over.
            if (checkWinner() || isBoardFull()) {
                printBoard();
                System.out.println("Game over!");
                break;
            }

            // switch player symbol.
            switchPlayer();
        }

        // cleaning up.
        scanner.close();
    }

    private static void printBoard() {
        // prints index of rows.
        System.out.println("  0 1 2");

        // prints index of col.
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");

            // prints board.
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);

                // adds separation between fields.
                if (j < 2) {
                    System.out.print("|");
                }

            }

            // adds padding.
            System.out.println();

            // adds separation between fields.
            if (i < 2) {
                System.out.println("  -----");
            }
        }

        // adds padding.
        System.out.println();
    }

    private static void playMove(Scanner scanner) {
        // initializing
        int row;
        int col;

        while (true) {
            // prompts player to pick a position row an validates.
            row = userInputIntWithValidation("Player " + currentPlayer + ", enter your move (row): ", scanner);
            // prompts player to pick a position col an validates.

            col = userInputIntWithValidation("Player " + currentPlayer + ", enter your move (col): ", scanner);


            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    private static int userInputIntWithValidation(String prompt, Scanner scanner){

        do {
            System.out.print(prompt);
            if (scanner.hasNextInt()){
                return scanner.nextInt();
            }
            System.out.println("You have entered a invalid value, please try again.");
            // waits for a new input.
            scanner.nextLine();


        }while (true);



    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    private static boolean checkWinner() {
        // Check rows, columns, and diagonals for a winner
        for (int i = 0; i < 3; i++) {

            // horizontal win check.
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                System.out.println("Player " + currentPlayer + " wins!");
                return true;
            }
            // vertical win check.
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                System.out.println("Player " + currentPlayer + " wins!");
                return true;
            }
        }

        // cross-check. right -> left
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            System.out.println("Player " + currentPlayer + " wins!");
            return true;
        }
        // cross-check. left -> right
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            System.out.println("Player " + currentPlayer + " wins!");
            return true;
        }

        return false;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        System.out.println("The game is a tie!");
        return true;
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}

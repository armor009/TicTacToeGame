//12 Jan 2023 - Mayank Mundhra - Intervue
// Create a tic-tac-toe game and mimic as player1 and player 2

import java.util.*;

class TicTacToe {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //Initialization
        int[][] matrix = new int[3][3];
        Arrays.fill(matrix[0], 0);
        Arrays.fill(matrix[1], 0);
        Arrays.fill(matrix[2], 0);
        boolean p1Turn = true;
        boolean isGameOver = false;
        int winner = 0;
        int count = 9;

        //Run Game
        while (count != 0 && !isGameOver) {
            //Print Board
            System.out.println("Turns remaining=" + count);
            displayMatrix(matrix);

            //Get input
            if (p1Turn) {
                getInput(1, matrix);
                p1Turn = false;
            } else {
                getInput(2, matrix);
                p1Turn = true;
            }

            //Check winner
            winner = checkWinner(matrix);
            if (winner != 0) {
                isGameOver = true;
            }

            System.out.println();
            count--;
        }

        //Give result
        if (isGameOver) {
            System.out.println("Winner = " + winner);
        } else {
            System.out.println("Tie");
        }
    }

    public static void displayMatrix(int[][] matrix) {
        System.out.println("Tic-Tac-Toe Board");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void getInput(int player, int[][] matrix) {
        System.out.println("Player " + player + "\'s turn:");
        boolean inputAccepted = false;
        while (!inputAccepted) {
            System.out.println("Enter your move:");
            int row = getPositionAndCheckSanity("Row");
            int column = getPositionAndCheckSanity("Column");

            if (matrix[row][column] == 0) {
                matrix[row][column] = player;
                inputAccepted = true;
            } else {
                System.out.println("Position [" + row + "," + column + "] occupied.");
            }
        }
    }

    private static int getPositionAndCheckSanity(String str) {
        boolean sanity = false;
        int position = -1;

        while (!sanity) {
            System.out.println("Enter " + str + " between 0 and 2, both inclusive:");
            position = sc.nextInt();
            if (position >= 0 && position <= 2) {
                sanity = true;
            } else {
                System.out.println("Invalid " + str);
            }
        }
        return position;
    }

    public static int checkWinner(int[][] matrix) {
        int player;

        //Check rows for winner
        player = checkRows(matrix);
        if (player != 0) {
            return player;
        }

        //Check columns for winner
        player = checkColumns(matrix);
        if (player != 0) {
            return player;
        }

        //Check Diagonal 1 for winner
        player = checkDiagonal1(matrix);
        if (player != 0) {
            return player;
        }

        //Check Diagonal 2 for winner
        player = checkDiagonal2(matrix);
        if (player != 0) {
            return player;
        }

        return 0;
    }

    private static int checkRows(int[][] matrix) {
        //Check rows for winners
        int i, j;
        for (i = 0; i < 3; i++) {
            int player = matrix[i][0];
            for (j = 1; j < 3; j++) {
                if (matrix[i][j] != player) {
                    j = 4;
                }
            }
            if (j == 3 && player != 0) {
                return player;
            }
        }
        return 0;
    }

    private static int checkColumns(int[][] matrix) {
        //Check columns for winners
        int i, j;
        for (i = 0; i < 3; i++) {
            int player = matrix[0][i];
            for (j = 1; j < 3; j++) {
                if (matrix[j][i] != player) {
                    j = 4;
                }
            }
            if (j == 3 && player != 0) {
                return player;
            }
        }
        return 0;
    }

    private static int checkDiagonal1(int[][] matrix) {
        //Diagonal 1
        int i;
        int player = matrix[0][0];
        for (i = 1; i < 3; i++) {
            if (matrix[i][i] != player) {
                i = 4;
            }
        }
        if (i == 3 && player != 0) {
            return player;
        }
        return 0;
    }

    private static int checkDiagonal2(int[][] matrix) {
        //Diagonal 2
        int i;
        int player = matrix[0][2];
        for (i = 1; i < 3; i++) {
            if (matrix[i][2 - i] != player) {
                i = 4;
            }
        }
        if (i == 3 && player != 0) {
            return player;
        }
        return 0;
    }
}
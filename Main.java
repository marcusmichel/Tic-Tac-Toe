package tictactoe;
import java.util.Scanner;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize both win conditions as false
        boolean xWins = false;
        boolean oWins = false;
        // Tracks whose turn it is
        boolean xTurn = true;
        // Assumes a bad input unless proven wrong within the main loop
        boolean goodInput = false;
        // Game is not over until one side wins, or a draw occurs
        boolean finished = false;

        // User input coordinates
        int a;
        int b;

        // The state of the playing field
        char[] play = new char[9];
        Arrays.fill(play, '_');
        char[][] table = setTable(play);
        
        // Displays table at beginning of game
        showTable(table);

        // Main loop
        while (!xWins && !oWins && !finished) {

            goodInput = false;

            System.out.println("Enter the coordinates: ");

            // Handles the case that user input is not numbers
            if (scanner.hasNextInt()) {
                a = scanner.nextInt();
            } else {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
                continue;
            }
            if (scanner.hasNextInt()) {
                b = scanner.nextInt();
            } else {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
                continue;
            }

            // Handles coordinates outside of the playing area and already occupied cells
            if (a > 3 || a < 1 || b > 3 || b < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            } else if (isOccupied(a, b, play)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            } else {
                goodInput = true;
            }

            // If the input satisfies the previous conditions, a move is made 
            if (goodInput) {
                if (xTurn) {
                    xMove(a, b, play);
                } else {
                    oMove(a, b, play);
                }
                xTurn = !xTurn;
                table = setTable(play);
                showTable(table);
            }

            // Creates an input string so I can use the checks to see if a win has occurred
            String input = new String(play);

            // Horizontal win
            if (input.contains("XXX")) {
                if (input.indexOf("XXX") % 3 == 0){
                    xWins = true;
                    break;
                }
            } else if (input.contains("OOO")) {
                if (input.indexOf("OOO") % 3 == 0){
                    oWins = true;
                    break;
                }
            }
            // Vertical win
            for (int i = 0; i < 3; i++) {
                if (input.charAt(i) == input.charAt(i + 3) && input.charAt(i) == input.charAt(i + 6)){
                    if (input.charAt(i) == 'X') {
                        xWins = true;
                        break;
                    }
                    if (input.charAt(i) == 'O') {
                        oWins = true;
                        break;
                    }
                }
            }

            // Top left to bottom right (\) Diagonal win
            if (input.charAt(0) == input.charAt(4) && input.charAt(4) == input.charAt(8)){
                if (input.charAt(0) == 'X') {
                    xWins = true;
                    break;
                } else if (input.charAt(0) == 'O') {
                    oWins = true;
                    break;
                }
            }
            // Top right to bottom left (/) Diagonal win
            if (input.charAt(2) == input.charAt(4) && input.charAt(4) == input.charAt(6)){
                if (input.charAt(2) == 'X') {
                    xWins = true;
                    break;
                } else if (input.charAt(2) == 'O') {
                    oWins = true;
                    break;
                }
            }

            // If there are still empty spaces, the game is not over
            if (input.contains("_")) {
                finished = false;
            } else {
                finished = true;
            }

        }

        if (xWins) {
            System.out.println("X wins");
        } else if (oWins) {
            System.out.println("O wins");
        } else {
            System.out.println("Draw");
        }

    }

    // Method to check whether a cell is occupied
    private static boolean isOccupied(int a, int b, char[] play) {

        char temp = ' ';
        char[] tempPlay = new char[9];
        for (int i = 0; i < 3; i++) {
            tempPlay[i] = play[i + 6];
            tempPlay[i + 6] = play[i];
            tempPlay[i + 3] = play[i + 3];
        }

        int section = (b - 1) * 3;
        if (tempPlay[section + a - 1] == '_') {
            return false;
        }

        return true;
    }

    // Method for x to make a move
    private static char[] xMove(int a, int b, char[] play) {

        char temp = ' ';
        int section = 0;

        switch (b) {
            case 1:
                section = 6;
                break;
            case 2:
                section = 3;
                break;
            case 3:
                section = 0;
                break;
        }

        play[section + a - 1] = 'X';

        return play;
    }
    
    // Method to set the table based off of the 9 character array
    private static char[][] setTable(char[] play) {
        int count = 0;

        char[][] table = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = play[count];
                count++;
            }
        }
        return table;
    }

    // Displays the table
    private static void showTable(char[][] table) {
        System.out.println("---------");
        System.out.println("| " + table[0][0] + " " + table[0][1] + " " + table[0][2] + " |");
        System.out.println("| " + table[1][0] + " " + table[1][1] + " " + table[1][2] + " |");
        System.out.println("| " + table[2][0] + " " + table[2][1] + " " + table[2][2] + " |");
        System.out.println("---------");
    }

    // Method for o to make a move
    private static char[] oMove(int a, int b, char[] play) {

        char temp = ' ';
        int section = 0;

        switch (b) {
            case 1:
                section = 6;
                break;
            case 2:
                section = 3;
                break;
            case 3:
                section = 0;
                break;
        }

        play[section + a - 1] = 'O';

        return play;
    }

    // Method to convert input string into character array
    private static char[] setPlay(String input) {
        char[] play = new char[9];

        for (int i = 0; i < 9; i++) {
            play[i] = input.charAt(i);
        }

        return play;
    }
}

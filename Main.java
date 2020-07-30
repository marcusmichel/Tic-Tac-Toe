package tictactoe;
import java.util.Scanner;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // String input = "";
//        String message = "";
        boolean xWins = false;
        boolean oWins = false;
        boolean xTurn = true;
        boolean goodInput = false;
        boolean finished = false;

        int a;
        int b;

        char[] play = new char[9];
        Arrays.fill(play, '_');
        char[][] table = setTable(play);

        showTable(table);

        while (!xWins && !oWins && !finished) {

            goodInput = false;

            System.out.println("Enter the coordinates: ");

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

            if (a > 3 || a < 1 || b > 3 || b < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            } else if (isOccupied(a, b, play)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            } else {
                goodInput = true;
            }

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

            // \ Diagonal win
            if (input.charAt(0) == input.charAt(4) && input.charAt(4) == input.charAt(8)){
                if (input.charAt(0) == 'X') {
                    xWins = true;
                    break;
                } else if (input.charAt(0) == 'O') {
                    oWins = true;
                    break;
                }
            }
            // / Diagonal win
            if (input.charAt(2) == input.charAt(4) && input.charAt(4) == input.charAt(6)){
                if (input.charAt(2) == 'X') {
                    xWins = true;
                    break;
                } else if (input.charAt(2) == 'O') {
                    oWins = true;
                    break;
                }
            }

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



        /*
        // Horizontal win
        if (input.contains("XXX")) {
            if (input.indexOf("XXX") % 3 == 0){
                xWins = true;
            }
        } else if (input.contains("OOO")) {
            if (input.indexOf("OOO") % 3 == 0){
                oWins = true;
            }
        }
        // Vertical win
        for (int i = 0; i < 3; i++) {
            if (input.charAt(i) == input.charAt(i + 3) && input.charAt(i) == input.charAt(i + 6)){
                if (input.charAt(i) == 'X') {
                    xWins = true;
                }
                if (input.charAt(i) == 'O') {
                    oWins = true;
                }
            }
        }

        // \ Diagonal win
        if (input.charAt(0) == input.charAt(4) && input.charAt(4) == input.charAt(8)){
            if (input.charAt(0) == 'X') {
                xWins = true;
            } else if (input.charAt(0) == 'O') {
                oWins = true;
            }
        }
        // / Diagonal win
        if (input.charAt(2) == input.charAt(4) && input.charAt(4) == input.charAt(6)){
            if (input.charAt(2) == 'X') {
                xWins = true;
            } else if (input.charAt(2) == 'O') {
                oWins = true;
            }
        }
         */

        /* System.out.println("Enter cells: " + input);
        int x = 0;
        int o = 0;

        System.out.println("---------");
        System.out.println("| " + table[0][0] + " " + table[0][1] + " " + table[0][2] + " |");
        System.out.println("| " + table[1][0] + " " + table[1][1] + " " + table[1][2] + " |");
        System.out.println("| " + table[2][0] + " " + table[2][1] + " " + table[2][2] + " |");
        System.out.println("---------");
        System.out.println("Enter the coordinates: ");

        boolean goodInput = false;

        int a = 0;
        int b = 0;

        while (!goodInput) {
            a = scanner.nextInt();
            b = scanner.nextInt();

            if (a > 3 || a < 1 || b > 3 || b < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                System.out.println("Enter the coordinates: ");
            } else if (isOccupied(a, b, play)) {
                System.out.println("This cell is occupied! Choose another one!");
                System.out.println("Enter the coordinates: ");
            } else {
                goodInput = true;
            }

        }

        play = xMove(a, b, play);
        table = setTable(play);

        System.out.println("---------");
        System.out.println("| " + table[0][0] + " " + table[0][1] + " " + table[0][2] + " |");
        System.out.println("| " + table[1][0] + " " + table[1][1] + " " + table[1][2] + " |");
        System.out.println("| " + table[2][0] + " " + table[2][1] + " " + table[2][2] + " |");
        System.out.println("---------"); */






//        // Check if there's more cells to fill
//        if (!xWins && !oWins) {
//            if (input.contains("_")) {
//                message = "Game not finished";
//            } else {
//                message = "Draw";
//            }
//        }
//
//        if (x - 2 >= o || o - 2 >= x) {
//            message = "Impossible";
//        }
//        if (xWins) {
//            message = "X wins";
//        }
//        if (oWins) {
//            message = "O wins";
//        }
//
//        if (xWins && oWins) {
//            message = "Impossible";
//        }
//        System.out.println(message);

    }

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

    private static void showTable(char[][] table) {
        System.out.println("---------");
        System.out.println("| " + table[0][0] + " " + table[0][1] + " " + table[0][2] + " |");
        System.out.println("| " + table[1][0] + " " + table[1][1] + " " + table[1][2] + " |");
        System.out.println("| " + table[2][0] + " " + table[2][1] + " " + table[2][2] + " |");
        System.out.println("---------");
    }

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

    private static char[] setPlay(String input) {
        char[] play = new char[9];

        for (int i = 0; i < 9; i++) {
            play[i] = input.charAt(i);
        }

        return play;
    }
}

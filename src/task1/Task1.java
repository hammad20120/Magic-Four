package task1;

import java.util.Scanner;

public class Task1 {

    public static final String ANSI_YELLOW = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static boolean red = true; //true means red's turn
    public static boolean redWon = false; //indicates if red has won
    public static boolean yellowWon = false; //indicates if yellow has won
    public static int inputs = 0; //counts total number of inputs

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Variable to store the column input by user
        int inp = 0;

        char s[][] = new char[6][7];

        //Initialize array with spaces and print the array using print method
        for (int j = 0; j < s.length; j++) {
            for (int i = 0; i < s[j].length; i++) {
                s[j][i] = ' ';
            }
        }
        print(s);
        //Game continues until red or yellow have won
        while (!redWon && !yellowWon) {

            //validate if the input is correct i.e. between 0 and 6
            do {
                //Prompt whether it is red's turn or yellow's turn
                inputPrompt();
                inp = sc.nextInt();
            } while (inp < 0 || inp > 6);

            //Use method add to place 'R' or 'Y' in the column entered by user
            add(inp, s);

            System.out.println("");
            print(s);

            //Perform check after atleast 6 inputs have been made to see if 
            //someone won the game
            if (inputs > 6) {
                check(s);

                //if total number of inputs reached 42 means that game has drawn
                //so exit the while loop
                if (inputs == 42) {
                    break;
                }
            }
        }

        //Display who has won or if the game is drawn
        if (redWon) {
            System.out.println(ANSI_RED + "RED WON" + ANSI_RESET);
        } else if (yellowWon) {
            System.out.println(ANSI_YELLOW + "BLUE WON" + ANSI_RESET);
        } else {
            System.out.println("DRAW");
        }

    }

    //Method to print the array
    public static void print(char[][] x) {

        System.out.println(" 0 1 2 3 4 5 6 ");
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                if (x[i][j] == 'R') {
                    System.out.print("|" + ANSI_RED + x[i][j] + ANSI_RESET);
                } else {
                    System.out.print("|" + ANSI_YELLOW + x[i][j] + ANSI_RESET);
                }
            }
            System.out.println("|");
        }

    }

    //Method to add 'R' or 'Y' to correct row in column
    public static void add(int inp, char[][] s) {

        //Start checking if the column is empty from last row then move up
        for (int i = s.length - 1; i >= 0; i--) {
            //if the current column is empty then place either 'R' or 'Y' in the
            //column
            if (s[i][inp] == ' ') {
                if (red) {
                    s[i][inp] = 'R';
                    red = false;
                } else {
                    s[i][inp] = 'B';
                    red = true;
                }
                inputs++;
                break;
            }
        }
    }

    //Display whose turn it is
    public static void inputPrompt() {
        if (red) {
            System.out.print(ANSI_RED + "Drop a red disk at column (0-6):" + ANSI_RESET);

        } else {
            System.out.print(ANSI_YELLOW + "Drop a Blue disk at column (0-6):" + ANSI_RESET);

        }
    }

    //Check rows, columns and diagnols to see if there are 4 of same colors
    public static void check(char[][] s) {
        int red, yellow;
        //Check Columns
        for (int i = s.length - 1; i >= 0; i--) {
            for (int j = 0; j < s[i].length - 4; j++) {
                red = 0;
                yellow = 0;
                if (s[i][j] != ' ') {
                    for (int k = j; k < j + 4; k++) {
                        char temp = s[i][k];

                        if (temp == 'R') {
                            red++;
                        }
                        if (temp == 'B') {
                            yellow++;
                        }

                    }
                    
                    if (red == 4) {
                        redWon = true;
                        break;
                    }

                    if (yellow == 4) {
                        yellowWon = true;
                        break;
                    }

                }
            }
            
            if(redWon||yellowWon)
                break;
            
        }

        //Check Rows
        for (int i = s.length - 4; i >= 0; i--) {
            for (int j = 0; j < s[i].length; j++) {
                red = 0;
                yellow = 0;
                for (int k = i; k < i + 4; k++) {
                    char temp = s[k][j];

                    if (temp == 'R') {
                        red++;
                    }
                    if (temp == 'B') {
                        yellow++;
                    }

                    if (red == 4) {
                        redWon = true;
                        break;
                    }

                    if (yellow == 4) {
                        yellowWon = true;
                        break;
                    }

                }

            }
        }

        //Check Diagnols from left to right
        for (int x = 0; x < 4; x++) {

            for (int i = 2; i >= 0; i--) {
                yellow = 0;
                red = 0;
                for (int j = 0; j < 4; j++) {
                    char temp = s[i + j][x + j];

                    if (temp == 'R') {
                        red++;
                    }
                    if (temp == 'B') {
                        yellow++;
                    }

                    if (red == 4) {
                        redWon = true;
                        break;
                    }

                    if (yellow == 4) {
                        yellowWon = true;
                        break;
                    }

                }

            }
        }

        //Check Diagnols from right to left
        for (int x = 0; x < 4; x++) {

            for (int i = 2; i >= 0; i--) {
                yellow = 0;
                red = 0;
                for (int j = 0; j < 4; j++) {
                    char temp = s[i + j][(s[0].length - 1) - (x + j)];

                    if (temp == 'R') {
                        red++;
                    }
                    if (temp == 'B') {
                        yellow++;
                    }

                    if (red == 4) {
                        redWon = true;
                        break;
                    }

                    if (yellow == 4) {
                        yellowWon = true;
                        break;
                    }

                }
            }
        }

    }
}

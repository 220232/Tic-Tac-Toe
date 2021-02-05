//Chloe Tse's Code for Tic Tac Toe

import java.util.Scanner; //import statement

public class TicTacToe {

    public char[][] gameBoard; //2D array

    //constructor
    public TicTacToe() {
        gameBoard = new char[3][3];
        createEmptyGameBoard(); //creates the empty game board
    }

    //creates the empty game board by using for loops for the rows and columns
    public void createEmptyGameBoard() {
        //rows
        for (int rows = 0; rows < 3; rows++) { //three rows
            //columns
            for (int columns = 0; columns < 3; columns++) { //three columns
                gameBoard[rows][columns] = '-'; //resets the values
            }
        }
    }

    //prints the game board by using for loops for the rows and columns
    public void printGameBoard() {
        System.out.println("-------------");
        for (int columns = 0; columns < 3; columns++) { //three columns
            System.out.print("| ");
            for (int rows = 0; rows < 3; rows++) { //three rows
                System.out.print(gameBoard[rows][columns] + " | ");
            }
        System.out.println();
        System.out.println("-------------");
        }
    }

    //plays the game
    public void playerAction(Scanner inputS) {
        System.out.println("Use the coordinates to place the points.");
        printGameBoard(); //prints the empty game board
        for (int moveCount = 1; moveCount <= 9; moveCount++) { //ensures that a maximum of nine moves are made
            //decides the symbol based on the move count (if the move count is odd, the 'X' character is used; if the move count is even, the 'O' character is used)
            char symbol = (moveCount % 2 == 1) ? 'X' : 'O';
            int row = getValidIntegerInputInRange(inputS, "Select your row.", 1, 3); //asks the user to select the row
            int column = getValidIntegerInputInRange(inputS, "Select your column.", 1, 3); //asks the user to select the column
            //subtracts a value of one from each row and column to match the 2D array index
            if (gameBoard[column - 1][row - 1] == '-') {  //ensures that the position is empty
                gameBoard[column- 1][row - 1] = symbol; //replaces the position with the symbol (depending on the move count)
            }
            printGameBoard(); //prints the game board

            //checks for a win, and uses the symbol to determine who wins
            if (win()) {
                if (symbol == 'X') {
                    System.out.println("Player one has won.");
                } else {
                    System.out.println("Player two has one.");
                }
                System.exit(0); //exits the program
            }
        }
        System.out.println("We have a draw.");
    }

    //main method
    public static void main(String[] args) {
        Scanner inputS = new Scanner(System.in); //scanner
        TicTacToe game = new TicTacToe(); //creates a new game
        //asks the user which opponent they want
        String opponent = getValidStringInput(inputS,"Type y to play against a human player, and any other key to play against a computer player.");
        if (opponent.equals("y")) {
            System.out.println("Your opponent will be a human player. ");
            game.playerAction(inputS); //calls upon the player action method
        } else {
            System.out.println("Your opponent will be a computer player. ");
            //Angela's code
        }
    }

    //compares whether the value of the characters are the same, and ensures that the characters are replacing empty squares
    public boolean checkSameCharacter(char valueOne, char valueTwo, char valueThree) {
        return ((valueOne != '-') && (valueOne == valueTwo) && (valueTwo == valueThree));
    }


    //checks whether the characters for each row are the same
    public boolean rowWin() {
        for (int row = 0; row < 3; row++) {
            if (checkSameCharacter(gameBoard[row][0], gameBoard[row][1], gameBoard[row][2])) {
                return true;
            }
        }
        return false;
    }

    //checks whether the characters for each column are the same
    public boolean columnWin(){
        for (int column = 0; column < 3; column++) {
            if (checkSameCharacter(gameBoard[0][column], gameBoard[1][column], gameBoard[2][column])) {
                return true;
            }
        }
        return false;
    }

    //checks whether the characters for each diagonal are the same
    public boolean diagonalWin() {
        return (checkSameCharacter(gameBoard[0][0], gameBoard[1][1], gameBoard[2][2])) || (checkSameCharacter(gameBoard[2][2], gameBoard[1][1], gameBoard[0][0]));
    }

    //checks whether there is the same character in a row, column or diagonal
    public boolean win() {
        return (rowWin() || columnWin() || diagonalWin());
    }

    /* asks a question that requires an integer input and then checks the validity of the input
   if the input is not a valid integer (eg. String, char), the program will not continue
   (hence, the while loop) until a valid integer is inputted
   this prevents the program from continuing with an incorrect input
   */
    private int getValidIntegerInput(Scanner inputS, String question) {
        System.out.print(question + " "); //prints the question the user must respond to
        while (!inputS.hasNextInt()) { //while the input is not a valid integer
            System.err.println("Error: not a valid integer input"); //print the error message of "invalid input"
            inputS.nextLine(); //keep waiting for input
        }
        return Integer.parseInt(inputS.nextLine()); //returns the valid input (may be set to a variable)
    }

    //same as getValidIntegerInput, but includes the minimum and maximum  value
    private int getValidIntegerInputInRange(Scanner inputS, String question, int minValue, int maxValue) {
        while (true) {
            int userInputValue = getValidIntegerInput(inputS, question);
            if (userInputValue < minValue || userInputValue > maxValue) {
                System.err.println("Error: input not within the valid range"); //prints the error message of "invalid input"
            }
            else {
                return userInputValue;
            }
        }
    }

    //asks a question (that requires a String input) and then returns the String input
    private static String getValidStringInput(Scanner inputS, String question) {
        System.out.print(question + " "); //prints the question the user must respond to
        return inputS.nextLine(); //returns the String input
    }
}

import java.util.Scanner; //import statement
import java.util.*;
import java.lang.Thread;


public class TicTacToe {

    public char[][] gameBoard; //2D array
    public static String currentPlayer = "x";
    static int row;
    static int column;

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

//switches the player from x to o and o to x
    public static String CurrentPlayer() {
        if (currentPlayer == "x") {
            currentPlayer = "o";
            } else {
            currentPlayer = "x";
        }
        return currentPlayer;
    }

    // player's turn
    public void playerPlayMove(Scanner inputS) {
        if (checkWin(currentPlayer) == false) { //if the player hasn't lost yet

            int row = getValidIntegerInputInRange(inputS, "Select your row.", 1, 3); //asks the user to select the row
            int column = getValidIntegerInputInRange(inputS, "Select your column.", 1, 3); //asks the user to select the column
            //subtracts a value of one from each row and column to match the 2D array index
            if (gameBoard[column - 1][row - 1] == '-') {  //ensures that the position is empty
                gameBoard[column - 1][row - 1] = 'x'; //replaces the position with the symbol (depending on the move count)
            }


            printGameBoard(); //prints the game board
            CurrentPlayer(); //switch player
            System.out.println("Current player: " + currentPlayer);
        }
        else {
            System.out.println("You lose :(");
        }
    }

    public void computerPlayMove(int column, int row) { //the computer' move
        gameBoard[column][row] = 'o';
        printGameBoard();
        CurrentPlayer();
    }

    public void computerAction(Scanner inputS) {

        Random random = new Random();
        System.out.println("Use the coordinates to place the points.");
        printGameBoard();

        //while board is not full
        while (isBoardFull() == false) {

            if (currentPlayer == "x") { //player's turn

                System.out.println("Current player is " + currentPlayer);
                playerPlayMove(inputS); //player moves

                }


            if (currentPlayer == "o") { //computer's turn

                System.out.println("Current player is " + currentPlayer);

                if (checkWin(currentPlayer) == false) { //if computer hasn't lost yet


                    System.out.println("The computer plays...");
                    wait(1000); //time delay

                    //take middle position
                    if (gameBoard[1][1] == '-') {
                        gameBoard[1][1] = 'o';
                        printGameBoard();
                        CurrentPlayer();
                        System.out.println("Current player: " + currentPlayer);

                    } else {

                            //check for near wins then either blocks the player or play winning move
                            for (int row = 0; row < 3; row++) { //near win for row
                                if (checkSameCharacter2(gameBoard[0][row], gameBoard[1][row])) {
                                    column = 2;
                                    computerPlayMove(column, row);
                                    CurrentPlayer();
                                }

                                if (checkSameCharacter2(gameBoard[1][row],
                                        gameBoard[2][row])) {
                                    column = 0;
                                    computerPlayMove(column, row);
                                    CurrentPlayer();
                                }

                                if (checkSameCharacter2(gameBoard[0][row],
                                        gameBoard[2][row])) {
                                    column = 1;
                                    computerPlayMove(column, row);
                                    CurrentPlayer();

                                }
                            }

                        if (currentPlayer == "o") { //if computer still hasn't played yet

                            System.out.println(currentPlayer);

                            for (int column = 0; column < 3; column++) { //near wins for column
                                if (checkSameCharacter2(gameBoard[column][0], gameBoard[column][1])) {
                                    row = 2;
                                    computerPlayMove(column, row);
                                    CurrentPlayer();
                                }

                                if (checkSameCharacter2(gameBoard[column][1],
                                        gameBoard[column][2])) {
                                    row = 0;
                                    computerPlayMove(column, row);
                                    CurrentPlayer();
                                }

                                if (checkSameCharacter2(gameBoard[column][0],
                                        gameBoard[column][2])) {
                                    row = 1;
                                    computerPlayMove(column, row);
                                    CurrentPlayer();
                                }
                            }
                        }

                        if (currentPlayer == "o") {

                            if (checkSameCharacter2(gameBoard[0][0], gameBoard[1][1])) { //near win for diagonal
                                row = 2;
                                column = 2;
                                computerPlayMove(column, row);
                                CurrentPlayer();

                            }
                            if (checkSameCharacter2(gameBoard[0][0], gameBoard[2][2])) {
                                row = 1;
                                column = 1;
                                computerPlayMove(column, row);
                                CurrentPlayer();
                            }
                            if (checkSameCharacter2(gameBoard[2][2], gameBoard[1][1])) {
                                row = 0;
                                column = 0;
                                computerPlayMove(column, row);
                                CurrentPlayer();
                            }

                        }


                        if (currentPlayer == "o") {

                            System.out.println(currentPlayer);

                            //generate random position
                            int randCol = random.nextInt(2);
                            int randRow = random.nextInt(2);

                            if (gameBoard[randRow][randCol] == '-') { //play random position if not taken

                                column = randCol;
                                row = randRow;
                                gameBoard[column][row] = 'o';
                                printGameBoard();
                                CurrentPlayer();

                            } else { //if position is taken, then take nearest empty position
                                for (int row = 0; row < 2; row++) {
                                    for (int column = 0; column < 2; column++) {
                                        if (gameBoard[column][row] == '-') {
                                            gameBoard[column][row] = 'o';
                                            printGameBoard();
                                            CurrentPlayer();
                                        }
                                    }
                                }
                            }
                        }
                }

                    }

                else { // if checkWin is true
                    System.out.println("You win!");
                    break;
                }

                }

                }

        if (isBoardFull() == true) { //if board is full
            System.out.println("You tied...");
        }

        }

    /**
    public char nearRowWin() {
        //check for near wins then counters
        for (int row = 0; row < 3; row++) {
            if (checkSameCharacter2(gameBoard[0][row], gameBoard[1][row])) {
                column = 2;
            }
            if (checkSameCharacter2(gameBoard[1][row],
                    gameBoard[2][row])) {
                column = 0;
            }

            if (checkSameCharacter2(gameBoard[0][row],
                    gameBoard[2][row])) {
                column = 1;
            }
        }
        return gameBoard[column][row];
    }

     public char nearColumnWin() {
         for (int column = 0; column < 3; column++) {
             if (checkSameCharacter2(gameBoard[column][0], gameBoard[column][1])) {
                 row = 2;
                 gameBoard[column][row] = 'o';
                 printGameBoard();
             }

             if (checkSameCharacter2(gameBoard[column][1],
                     gameBoard[column][2])) {
                 row = 0;
                 gameBoard[column][row] = 'o';
                 printGameBoard();
             }

             if (checkSameCharacter2(gameBoard[column][0],
                     gameBoard[column][2])) {
                 row = 1;
                 gameBoard[column][row] = 'o';
                 printGameBoard();

             }
         }
         return char[][];
     }


     public char nearDiagonalWin() {
     for (int row = 0; row < 3; row++) {
     if (checkSameCharacter2(gameBoard[0][0],
     gameBoard[1][1])) {
     return gameBoard[2][2];
     }
     if (checkSameCharacter2(gameBoard[0][0],
     gameBoard[2][2])) {
     return gameBoard[1][1];
     }
     if (checkSameCharacter2(gameBoard[1][1],
     gameBoard[2][2])) {
     return gameBoard[0][0];
     }
     }
     return char[][];
     }
**/
    public boolean checkWin(String currentPlayer) {

        boolean isThereWinner = false;

        if (currentPlayer == "o") {
            if (rowWin() == true | columnWin() == true | diagonalWin() == true) {
                System.out.println(isThereWinner);
                isThereWinner = true;
            }
            else {
                isThereWinner = false;
            }
        }

        if (currentPlayer == "x") {
            if (rowWin() == true | columnWin() == true | diagonalWin() == true) {
                isThereWinner = true;
            }
            else {
                isThereWinner = false;
            }
        }
        return isThereWinner;
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
            game.computerAction(inputS);
        }
    }



    //compares whether the value of the characters are the same, and ensures that the characters are replacing empty squares
    public boolean checkSameCharacter(char valueOne, char valueTwo, char valueThree) {
        return ((valueOne != '-') && (valueOne == valueTwo) && (valueTwo == valueThree));
    }

    public boolean checkSameCharacter2(char valueOne, char valueTwo) {
        return ((valueOne != '-') && (valueOne == valueTwo));
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

    public boolean isBoardFull() {

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (gameBoard[column][row] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void wait(int ms) //time delay
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
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

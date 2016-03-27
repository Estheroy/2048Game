//------------------------------------------------------------------//
// GameManager.java                                                 //
//                                                                  //
// Game Manager for 2048                                            //
//                                                                  //
// Author:  Brandon Williams                                        //
// Date:    1/17/15                                                 //
//------------------------------------------------------------------//

import java.util.*;
import java.io.*;

/*
 * Name: Xuanpei Ouyang
 * Login: cs8baqa
 * Date: Feb 2, 2015
 * File: GameManager.java
 * Source of Help: Introduction to Java Programming
 *
 * This program can create a new game with a specified size board, random value
 * and the outputFileName which will be save when the game is quit. User can 
 * also create a new game with the information which stored in the previoUs 
 * game. The user can load the old game and continue their old game.
 * And this program can let user play the game by taking users' input command.
 * User can move up, down, left and right to move all the tiles in the board. 
 * If the user type q, then the program will save the current board and quit 
 * the program. When the game begins, there will be two tiles in the board 
 * randomly with value 2 or 4. Then the game will test whether the command
 * user types can actually move the tiles in the board. If yes, the program
 * will move all the tiles in board in that direction and add a new random 
 * tiles with value 2 or 4 in the board, if no, then the program will print the
 * command list and prompt user to type another command until the command is 
 * valid.
 */

/* 
 * Name : GameManager
 * Purpose: This class can create a new game with a specified size board and
 * specified random value and the outputFileName which will be save then the 
 * game is quit. User can also load their old game with the information which 
 * stored in the stored game and save their current game and coutinue it later. 
 * This program can let user to enter the command and move the tiles according 
 * to the command. User can enter w,a,s,d to move all the tiles in the board 
 * to up, left, down and right. If user enter q, the game will quit and save 
 * the current board to the outputFileName file. And the program can check if
 * the user enter the invalid command, if the command is valid, then the
 * program will move the tiles according to the command and then add a new
 * random tile. If the command is not valid, the program will prompt the user   
 * to enter a new command until it is valid.
 */
public class GameManager
{
  // Instance variables
  private Board board;    // The actual 2048 board
  private String outputFileName;  // File to save the board to when exiting

  /** 
   * Constructor to create a game manager to start a new game for game
   * 2048. The constructor will create a new board for the new game by 
   * reading the input parameter boardSize and random which the board 
   * uses to generate random number and set the file to save the board
   * to when exiting.
   * 
   * @param boardSize the size of the board
   * @param outputBoard the name of the output board
   * @param random the Random object the board uses to generate random number
   */
  GameManager(int boardSize, String outputBoard, Random random)
  {
    // create a new board with boardSize and random
    board = new Board(boardSize, random);

    // assign the outputboard to the instance variable outputFileName
    outputFileName = outputBoard;      
  }


  /** 
   * Constructor to create a game manager to load a saved game for game
   * 2048, by using the board size and a Random from the inputBoard 
   * string and set the file to save the board to when exiting.
   *
   * @param boardSize the size of the board
   * @param outputBoard the name of the output board
   * @param random the Random object the board uses to generate random number
   */
  GameManager(String inputBoard, String outputBoard, Random random)
    throws IOException
    {
      // create a new board by using the inputboard information and with 
      board = new Board(inputBoard, random);

      // assign the outputboard to the instance variable outputFileName
      outputFileName = outputBoard;
    }

  /** 
   * The method to play the 2048 game. This method can takes in input from the
   * user to specify moves to execute valid moves are: w - Move up, 
   * s - Move Down, a - Move Left, d - Move Right, q - Quit and Save Board.
   * If an invalid command is received then print the controls to remind 
   * the user of the valid moves. Once the player decides to quit or the game
   * is over, save the game board to a file based on the outputFileName string 
   * that was set in the constructor and then return
   */
  public void play() throws IOException{

    // print command list to user
    printControls();

    // create a scanner object to read users' command
    Scanner input = new Scanner(System.in); 

    // create a direction object and set to null
    Direction direction = null;

    // a boolean shows whether the game is over
    boolean isGameOver = false;

    // print the current game board
    System.out.println(board);

    // make a infinite loop to prompt user to input
    while(true){

      // check if the game is over
      isGameOver = board.isGameOver();

      // if the game is not over, then check the user' s input
      if(!isGameOver){

        // prompt user to enter a command
        System.out.println("Please enter a command.");

        // use a switch statement to execute methods according to user's 
        // input when user enter a command
        switch(input.next()){

          // if user input w, then the method will read w as UP command
          case "w":
            direction = Direction.UP;

            // check if move up is a valid move, if it is valid, then 
            // execute the move method and add a random tile after the move
            if(board.canMove(direction)){
              board.move(direction);
              board.addRandomTile();
              System.out.println(board); 
              break;
            } 

            //if move up is not a valid move, then print message about
            // invalid move and prompt user to enter a new command.
            else {
              System.out.println("Invalid move. Please enter a command");
              printControls();
              System.out.println(board); 
              break;
            }

            // if user input s, then the method will read s as DOWN command  
          case "s":
            direction = Direction.DOWN;

            // check if move up is a valid move, if it is valid, then
            // execute the move method and add a random tile after the move
            if(board.canMove(direction)){
              board.move(direction);
              board.addRandomTile();
              System.out.println(board); 
              break;
            }

            //if move up is not a valid move, then print message about
            // invalid move and prompt user to enter a new command.
            else {
              System.out.println("Invalid move. Please enter a command");
              printControls();
              System.out.println(board); 
              break;
            }

            // if user input a, then the method will read a as LEFT command 
          case "a":
            direction = Direction.LEFT;

            // check if move up is a valid move, if it is valid, then
            // execute the move method and add a random tile after the move
            if(board.canMove(direction)){
              board.move(direction);
              board.addRandomTile();
              System.out.println(board); 
              break;
            }

            //if move up is not a valid move, then print message about
            // invalid move and prompt user to enter a new command.
            else {
              System.out.println("Invalid move. Please enter a command");
              printControls();
              System.out.println(board); 
              break;
            }

            // if user input d, then the method will read d as RIGHT command 
          case "d":
            direction = Direction.RIGHT;

            // check if move up is a valid move, if it is valid, then
            // execute the move method and add a random tile after the move
            if(board.canMove(direction)){
              board.move(direction);
              board.addRandomTile();
              System.out.println(board); 
              break;
            }

            //if move up is not a valid move, then print message about
            // invalid move and prompt user to enter a new command.
            else {
              System.out.println("Invalid move. Please enter a command");
              printControls();
              System.out.println(board); 
              break;
            }

            // if user input q, then the method save the current game board
            // to the outputFileName file and exit the game
          case "q":
            board.saveBoard(outputFileName);
            System.exit(0);

          case "u":
            board.undo();
            System.out.println(board); 
            break;

          case "r":
            board.rotate(true);
            System.out.println(board); 
            break;

            // if user input any thing other than w, a, s, d, q, then the 
            // method will print the list of command and prompt user to 
            // enter a new command. 
          default:
            printControls(); 
            System.out.println(board); 
            break;
        }

      }

      //if game over, then save the current game board to outputFileName
      // file, then print each value of board and exit the switch
      else{board.saveBoard(outputFileName);
        return;
      }
    }
  }

  /** 
   * Method for printing the Controls for the Game
   *
   */
  private void printControls(){

    System.out.println("  Controls:");
    System.out.println("    w - Move Up");
    System.out.println("    s - Move Down");
    System.out.println("    a - Move Left");
    System.out.println("    d - Move Right");
    System.out.println("    q - Quit and Save Board");
    System.out.println("    u - undo the previous move");
    System.out.println("    r - rotate the board clockwise");
    System.out.println();
  }
}

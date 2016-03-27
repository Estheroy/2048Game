import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.util.*;
import java.io.*;
import javafx.scene.effect.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/*  
 * Name : Gui2048
 * Created  by Xuanpei Ouyang
 * Date: Feb 2, 2015
 * File: Gui2048.java
 * Purpose: This program can let user play the 2048 game with user interface 
 * which is resizable.  
 */
public class Gui2048 extends Application
{
  private String outputBoard; // The filename for where to save the Board
  private Board board; // The 2048 Game Board

  // Fill colors for each of the Tile values
  private static final Color COLOR_EMPTY = Color.rgb(238, 228, 218, 0.60);
  private static final Color COLOR_2 = Color.rgb(249,205,173);
  private static final Color COLOR_4 = Color.rgb(252,157,154);
  private static final Color COLOR_8 = Color.rgb(254,67,101);
  private static final Color COLOR_16 = Color.rgb(185,227,217);
  private static final Color COLOR_32 = Color.rgb(250,218,141);
  private static final Color COLOR_64 = Color.rgb(174,221,129);
  private static final Color COLOR_128 = Color.rgb(107,194,53);
  private static final Color COLOR_256 = Color.rgb(35,235,185);
  private static final Color COLOR_512 = Color.rgb(175,215,237);
  private static final Color COLOR_1024 = Color.rgb(147,224,255);
  private static final Color COLOR_2048 = Color.rgb(79,193,233);
  private static final Color COLOR_OTHER = Color.rgb(93,156,23);
  private static final Color COLOR_GAME_OVER = Color.rgb(238, 228, 218, 0.65);
  private static final Color COLOR_TITLE = Color.rgb(51,220,255);

  // For tiles >= 8
  private static final Color COLOR_VALUE_LIGHT = Color.rgb(249, 246, 242);
  // For tiles < 8
  private static final Color COLOR_VALUE_DARK = Color.rgb(119, 110, 101,0.8); 

  // fields for constant number in the board
  private static final int TWO = 2;
  private static final int FOUR = 4;
  private static final int EIGHT = 8;
  private static final int SIXTEEN = 16;
  private static final int THIRTY_TWO = 32;
  private static final int SIXTY_FOUR = 64;
  private static final int ONE_HUNDRED = 128;
  private static final int TWO_HUNDRED = 256;
  private static final int FIVE_HUNDRED = 512;
  private static final int ONE_THOUSAND = 1024;
  private static final int TWO_THOUSANDS = 2048;
  private static final int THREE = 3;
  private static final Color COLOR_BACK = Color.rgb(255, 204, 125);
 
  // fields foe constant number for creating board, retangle and set padding
  private static final int GAP1 = 8;
  private static final int FIVE = 8;
  private static final int SIX = 9;
  private static final int TWENTY = 24;
  private static final int RECT1 = 5;
  private static final int RECT2 = 10;
  private static final int RECT3 = 50;
  private static final int RECT4 = 3;
  private static final int BACK1 = 187;
  private static final int BACK2 = 173;
  private static final int BACK3 = 160;
  private static final int SPACE3 = 20;
  private static final int TEXT1 = 24;
  private static final double INSETS1 = 11.5;
  private static final double INSETS2 = 12.5;
  private static final double INSETS3 = 13.5;
  private static final double INSETS4 = 14.5;
  private static final int SPACE4 = 25;
  private static final int RECT5 = 40;
  private static final int ARC = 6;
  private static final int SPACE5 = 17;
  private static final int ARC2 = 10;
  private static final double REF = 0.9;
  private static final int TOP = 13;

  // fields for Javafx layout manager
  private int[][] grid;
  private GridPane gridPane;
  private BorderPane borderPane;
  private StackPane borderPane2;
  private BorderPane innerPane;
  private BorderPane flowPane;


  // fields for the style of grid and the name of title
  private String style = "-fx-background-color: rgb(255, 204, 125)";
  private String Title = "2048";
  private DoubleProperty fontSizeTitle;
  private DoubleProperty fontSizeScore;
  private DoubleProperty fontSizeR;
  private DoubleProperty fontSizeG;


  @Override
    /**                                                                              
     * Method for running and display the Gui of 2048       
     *                                                                               
     * @param the primary Stage which used to display the Gui  of 2048
     */
    public void start(Stage primaryStage)
    {
      // Process Arguments and Initialize the Game Board
      processArgs(getParameters().getRaw().toArray(new String[0]));

      // create a borderPane as the play board
      borderPane = new BorderPane();
      borderPane2 = new StackPane();

    /*  // check if the game is over, if it is over, then create a rectangle 
      // fill it color and create a game over text, then add them to the 
      // board
      if(board.isGameOver()){
        Rectangle gameOver = new Rectangle(0,0,RECT3,RECT3);
        gameOver.widthProperty().bind(primaryStage.widthProperty());
        gameOver.heightProperty().bind(primaryStage.heightProperty());
        gameOver.setFill(COLOR_GAME_OVER);
        return;
      }
*/
      // create a gridPane as the grid to store tiles
      gridPane = new GridPane();

      // set the background color of gridPane
      gridPane.setStyle(style);

      // set the margin of girdPane and set the horizontal and vertial gap
      gridPane.setVgap(GAP1);
      gridPane.setHgap(GAP1);

      // get the number in board
      grid = board.getGrid();

      // create two borderPane
      flowPane = new BorderPane();    
      innerPane = new BorderPane();

      // creata a rectangle and bind its width to the width of the borderPane 
      // divides 7 and set the color
      Rectangle space1 = new Rectangle(RECT1,RECT1,RECT2,RECT2);
      space1.widthProperty().bind(primaryStage.widthProperty().divide(GAP1));
      space1.setFill(COLOR_BACK);

      // create a rectangle and bind its width to the width of the borderPane
      // divides 7 and set the color
      Rectangle space2 = new Rectangle(RECT1,RECT1,RECT2,RECT2);
      space2.widthProperty().bind(primaryStage.widthProperty().divide(GAP1));
      space2.setFill(COLOR_BACK);

      // create a rectangle and bind its height to the height of the 
      // borderPane divides 10 and set the color
      Rectangle space3 = new Rectangle(0,0,RECT4,RECT4);
      space3.heightProperty().bind(primaryStage.heightProperty().divide(TOP));
      space3.setFill(COLOR_BACK);

      // create a rectangle and bind its height to the height of the
      // borderPane dividves 10 and set the color 
      Rectangle space4 = new Rectangle(0,0,RECT4,RECT4);
      space4.heightProperty().bind(
      primaryStage.heightProperty().divide(SPACE4));
      space4.setFill(COLOR_BACK);
      
      // create a rectangle and bind its height to the height of the
      // borderPane dividves 10 and set the color 
      Rectangle space5 = new Rectangle(0,0,RECT4,RECT4);
      space5.heightProperty().bind(primaryStage.heightProperty().divide(TOP));
      space5.widthProperty().bind(primaryStage.widthProperty());
      space5.setFill(COLOR_BACK);

      // create a reflection and set the fraction to 1.0
      Reflection reflection = new Reflection();
      reflection.setFraction(REF);

      // create two text with the title and the socre by using the getScore
      // method, and set set the font, fontweight and size of these two text
      Text title = new Text(Title);
      Text score = new Text("Score: "+board.getScore());
      title.setFont(Font.font("Arial", FontWeight.BOLD, TWENTY));
      score.setFont(Font.font("Arial", FontWeight.BOLD, TEXT1));

      title.setStyle("-fx-font-size: primaryStage");

      // set the color of title to gold
      title.setFill(COLOR_TITLE);

      // set title and score with effect
      title.setEffect(reflection);
      score.setEffect(reflection);

      // set color score to color gold
      score.setFill(COLOR_TITLE);

      // add space3 rectangle to the top of innerPane
      innerPane.setTop(space3);

      // add title text to the left of innerPane
      innerPane.setLeft(title);

      // add score text to the right of innerPane
      innerPane.setRight(score);

      // add space4 to the bottom of innerPane
      innerPane.setBottom(space4);

      // loop throught the grid rows by rows and get the number in each tiles
      // in the grid. Create rectangle and text according to the number in 
      // gird and then add rectangle and text to a stackPane and then add 
      // stackPane to the gridpane. The number of stackpane in the gridpane 
      // depends on the number of entries in the original grid. If number
      // in the original grid is not zero, then create a text with number 
      // set the color according the number. If the number is zero, then 
      // only create a rectangle with empty color and add it to stackpane and
      // add the stackpane to the gridpane
      for(int i = 0; i < grid[1].length; i++){
        for(int j = 0; j < grid[1].length; j++){
    
          int number = grid[j][i];
          Pane stackPane = new StackPane();
          Rectangle r1 = new Rectangle(RECT2,RECT2,1,1);
          r1.setFill(COLOR_EMPTY);
          
          // if number is not 0, then create a text and set the color 
          // of the text to Color_light
          if(number != 0){
            Text text1 = new Text(number+"");
            text1.setFill(COLOR_VALUE_LIGHT);

            switch(number){

              case TWO: r1.setFill(COLOR_2);
                        break;
              case FOUR: r1.setFill(COLOR_4);
                         break;
              case EIGHT: r1.setFill(COLOR_8);
                          break;
              case SIXTEEN: r1.setFill(COLOR_16);
                            break;
              case THIRTY_TWO: r1.setFill(COLOR_32);
                               break;
              case SIXTY_FOUR: r1.setFill(COLOR_64);
                               break;
              case ONE_HUNDRED: r1.setFill(COLOR_128);
                                break;
              case TWO_HUNDRED: r1.setFill(COLOR_256);
                                break;
              case FIVE_HUNDRED: r1.setFill(COLOR_512);
                                 break;
              case ONE_THOUSAND: r1.setFill(COLOR_1024);
                                 break;
              case TWO_THOUSANDS: r1.setFill(COLOR_2048);
                                  break;
              default: r1.setFill(COLOR_OTHER);
            }

            // bind the widthproperty of r1 with widthproperty of borderPane
            r1.widthProperty().bind(primaryStage.widthProperty().subtract(
                  GAP1*(grid[1].length)).add(
                  GAP1).subtract(INSETS3).divide(grid[1].length+1));

            // bind the heightproperty of r1 with widthproperty of 
            // borderPane
            r1.heightProperty().bind(primaryStage.heightProperty().subtract(
               GAP1*(grid[1].length)).add(
               GAP1).subtract(SPACE4).divide(grid[1].length+1));

            // create a arc with height of ARC2 and bind the length of arc 
            // with height and width to the rectangle
            r1.setArcHeight(ARC2);
            r1.setArcWidth(ARC2);
            r1.arcHeightProperty().bind(r1.heightProperty().divide(SIX));
            r1.arcWidthProperty().bind(r1.heightProperty().divide(SIX));
          
            // create a doubleproperty and bind the font size double property
            // to the width of rectangle divided three and reset the size
            // of the text
            fontSizeR = new SimpleDoubleProperty(1);
            fontSizeR.bind(r1.widthProperty().divide(THREE));
            text1.styleProperty().bind(
            Bindings.concat("-fx-font-size: ", fontSizeR.asString()));
            
            // create a doubleproperty and bind the font size double property
            // to the width of rectangle divided three and reset the size
            // of the text
            fontSizeTitle = new SimpleDoubleProperty(1);
            fontSizeTitle.bind(r1.widthProperty().divide(THREE));
            title.styleProperty().bind(
            Bindings.concat("-fx-font-size: ", fontSizeTitle.asString()));
            
            // create a doubleproperty and bind the font size double property
            // to the width of rectangle divided three and reset the size
            // of the text
            fontSizeScore = new SimpleDoubleProperty(1);
            fontSizeScore.bind(r1.widthProperty().divide(THREE));
            score.styleProperty().bind(
            Bindings.concat("-fx-font-size: ", fontSizeScore.asString()));

            // set the font, fontweight and size of text1
            text1.setFont(Font.font(
                  "Helvetica Neue", FontWeight.BOLD, TWENTY));

            // add r1 and text1 to the stackPane
            stackPane.getChildren().addAll(r1,text1);

          }
          else{

            // bind the width property and height property of r1 with the 
            // width property of borderPane divided by SIX
            r1.widthProperty().bind(primaryStage.widthProperty().subtract(
                  GAP1*(grid[1].length)).add(
                  GAP1).subtract(INSETS3).divide(grid[1].length+1));
            r1.heightProperty().bind(primaryStage.heightProperty().subtract(
                  GAP1*(grid[1].length)).add(
                  GAP1).subtract(SPACE4).divide(grid[1].length+1));

            // create a arc with height of ARC2 and bind the length of arc 
            // with height and width to the rectangle
            r1.setArcHeight(ARC2);
            r1.setArcWidth(ARC2);
            r1.arcHeightProperty().bind(r1.heightProperty().divide(ARC));
            r1.arcWidthProperty().bind(r1.heightProperty().divide(ARC));
            
            // create a doubleproperty and bind the font size double property
            // to the width of rectangle divided three and reset the size
            // of the text
            fontSizeTitle = new SimpleDoubleProperty(1);
            fontSizeTitle.bind(r1.widthProperty().divide(THREE));
            title.styleProperty().bind(
            Bindings.concat("-fx-font-size: ", fontSizeTitle.asString()));
            
            // create a doubleproperty and bind the font size double property
            // to the width of rectangle divided three and reset the size
            // of the text
            fontSizeScore = new SimpleDoubleProperty(1);
            fontSizeScore.bind(r1.widthProperty().divide(THREE));
            score.styleProperty().bind(
            Bindings.concat("-fx-font-size: ", fontSizeScore.asString()));

            // add the rectangle to the stackPane
            stackPane.getChildren().add(r1);
          }

          // add stackPane to i,j to gridPane
          gridPane.add(stackPane,i,j);

        }
      }

      // set gridPane in the center of the center of boardPane
      gridPane.setAlignment(Pos.CENTER);

      // add flowPane to the center of innerPane and add space1 and space2 to
      // the left and right of flowPane. Then set the background of flowPane.
      flowPane.setCenter(innerPane);
      flowPane.setLeft(space1);
      flowPane.setRight(space2);
      flowPane.setStyle(style);
      borderPane.setTop(flowPane);
      borderPane.setBottom(space5);

      borderPane.setAlignment(space5,Pos.CENTER);
      
      // add gridPane to the center of borderPane
      borderPane.setCenter(gridPane); 

      // add borderPane to the borderPane2 
      borderPane2.getChildren().add(borderPane);
      
       if(board.isGameOver()){
                        
         Rectangle gameOver = new Rectangle(
          0,0,RECT3,RECT3);
         gameOver.widthProperty().bind(
          borderPane2.widthProperty());
         gameOver.heightProperty().bind(
          borderPane2.heightProperty());
         Text gameOverT = new Text("Game Over!");
         gameOverT.setFill(Color.BLACK);
         gameOverT.setFont(Font.font(
          "Century Gothic", FontWeight.BOLD, RECT5));
         gameOver.setFill(COLOR_GAME_OVER);

         // create doubleproperty and bind the font size double property
         // to the width of rectangle divided three and reset the size
         // of the text
         fontSizeG = new SimpleDoubleProperty(1);
         fontSizeG.bind(primaryStage.widthProperty().divide(EIGHT));
         gameOverT.styleProperty().bind(
         Bindings.concat("-fx-font-size: ", fontSizeG.asString()));
                       
         // add all the node to the borderPane
         borderPane2.getChildren().addAll(
              gameOver, gameOverT);
         borderPane2.setAlignment(gameOverT, Pos.CENTER);
                       
       }

      // create a scene and add the borderPane2 to the scene
      Scene scene = new Scene(borderPane2);
      
      // set the scene on the key pressed and create a EventHandler inner 
      // class
      scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

          /**                                                                              
           * Method for handling and updating the girdBored of 2048 board     
           *                                                                               
           * @param e the KeyEvent user enter
           */
          public void handle(KeyEvent e){

          // first check if the game is over
          if(!board.isGameOver()){
            
              grid = board.getGrid();

            // use a switch statement to execute different case according to 
            // the content of code presses
            switch(e.getCode()){
            case DOWN:if(board.moveAndAdd(Direction.DOWN)){

                System.out.println("Moving DOWN");
            }

            // get grid and clear all the nodes in the gridPane
                    grid = board.getGrid(); 
                    

                    // check if the game is over, then create a new 
                    // rectangle and a text of Game Over, and bind
                    // the height and width of the rectangle with 
                    // the height and width of the borderPane2 and set
                    // color of rectangle and text
                    if(board.isGameOver()){

                      Rectangle gameOver = new Rectangle(
                          0,0,RECT3,RECT3);
                      gameOver.widthProperty().bind(
                          borderPane2.widthProperty());
                      gameOver.heightProperty().bind(
                          borderPane2.heightProperty());
                      Text gameOverT = new Text("Game Over!");
                      gameOverT.setFill(Color.BLACK);
                      gameOverT.setFont(Font.font(
                            "Century Gothic", FontWeight.BOLD, RECT5));
                      gameOver.setFill(COLOR_GAME_OVER);

                      // create doubleproperty and bind the font size double property
                      // to the width of rectangle divided three and reset the size
                      // of the text
                      fontSizeG = new SimpleDoubleProperty(1);
                      fontSizeG.bind(primaryStage.widthProperty().divide(EIGHT));
                      gameOverT.styleProperty().bind(
                      Bindings.concat("-fx-font-size: ", fontSizeG.asString()));


                      // add gameOver and gameOverT to the borderPane2 
                      // and set alignment to center
                      borderPane2.getChildren().addAll(
                          gameOver, gameOverT);
                      borderPane2.setAlignment(gameOverT, Pos.CENTER);
                      
                    }

                    break;

          case UP:if(board.moveAndAdd(Direction.UP)){

                    System.out.println("Moving UP"); 
                  }

                  // get grid and clear all the nodes in the gridPane
                  grid = board.getGrid();  


                  // check if the game is over, then create a new 
                  // rectangle and a text of Game Over, and bind
                  // the height and width of the rectangle with 
                  // the height and width of the borderPane2 and 
                  // set color of rectangle and text
                  if(board.isGameOver()){
                    
                    Rectangle gameOver = new Rectangle(
                        0,0,RECT3,RECT3);
                    gameOver.widthProperty().bind(
                        borderPane2.widthProperty());
                    gameOver.heightProperty().bind(
                        borderPane2.heightProperty());
                    Text gameOverT = new Text("Game Over!");
                    gameOverT.setFill(Color.BLACK);
                    gameOverT.setFont(Font.font(
                          "Century Gothic", FontWeight.BOLD, RECT5));
                    gameOver.setFill(COLOR_GAME_OVER);

                    // create doubleproperty and bind the font size double property
                    // to the width of rectangle divided three and reset the size
                    // of the text
                    fontSizeG = new SimpleDoubleProperty(1);
                    fontSizeG.bind(primaryStage.widthProperty().divide(EIGHT));
                    gameOverT.styleProperty().bind(
                    Bindings.concat("-fx-font-size: ", fontSizeG.asString()));

                    // add all the node to the borderPane2
                    borderPane2.getChildren().addAll(
                        gameOver, gameOverT);
                    borderPane2.setAlignment(
                        gameOverT, Pos.CENTER);

                  }

                  break;  

          case LEFT:if(board.moveAndAdd(Direction.LEFT)){

                      System.out.println("Moving LEFT");  
                    }

                    // get grid and clear all the nodes in the gridPane
                    grid = board.getGrid(); 


                    // check if the game is over, then create a new 
                    // rectangle and a text of Game Over, and bind the
                    // height and width of the rectangle with the height
                    // and width of the borderPane2 and set color of 
                    // rectangle and text
                    if(board.isGameOver()){
                      
                      Rectangle gameOver = new Rectangle(
                          0,0,RECT3,RECT3);
                      gameOver.widthProperty().bind(
                          borderPane2.widthProperty());
                      gameOver.heightProperty().bind(
                          borderPane2.heightProperty());
                      Text gameOverT = new Text("Game Over!");
                      gameOverT.setFill(Color.BLACK);
                      gameOverT.setFont(Font.font(
                            "Century Gothic", FontWeight.BOLD, RECT5));
                      gameOver.setFill(COLOR_GAME_OVER);

                      // create doubleproperty and bind the font size double property
                      // to the width of rectangle divided three and reset the size
                      // of the text
                      fontSizeG = new SimpleDoubleProperty(1);
                      fontSizeG.bind(primaryStage.widthProperty().divide(EIGHT));
                      gameOverT.styleProperty().bind(
                      Bindings.concat("-fx-font-size: ", fontSizeG.asString()));

                      // add all the node to the borderPane
                      borderPane2.getChildren().addAll(
                          gameOver,gameOverT);
                      borderPane2.setAlignment(gameOverT, Pos.CENTER);
                      
                   }

                    break;

          case RIGHT:if(board.moveAndAdd(Direction.RIGHT)){
                       System.out.println("Moving RIGHT");

                     }

                     // get grid and clear all the nodes in the gridPane
                     grid = board.getGrid();  

                     // check if the game is over, then create a new 
                     // rectangle and a text of Game Over, and bind the
                     // height and width of the rectangle with 
                     // the height and width of the borderPane2 and set 
                     // color of rectangle and text
                     if(board.isGameOver()){
                        
                       Rectangle gameOver = new Rectangle(
                           0,0,RECT3,RECT3);
                       gameOver.widthProperty().bind(
                           borderPane2.widthProperty());
                       gameOver.heightProperty().bind(
                           borderPane2.heightProperty());
                       Text gameOverT = new Text("Game Over!");
                       gameOverT.setFill(Color.BLACK);
                       gameOverT.setFont(Font.font(
                             "Century Gothic", FontWeight.BOLD, RECT5));
                       gameOver.setFill(COLOR_GAME_OVER);

                       // create doubleproperty and bind the font size double property
                       // to the width of rectangle divided three and reset the size
                       // of the text
                       fontSizeG = new SimpleDoubleProperty(1);
                       fontSizeG.bind(primaryStage.widthProperty().divide(EIGHT));
                       gameOverT.styleProperty().bind(
                       Bindings.concat("-fx-font-size: ", fontSizeG.asString()));
                       
                       // add all the node to the borderPane
                       borderPane2.getChildren().addAll(
                           gameOver, gameOverT);
                       borderPane2.setAlignment(gameOverT, Pos.CENTER);
                      
                     }

                     break;

          case R: System.out.println("Rotate clockwise"); 
              
                  board.rotate(true);
                  
                  // get grid and clear all the nodes in the gridPane
                  grid = board.getGrid();  

                  // check if the game is over, then create a new 
                  // rectangle and a text of Game Over, and bind
                  // the height and width of the rectangle with 
                  // the height and width of the borderPane2 and 
                  // set color of rectangle and text
                  if(board.isGameOver()){
                    
                    Rectangle gameOver = new Rectangle(
                        0,0,RECT3,RECT3);
                    gameOver.widthProperty().bind(
                        borderPane2.widthProperty());
                    gameOver.heightProperty().bind(
                        borderPane2.heightProperty());
                    Text gameOverT = new Text("Game Over!");
                    gameOverT.setFill(Color.BLACK);
                    gameOverT.setFont(Font.font(
                          "Century Gothic", FontWeight.BOLD, RECT5));
                    gameOver.setFill(COLOR_GAME_OVER);

                    // create doubleproperty and bind the font size double property
                    // to the width of rectangle divided three and reset the size
                    // of the text
                    fontSizeG = new SimpleDoubleProperty(1);
                    fontSizeG.bind(primaryStage.widthProperty().divide(EIGHT));
                    gameOverT.styleProperty().bind(
                    Bindings.concat("-fx-font-size: ", fontSizeG.asString()));

                    // add all the node to the borderPane2
                    borderPane2.getChildren().addAll(
                        gameOver, gameOverT);
                    borderPane2.setAlignment(
                        gameOverT, Pos.CENTER);

                  }

                  break;  

          case U: System.out.println("Undo"); 
              
                  board.undo();

                  // get grid and clear all the nodes in the gridPane
                  grid = board.getGrid();  

                  // check if the game is over, then create a new 
                  // rectangle and a text of Game Over, and bind
                  // the height and width of the rectangle with 
                  // the height and width of the borderPane2 and 
                  // set color of rectangle and text
                  if(board.isGameOver()){
                    
                    Rectangle gameOver = new Rectangle(
                        0,0,RECT3,RECT3);
                    gameOver.widthProperty().bind(
                        borderPane2.widthProperty());
                    gameOver.heightProperty().bind(
                        borderPane2.heightProperty());
                    Text gameOverT = new Text("Game Over!");
                    gameOverT.setFill(Color.BLACK);
                    gameOverT.setFont(Font.font(
                          "Century Gothic", FontWeight.BOLD, RECT5));
                    gameOver.setFill(COLOR_GAME_OVER);

                    // create doubleproperty and bind the font size double property
                    // to the width of rectangle divided three and reset the size
                    // of the text
                    fontSizeG = new SimpleDoubleProperty(1);
                    fontSizeG.bind(primaryStage.widthProperty().divide(EIGHT));
                    gameOverT.styleProperty().bind(
                    Bindings.concat("-fx-font-size: ", fontSizeG.asString()));

                    // add all the node to the borderPane2
                    borderPane2.getChildren().addAll(
                        gameOver, gameOverT);
                    borderPane2.setAlignment(
                        gameOverT, Pos.CENTER);

                  }

                  break;  
          
          // if user enter "s" key, then it will save the current board and 
          // also print out the information in the terminal and exit this 
          // program
          default:if(e.getText().equals("s")){

                    try{
                      // save the board to outputBoard
                      board.saveBoard(outputBoard);
                    }
                    catch(Exception e1){
                      System.out.println(e1);
                    }

                    // print out information of saving board
                    System.out.println("Saving board to "+ outputBoard);
                    System.exit(0); // exit this program

                  }

          }
          
          // check if game is over, then clear all the node in the gridpane
          //if(!board.isGameOver()){
            gridPane.getChildren().clear(); 
          //} 

          // create a rectangle and bind its height to the height of the 
          // borderPane divides 10 and set the color to transparent
          Rectangle space3 = new Rectangle(0,0,RECT4,RECT4);
          space3.heightProperty().bind(
          primaryStage.heightProperty().divide(TOP));
          space3.setFill(COLOR_BACK);

          // create two text with the title and the socre by using the 
          // getScore method, and set set the font, fontweight and size of
          // these two text
          Text score = new Text("Score: "+board.getScore());
          score.setFont(Font.font("Arial", FontWeight.BOLD, TEXT1));
          score.setFill(COLOR_TITLE);
          Reflection reflection = new Reflection();
          reflection.setFraction(REF);
          score.setEffect(reflection);


          // add space3 rectangle to the top of innerPane
          innerPane.setTop(space3);

          // add score text to the right of innerPane
          innerPane.setRight(score);

          // loop throught the grid rows by rows and get the number in 
          // each tiles in the grid. Create rectangle and text according 
          // to the number in gird and then add rectangle and text to
          // a stackPane and then add stackPane to the gridpane. The
          // number of stackpane in the gridpane depends on the number
          // of entries in the original grid. If number in the original 
          // grid is not zero, then create a text with number set the
          // color according the number. If the number is zero, then only
          // create a rectangle with empty color and add it to stackpane and
          // add the stackpane to the gridpane
          for(int i = 0; i < grid[1].length; i++){
            for(int j = 0; j < grid[1].length; j++){
              
              grid = board.getGrid();
              int number = grid[j][i];
              Pane stackPane2 = new StackPane();
              Rectangle r2 = new Rectangle(RECT2,RECT2,1,1);
              r2.setFill(COLOR_EMPTY);

              if(number != 0){
                Text text2 = new Text(number+"");

                // switch the case based on the number, when number is 2,set
                // COLOR_2 to the rectangle, when number is 4, set COLOR_4 to 
                // rectangle and when number is 8, set COLOR_8 to the rectangle 
                // and when number is 16, set COLOR_16 to the rectangle and 
                // when number is 32, set COLOR_32 to the rectangle and when
                // number is 64, set COLOR_64 to the rectangle and when number 
                // is 128, set COLOR_128 to the rectangle and when number is 
                // 256, set COLOR_256 to the rectangle and when number is 512,
                // set COLOR_512 to the rectangle and when number is 1024, 
                // set COLOR_1024 to the rectangle and when number is 2048
                // set COLOR_2048 to the rectangle and otherwise set the 
                // COLOR_OTHERS to the rectangle. Also set the text with 
                // value less than 8 to light color and set the text with 
                // value more then 8 to dark color.
                switch(number){

                  case TWO: r2.setFill(COLOR_2);
                            text2.setFill(COLOR_VALUE_LIGHT);
                            break;
                  case FOUR: r2.setFill(COLOR_4);
                             text2.setFill(COLOR_VALUE_LIGHT);
                             break;
                  case EIGHT: r2.setFill(COLOR_8);
                              text2.setFill(COLOR_VALUE_LIGHT);
                              break;
                  case SIXTEEN: r2.setFill(COLOR_16);
                                text2.setFill(COLOR_VALUE_DARK);
                                break;
                  case THIRTY_TWO: r2.setFill(COLOR_32);
                                   text2.setFill(COLOR_VALUE_DARK);
                                   break;
                  case SIXTY_FOUR: r2.setFill(COLOR_64);
                                   text2.setFill(COLOR_VALUE_DARK);
                                   break;
                  case ONE_HUNDRED: r2.setFill(COLOR_128);
                                    text2.setFill(COLOR_VALUE_DARK);
                                    break;
                  case TWO_HUNDRED: r2.setFill(COLOR_256);
                                    text2.setFill(COLOR_VALUE_DARK);
                                    break;
                  case FIVE_HUNDRED: r2.setFill(COLOR_512);
                                     text2.setFill(COLOR_VALUE_DARK);
                                     break;
                  case ONE_THOUSAND: r2.setFill(COLOR_1024);
                                     text2.setFill(COLOR_VALUE_DARK);
                                     break;
                  case TWO_THOUSANDS: r2.setFill(COLOR_2048);
                                      text2.setFill(COLOR_VALUE_DARK);
                                      break;
                  default: r2.setFill(COLOR_OTHER);
                           text2.setFill(COLOR_VALUE_DARK);
                }

                // bind the widthproperty of r1 with widthproperty
                // of borderPane
                r2.widthProperty().bind(primaryStage.widthProperty().subtract(
                      GAP1*(grid[1].length)).add(GAP1).subtract(
                      INSETS3).divide(grid[1].length+1));

                // bind the heightproperty of r1 with widthproperty of 
                // borderPane
                r2.heightProperty().bind(
                primaryStage.heightProperty().subtract(
                GAP1*(grid[1].length)).add(
                GAP1).subtract(SPACE4).divide(grid[1].length+1));

                // create a arc with height of ARC2 and bind the length of arc 
                // with height and width to the rectangle
                r2.setArcHeight(ARC2);
                r2.setArcWidth(ARC2);
                r2.arcHeightProperty().bind(r2.heightProperty().divide(ARC));
                r2.arcWidthProperty().bind(r2.heightProperty().divide(ARC));

                // create doubleproperty and bind the font size double property
                // to the width of rectangle divided three and reset the size
                // of the text
                fontSizeR = new SimpleDoubleProperty(1);
                fontSizeR.bind(r2.widthProperty().divide(THREE));
                text2.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", fontSizeR.asString()));

                // create doubleproperty and bind the font size double property
                // to the width of rectangle divided three and reset the size
                // of the text
                fontSizeTitle = new SimpleDoubleProperty(1);
                fontSizeTitle.bind(r2.widthProperty().divide(THREE));
                title.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", fontSizeTitle.asString()));
                
                // create doubleproperty and bind the font size double property
                // to the width of rectangle divided three and reset the size
                // of the text
                fontSizeScore = new SimpleDoubleProperty(1);
                fontSizeScore.bind(r2.widthProperty().divide(THREE));
                score.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", fontSizeScore.asString()));
                
                // set the font, fontweight and size of text1
                text2.setFont(
                Font.font("Helvetica Neue", FontWeight.BOLD, TWENTY));

                // add r2 and text2 to stackPane
                stackPane2.getChildren().addAll(r2,text2);

              }
              else{

                // bind the width property and hight property with the width 
                // property of borderPane divided by 6
                r2.widthProperty().bind(primaryStage.widthProperty().subtract(
                      GAP1*(grid[1].length)).add(
                      GAP1).subtract(INSETS3).divide(grid[1].length+1));
                r2.heightProperty().bind(
                primaryStage.heightProperty().subtract(
                GAP1*(grid[1].length)).add(
                GAP1).subtract(SPACE4).divide(grid[1].length+1));

                // create a arc with height of ARC2 and bind the length of arc 
                // with height and width to the rectangle
                r2.setArcHeight(ARC2);
                r2.setArcWidth(ARC2);
                r2.arcHeightProperty().bind(r2.heightProperty().divide(ARC));
                r2.arcWidthProperty().bind(r2.heightProperty().divide(ARC));
                
                // create doubleproperty and bind the font size double property
                // to the width of rectangle divided three and reset the size
                // of the text
                fontSizeTitle = new SimpleDoubleProperty(1);
                fontSizeTitle.bind(r2.widthProperty().divide(THREE));
                title.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", fontSizeTitle.asString()));

                // create doubleproperty and bind the font size double property
                // to the width of rectangle divided three and reset the size
                // of the text
                fontSizeScore = new SimpleDoubleProperty(1);
                fontSizeScore.bind(r2.widthProperty().divide(THREE));
                score.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", fontSizeScore.asString()));


                // add r2 to the stackPane
                stackPane2.getChildren().add(r2);
              }

              // add stackPane to gridPane at (i,j)
              gridPane.add(stackPane2,i,j);

            }
          }

          gridPane.setAlignment(Pos.CENTER);

          }
          }

      });

      // set the scene to the stage
      primaryStage.setScene(scene);
      
      // show the primaryStage
      primaryStage.show();
    
      gridPane.requestFocus();

    }

  /** DO NOT EDIT BELOW */

  // The method used to process the command line arguments
  private void processArgs(String[] args)
  {
    String inputBoard = null;   // The filename for where to load the Board
    int boardSize = 0;          // The Size of the Board

    // Arguments must come in pairs
    if((args.length % 2) != 0)
    {
      printUsage();
      System.exit(-1);
    }

    // Process all the arguments 
    for(int i = 0; i < args.length; i += 2)
    {
      if(args[i].equals("-i"))
      {   // We are processing the argument that specifies
        // the input file to be used to set the board
        inputBoard = args[i + 1];
      }
      else if(args[i].equals("-o"))
      {   // We are processing the argument that specifies
        // the output file to be used to save the board
        outputBoard = args[i + 1];
      }
      else if(args[i].equals("-s"))
      {   // We are processing the argument that specifies
        // the size of the Board
        boardSize = Integer.parseInt(args[i + 1]);
      }
      else
      {   // Incorrect Argument 
        printUsage();
        System.exit(-1);
      }
    }

    // Set the default output file if none specified
    if(outputBoard == null)
      outputBoard = "2048.board";
    // Set the default Board size if none specified or less than 2
    if(boardSize < 2)
      boardSize = 4;

    // Initialize the Game Board
    try{
      if(inputBoard != null)
        board = new Board(inputBoard, new Random());
      else
        board = new Board(boardSize, new Random());
    }
    catch (Exception e)
    {
      System.out.println(
          e.getClass().getName() + " was thrown while creating a " +
          "Board from file " + inputBoard);
      System.out.println("Either your Board(String, Random) " +
          "Constructor is broken or the file isn't " +
          "formated correctly");
      System.exit(-1);
    }
  }

  // Print the Usage Message 
  private static void printUsage()
  {
    System.out.println("Gui2048");
    System.out.println("Usage:  Gui2048 [-i|o file ...]");
    System.out.println();
    System.out.println("  Command line arguments come in pairs of the" + 
        " form: <command> <argument>");
    System.out.println();
    System.out.println(
        "  -i [file]  -> Specifies a 2048 board that should be loaded");
    System.out.println();
    System.out.println("  -o [file]  -> Specifies a file that should be" + 
        " used to save the 2048 board");
    System.out.println("                If none specified then the" + 
        " default \"2048.board\" file will be used");
    System.out.println("  -s [size]  -> Specifies the size of the 2048" + 
        " board if an input file hasn't been");
    System.out.println("                specified.  If both -s and -i" + 
        " are used, then the size of the board");
    System.out.println("                will be determined by the input file."
    + " The default size is 4.");
  }
}

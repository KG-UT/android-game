
                PROJECT TITLE : CSC 207 Project Phase 2

            Welcome to Our CSC 207 Android Application

In this implementation the Tic Tac Toe Game can be played by the User.
In this implementation the Matching Cards Game can be played by the User.
In this implementation the Sliding Tiles Puzzle Game can be played by the User.

We were given this task for Phase 2 of our CSC 207: Software Design course.
Using an implementation of this project from Phase 1 of the project, we were to add and improve, at least the following requirements
to the previous implementation :

1. Code Coverage - Write a set of JUnit tests that cover as much of the code as you can.
(Our Code Coverage is broken down in Walkthrough,pdf)

2. Change the sliding tiles puzzle app so that the board is always solvable

3. Add two more games of roughly the complexity of the sliding tile puzzle. Make sure the scoreboard features accommodate them.
At least one new game has to have automatic save points of some kind, and at least one new game (maybe the same one) has to have undo.
Our two new games are TicTacToe(Which has Undo) and Matching Cards, both of which have autosave functionality.

4. Implement useful Design Patterns where required and Refactor code proactively.

5. Fix style issues, fix code smells, add javadoc, etc


                GETTING STARTED:
## URL:
https://markus.teach.cs.toronto.edu/git/csc207-2018-09-reg/group_0702

The above URL is a link to the remote repository where all of the code for the application is maintained.

                INSTALLATION AND SET UP
##  adapted from A2 sliding tile puzzle game instructions from Pages in Quercus CSC 207.

1. Install Android Studios or use Teaching Labs Account
2. Get the URL by copying it from above.
3. Clone the repository using checkout from Version Control via Git
4. Create an Android Virtual Device within Android Studio. Select a Pixel2 smartphone as the device to emulate, specifiying
   the device OS as Android 8.1 API 27. You may need to download this specific build of Android at this step if you're not
   using the CDF computers. Leave all other settings to default values.
5. Create and launch the virtual device and ensure it loads correctly.

THE WAY THE GAMES WORKS -
    You Log in with the log in screen. Enter your username and password (>= 6 characters).
    You can sign up if you have not already done so before, otherwise you can sign in.
    You will be taken to a screen where you can choose between three of the following games:

1. Sliding Tiles :
     You can slide the tiles adjacent to the blank area(which is a Pepe Frog), in all 4 directions if possible.
     The game ends when the tiles are successfully ordered in terms of numbers, with the blank tile(Pepe Frog)
     being at the bottom right. The Game can be saved with the save button present on the screen,
     which can be loaded by pressing back twice and then clicking the Saved Games Buttom and then
     clicking on the Saved Game button in the new layout. There is also an Autosave function which can be accessed from the starting screen.
     There is also an Undo button on the screen when playing the game which can be used to undo at least 3 moves by default.
     There is a score that is calculated based on number of moves, and the user who completes the game in minimum number of moves wins.

2. Tic Tac Toe :
     You will be taken to a page where there are two buttons :
     Pressing New Game will take you to a settings screen where you can select the complexity of the game, 3x3 or 5x5
     Once you have selected complexity, press start game to load the new game.
     Your score is how many times you beat or tie with the computer before you lose.
     Pressing Score button takes you to your scoreboard.
     The Game is played by pressing on a blank tile, which will generate an X, and the computer will automatically plays an O,
     and the way to win is to get 3 of your X's in a row - diagonal, horizontal or vertical. If the computer puts an O after an X,
     you cannot press that tile. Users can strategically this block the opponent from winning by placing their X or O before the opponents.
     There is an Undo Move button, which will undo your last move, a total of times.

3. Matching Cards :
     Pressing this takes you to a screen which has New Game button,Load Save Game, Load Autosave Game, and Scoreboard buttons.
     Pressing New game will take you to a new game of 4x4 complexity, where the objective is to
     match all the initally flipped down cards, in as less moves as possible.
     The cards are numbered in pairs, from 0 to 7.
     Each time you click a card, your score increases.
     The first initial card clicked from the initial face down cards will stay up.
     On clicking another card which is face down, if it matches the previous card, it will flip over.
     Otherwise the earlier card will flip down again, and you should try and remember it's position.
     Each time you click a card, your score increases.
     There is a save button which saves the game if you click it.
     Once you have matched all the pairs, the game ends.
     Pressing Load Save Game on the starting screen will load the game you saved by pressing Save.
     Pressing Load Autosave Game will load the autosaved game, which is saved at each click.
     Pressing ScoreBoard will take you to the Game ScoreBoard.

              VARIOUS CLASSES FUNCTIONALITIES EXPLAINED:
1. LoginActivity : Handles login and first time user creation
2. GameActivity: Parent of SlidingTileActivity, it is an abstract class
3. SlidingTilesStartingActivity: Handles main menu screen after login ing
4. DBTools : Sets up the sqlite database and provides the tools for reading and writing
5. SlidingTileActivity : Handles the sliding tiles game board
6. SlidingTileEndActivity: Handles the end game screen
7. SlidingTileGameScoreboardActivity: Handles the general (game) scoreboard
8. SlidingTileUserScoreboardActivity: Handles the personalized (user) scoreboard
9. SavedGamesView: Handles the save select screen
10. SlidingTilesSettingsActivity: Handles the settings screen
11. Score: Represents the score that a user got for a game
12. Scoreboard: Represents a scoreboard full of scores

## Android Studio Config:

                AKNOWLEDGEMENTS:
Credit for the project goes to all the team members - Kevin Guo, Rohit Bansal, Wenqin Ye, Kevin Li, Kuba Wernerowski










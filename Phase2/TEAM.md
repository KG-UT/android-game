# Contact Information

* Kuba Wernerowski
	* kubawernerowski@gmail.com
	* 647-850-5909

* Wenqin Ye
	* wenqin.ye@mail.utoronto.ca
	* 647-785-8729

* Kevin Guo
	* Kevin.guo@mail.utoronto.ca
	* 647-918-4810

* Kevin Zhou Li
	* kevinl6454@gmail.com
	* 581-995-8400

* Rohit Bansal
	* rohit.bansal@mail.utoronto.ca
	* 437-971-5848

---

# Communication Tools
* Facebook Group Chat
* Texting

---

# Team Contract

* I will get my allotted work done on time.
* I will attend every team meeting and actively participate.
* Should an emergency arise that prevents me from attending a team meeting, I will notify my team immediately.
* The work will be divided roughly equally among all team members.
* I will help my team to understand every concept in the project.
* If I do not understand a concept or code, I will immediately ask my team for help.

---



# Phase 1:
# Meeting Notes 
###### (Reverse Chronological Order)

## Meeting 4 -- October 31

#### Present: 
* Kuba Wernerowski
* Wenqin Ye
* Kevin Guo
* Kevin Zhou Li
* Rohit Bansal

#### Excused:
* All present

---

## Accomplished

1. Login Activity works.

2. MVP User objects are saved into the database with their username, password, and userId.
	* User, after login, is accessible globally from the LoginActivity class as a public static variable.

3. DBTools and database initialization, etc. are working so far in their MVP state. 

4. Implemented an undo moves method, and score calculating method in BoardManager.

5. Generalized Board to be able to created in arbitrary dimensions.
---

## End of Meeting Status

* Kuba
	* Figure out DB Schema for the following: 
		* Recording a User's saved games for each implemented game type.
			* Hash map?
		* Scores for each game type (Possibly store scoreboards?).
	* Refactoring to modularize different game activities in a more SOLID / OOP manner.
	

* Wenqin 
	* Working on modularizing settings for each game type (future proofing for phase 2)

* Kevin G.
	* Working on functions that are relevant to SlidingTileActivity such as the undo

* Kevin L.
	* Working on creating both a personalized scoreboard (per user) and a generalized scoreboard

* Rohit
	* Figure out how to potentially overwrite a given game state's .ser file with a new one.
	 

## TODO:

1. Critical:
	* Improve DB schema; implement storing of saved games and scores.
	* Continue implementions as we've been doing it so far.

2. Important:
	* Ensure database calls from each class work.
	* Clarify game behaviour with custom image as tiles.
	* Make Java Docs as we go along.

3. Non-Critical: 
	* Making things look pretty
	* Determine an exact way of calculating scores
	* Other optional functionalities
	* Rename group

## Meeting 3 -- October 29

#### Present: 
* Kuba Wernerowski
* Kevin Guo
* Kevin Zhou Li
* Rohit Bansal

#### Excused:
* Wenqin (out of the country)

---

## Accomplished

1. Worked on implementations together.

2. Some progress stalled by database work not yet done. Some technical issues on Kuba's side.

---

## End of Meeting Status

* Kuba
	* Database
	* Login / Registration

* Wenqin 
	* Launch Center View

* Kevin G.
	* Game Activity

* Kevin L.
	* Scoreboard / Game Activity 

* Rohit
	* Saved Games 

## TODO:

1. Critical:
	* Database Schema 
	* User Class
	* Login / Registration
	* Continue implementions as we've been doing it so far.

2. Important:
	* Ensure database calls from each class work.
	* Create function to correctly undo moves
	* Clarify game behaviour with custom image as tiles.
	* Make Java Docs as we go along.

3. Non-Critical: 
	* Making things look pretty
	* Determine an exact way of calculating scores
	* Other optional functionalities
	* Rename group


---
## Meeting 2 -- October 24

#### Present: 
* Kuba Wernerowski
* Wenqin Ye
* Kevin Guo
* Kevin Zhou Li
* Rohit Bansal

#### Excused:
* No Absences 

---

## Discussion

1. None. Mostly just spent time setting up the repo and fixing any issues from the initial set up.

---

## End of Meeting Status

* Kuba
	* Database
	* Login / Registration

* Wenqin 
	* Launch Center View

* Kevin G.
	* Game Activity

* Kevin L.
	* Scoreboard / Game Activity 

* Rohit
	* Saved Games 

## TODO:

1. Critical:
	* Database Schema 
	* User Class
	* Add the moves made Stack. 
	* Login / Registration
	* Start basic view models for each screen: Settings, Login / Registration, Default screen, scoreboard, etc.

2. Important:
	* Ensure database calls from each class work.
	* Connect with local storage correctly 
	* Create function to correctly undo moves
	* Score and Scoreboard
	* Clarify game behaviour with custom image as tiles.
	* Make Java Docs as we go along.

3. Non-Critical: 
	* Making things look pretty
	* Determine an exact way of calculating scores
	* Other optional functionalities
	* Rename group


---


## Meeting 1 -- October 22

#### Present: 
* Kuba Wernerowski
* Wenqin Ye
* Kevin Guo
* Kevin Zhou Li
* Rohit Bansal

#### Excused:
* No Absences

---

## Discussion

1. Will be doing bonus material actively along with base phase 1 requirements.

2. Will keep track of moves using a Stack. To undo any given move, pop the last move off the stack, ie. switch(x,y), and then call switch(y,x) to reverse it.

3. For scores, we will keep track of total moves taken as well as the total running time that the user has played. Upon the view loading in, timer is resumed. Each save updates the count of the total time thus far.

4. User class will have all previous played games stored, and unfinished saved games will be marked as such. Can delete games, upload images, download images. Each user will have their own unique images, besides the default ones.

5. Need to clarify how the game works when an image is uploaded. Do we have a white tile still, or can we move any tiles?

6. Will have a score class that implements the relevent comparable methods so we can display them using a score board class for viewing.

7. Database will likely be SQLite. Maybe MongoDB?

8. Launch Centre will view will be using a lot of different, smaller views like Saved Games, Login / Registration, Settings when starting new game

9. Need to figure out way of cutting up an image based on the user's choice of how large the board is.

10. Soft personal deadline for individual work will tentatively be 11:59 PM on Saturday, Oct 27. Gives some breathing room and room for bug testing / adding additional functionality.

11. Will be working largely as individuals, but we'll float around the project and help each other as needed.

12. Rename group at some point

---

## End of Meeting Status

* Kuba
	* Database
	* Login / Registration

* Wenqin 
	* Launch Center View

* Kevin G.
	* Game Activity

* Kevin L.
	* Scoreboard / Game Activity 

* Rohit
	* Saved Games 

## TODO:

1. Critical:
	* Copy our BEST A2 code to Phase1, and push.
	* Database Schema 
	* User Class
	* Add the moves made Stack. 
	* Login / Registration
	* Start basic view models for each screen: Settings, Login / Registration, Default screen, scoreboard, etc.

2. Important:
	* Ensure database calls from each class work.
	* Connect with local storage correctly 
	* Create function to correctly undo moves
	* Score and Scoreboard
	* Clarify game behaviour with custom image as tiles.
	* Make Java Docs as we go along.

3. Non-Critical: 
	* Making things look pretty
	* Determine an exact way of calculating scores
	* Other optional functionalities
	* Rename group



WORK DONE BY:

* Kuba
    - Implemented a sqlite database, DBTools
    - Allowed other classes and activities to save data to the database
    - Created database tools for saving and reading users
    - Created database tools for saving and reading scores of users
    - Created database tools for saving and reading save files
    - Made LoginActivity, a login screen, allowing users to log in or create their own account
    - Created the layout activity_login.xml, the UI for the login screen
    - Allowed the creation of new users in the login screen

* Wenqin
    - Implemented SettingActivity, a settings page and its corresponding UI, activity_settings.xml
    - Allowed the size of the board to be changed in the settings page
    - Connected SlidingTileStartingActivity with the settings screen and the settings screen to SlidingTileActivity
    - Revamped the layout activity_starting_.xml and functionality of SlidingTileStartingActivity by adding buttons and listeners
    - Created abstract class SettingsActivity, parent of SettingActivity
    - Helped come up with a solution to the autosave button
    - Fixed problem with the database and saving scores
    - Helped with debugging many critical issues with saving and the database

* Kevin G.
    - Implemented the undo function, undoMove() and getUndosLeft() which returns unused undos
    - Implemented a function to get the score, getScore()
    - Created the layout activity_ending_.xml
    - Created SlidingTileEndActivity which saves score to database and displays the score after the game is finished
    - Created abstract class GameEndActivity, a parent of SlidingTileEndActivity
    - Created a check in method update in SlidingTileActivity that checks to see if the game is over
    - Helped debugging problems with saving and autosave
    - Helped figure out how to pass data between different activities

* Kevin L.
    - Implemented the user scoreboard, SlidingTileUserScoreboardActivity
    - Implemented the game scoreboard SlidingTileGameScoreboardActivity
    - Created the abstract class ScoreboardActivity, parent of both scoreboards
    - Created the layout activity_user_scoreboard_display.xml, its listview, buttons and listeners
    - Created the layout activity_game_scoreboard_display.xml, its listview, buttons and listeners
    - Implemented class Score which holds int scores and string usernames. Allows for scores to be compared with each other
    - Implemented Scoreboard which holds scores and can sort them in order
    - Helped with passing in the score data from SlidingTileActivity to SlidingTileEndActivity

* Rohit
    - Autosave buttons and reading and writing of .ser files
    - Save and undo button in activity_main
    - Saving and loading from save file
    - Saved game buttons and saved games view layout
    - Added listeners for buttons
    - Linked save game and autosave buttons
    - Added undo default moves and undo n moves buttons
    - Some minor xml layout changes
    
    
    
# Phase 2:

# Meeting Notes 
###### (Reverse Chronological Order)

## Meeting 6  -- November 29

#### Present: 
* Kuba Wernerowski
* Wenqin Ye
* Kevin Guo
* Kevin Zhou Li
* Rohit Bansal

#### Excused:
* No Absences

--

## Discussion



--

## End of Meeting Status



## Accomplished



## TODO:




--


## Meeting 5  -- November 28

#### Present: 
* Kuba Wernerowski
* Wenqin Ye
* Kevin Guo
* Kevin Zhou Li
* Rohit Bansal

#### Excused:
* No Absences

--

## Discussion



--

## End of Meeting Status



## Accomplished



## TODO:




--


## Meeting 4  -- November 26

#### Present: 
* Kuba Wernerowski
* Wenqin Ye
* Kevin Guo
* Kevin Zhou Li
* Rohit Bansal

#### Excused:
* No Absences

--

## Discussion



--

## End of Meeting Status



## Accomplished



## TODO:




--


## Meeting 3  -- November 20

#### Present: 
* Kuba Wernerowski
* Wenqin Ye
* Kevin Guo
* Kevin Zhou Li
* Rohit Bansal

#### Excused:
* No Absences

--

## Discussion



--

## End of Meeting Status



## Accomplished



## TODO:




--


## Meeting 2  -- November 14

#### Present: 
* Kuba Wernerowski
* Wenqin Ye
* Kevin Guo
* Kevin Zhou Li
* Rohit Bansal

#### Excused:
* No Absences

--

## Discussion



--

## End of Meeting Status



## Accomplished



## TODO:




--


## Meeting 1  -- November 13

#### Present: 
* Kuba Wernerowski
* Wenqin Ye
* Kevin Guo
* Kevin Zhou Li
* Rohit Bansal

#### Excused:
* No Absences

--

## Discussion

    1. Initialized the repo and fixed issues

    2. Decided to implement TicTacToe
    
    3. Decided to implement Go
    
    4. Decided that the score for TicTacToe and Go should both be the amount of moves to win
    
    5. Discussed how the computer player should play, no conclusion on the matter

--

## End of Meeting Status

* Kuba
	* Database
	* Login / Registration
	* Go

* Wenqin 
	* Launch Center View

* Kevin G.
	* Make sliding tiles always solvable

* Kevin L.
	* Scoreboard refactoring 
	* Few of the Unit Tests

* Rohit
	* Most of the Unit Tests

## Accomplished



## TODO:




--


WORK DONE BY:

* Kuba
    - Implemented a sqlite database, DBTools
    - Allowed other classes and activities to save data to the database
    - Created database tools for saving and reading users
    - Created database tools for saving and reading scores of users
    - Created database tools for saving and reading save files
    - Made LoginActivity, a login screen, allowing users to log in or create their own account
    - Created the layout activity_login.xml, the UI for the login screen
    - Allowed the creation of new users in the login screen

* Wenqin
    - Implemented SettingActivity, a settings page and its corresponding UI, activity_settings.xml
    - Allowed the size of the board to be changed in the settings page
    - Connected SlidingTileStartingActivity with the settings screen and the settings screen to SlidingTileActivity
    - Revamped the layout activity_starting_.xml and functionality of SlidingTileStartingActivity by adding buttons and listeners
    - Created abstract class SettingsActivity, parent of SettingActivity
    - Helped come up with a solution to the autosave button
    - Fixed problem with the database and saving scores
    - Helped with debugging many critical issues with saving and the database

* Kevin G.
    - Implemented the undo function, undoMove() and getUndosLeft() which returns unused undos
    - Implemented a function to get the score, getScore()
    - Created the layout activity_ending_.xml
    - Created SlidingTileEndActivity which saves score to database and displays the score after the game is finished
    - Created abstract class GameEndActivity, a parent of SlidingTileEndActivity
    - Created a check in method update in SlidingTileActivity that checks to see if the game is over
    - Helped debugging problems with saving and autosave
    - Helped figure out how to pass data between different activities

* Kevin L.
    - Implemented the user scoreboard, SlidingTileUserScoreboardActivity
    - Implemented the game scoreboard SlidingTileGameScoreboardActivity
    - Created the abstract class ScoreboardActivity, parent of both scoreboards
    - Created the layout activity_user_scoreboard_display.xml, its listview, buttons and listeners
    - Created the layout activity_game_scoreboard_display.xml, its listview, buttons and listeners
    - Implemented class Score which holds int scores and string usernames. Allows for scores to be compared with each other
    - Implemented Scoreboard which holds scores and can sort them in order
    - Helped with passing in the score data from SlidingTileActivity to SlidingTileEndActivity

* Rohit
    - Autosave buttons and reading and writing of .ser files
    - Save and undo button in activity_main
    - Saving and loading from save file
    - Saved game buttons and saved games view layout
    - Added listeners for buttons
    - Linked save game and autosave buttons
    - Added undo default moves and undo n moves buttons
    - Some minor xml layout changes
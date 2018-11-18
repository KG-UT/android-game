package fall2018.csc2017.gamecentre;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import fall2018.csc2017.gamecentre.slidingTile.SlidingTileSavedGame;

/**
 * This class handles all database matters from reading to updating information.
 *
 *    <p>Code in part adapted from the follow resources on Nov 3, Nov 4, Nov 5 2018:</p>
 *    http://www.vogella.com/tutorials/AndroidSQLite/article.html#database-and-data-model
 *    https://stackoverflow.com/questions/22209046/example-and-explanation-android-studio-login-activity-template-generated-ac
 *    https://stackoverflow.com/questions/7510219/deleting-row-in-sqlite-in-android
 */

public class DBTools extends SQLiteOpenHelper {

    /**
     * The version of the database.
     */
    private final static int DB_VERSION = 10;

    /**
     * The name of the database.
     */
    private static final String DATABASE_NAME = "GameCentre.db";

    /**
     * The table names for the database.
     */
    private static final String TABLE_LOGINS = "logins";
    private static final String TABLE_SLIDING_TILE_SAVED = "slidingTileSavedGames";
    private static final String TABLE_SLIDING_TILE_SCORES = "slidingTileScores";

    /**
     * The column names for the login table.
     */
    private static final String COLUMN_USER_ID = "userId";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    /**
     * The column names for the Sliding Tile saved games table.
     *
     * Sliding Tile is abbreviated to ST for brevity as the
     * code is used in multiple places in the class.
     */
    private static final String COLUMN_ST_ID = "_id";
    private static final String COLUMN_ST_OWNER = "owner";
    private static final String COLUMN_ST_PATH = "filePath";

    /**
     *  The column names for the Sliding Tile scores table.
     *
     *  Sliding Tile is abbreviated to ST for brevity as the
     *  code is used in multiple places in the class.
     */
    private static final String COLUMN_ST_SCORE_ID = "_id";
    private static final String COLUMN_ST_SCORE_OWNER = "owner";
    private static final String COLUMN_ST_SCORE_VALUE = "score";

    /**
     * The login table construction query.
     */
    private static final String LOGINS_CREATE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_LOGINS + "(" + COLUMN_USER_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USERNAME + " TEXT, "
            + COLUMN_PASSWORD + " TEXT);";

    /**
     * The Sliding Tile saved games table construction query.
     */
    private static final String SLIDING_TILE_SAVED_GAMES_CREATE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_SLIDING_TILE_SAVED + "(" + COLUMN_ST_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ST_OWNER + " TEXT, "
            + COLUMN_ST_PATH + " TEXT);";

    /**
     * The Sliding Tile scores table construction query.
     */
    private static final String SLIDING_TILE_SCORES_CREATE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_SLIDING_TILE_SCORES + "(" + COLUMN_ST_SCORE_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ST_SCORE_OWNER + " TEXT, "
            + COLUMN_ST_SCORE_VALUE + " INTEGER);";

    /**
     * Instantiates a new Db tools.
     *
     * @param context the context.
     */
    public DBTools(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(LOGINS_CREATE);
        sqLiteDatabase.execSQL(SLIDING_TILE_SAVED_GAMES_CREATE);
        sqLiteDatabase.execSQL(SLIDING_TILE_SCORES_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        try{
            System.out.println("UPGRADE DB oldVersion=" + oldVersion + " - newVersion=" + newVersion);
            onCreate(sqLiteDatabase);
            if (oldVersion < 10){
                sqLiteDatabase.execSQL(LOGINS_CREATE);
                sqLiteDatabase.execSQL(SLIDING_TILE_SAVED_GAMES_CREATE);
                sqLiteDatabase.execSQL(SLIDING_TILE_SCORES_CREATE);
            }
        }
        catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        System.out.println("DOWNGRADE DB oldVersion=" + oldVersion + " - newVersion=" + newVersion);
    }

    /**
     * Inserts a new User into the database under the login table.
     *
     * @param queryValues User is passed in by LoginActivity.
     * @return The User just inserted into the database.
     */
    public User insertUser(User queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, queryValues.getUsername());
        values.put(COLUMN_PASSWORD, queryValues.getPassword());
        queryValues.setUserId(database.insert(TABLE_LOGINS, null, values));
        database.close();

        return queryValues;
    }

    /**
     * Retrieves the specified User from the database.
     *
     * @param username the username of the user we want.
     * @return the specified user.
     */
    public User getUser(String username) {
        String query = "SELECT " + COLUMN_USER_ID + ", "
                + COLUMN_PASSWORD + " FROM "
                + TABLE_LOGINS + " WHERE "
                + COLUMN_USERNAME + " = '" + username + "'";
        User myUser = new User(0, username, "");
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            // Column int id's.
            int userIdColumnIdx = cursor.getColumnIndexOrThrow(COLUMN_USER_ID);
            int userPasswordColumnIdx = cursor.getColumnIndexOrThrow(COLUMN_PASSWORD);

            do {
                myUser.setUserId(cursor.getLong(userIdColumnIdx));
                myUser.setPassword(cursor.getString(userPasswordColumnIdx));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return myUser;
    }

    /**
     * Retrieves an ArrayList of all Users in the database.
     *
     * @return ArrayList<User> of Users.
     */
    public ArrayList<User> getAllUsers() {
        String query = "SELECT * FROM " + TABLE_LOGINS;
        ArrayList<User> allUsers = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            // Assign the correct column index for each query part.
            int userIdColumnIdx = cursor.getColumnIndexOrThrow(COLUMN_USER_ID);
            int usernameColumnIdx = cursor.getColumnIndexOrThrow(COLUMN_USERNAME);
            int passwordColumnIdx = cursor.getColumnIndexOrThrow(COLUMN_PASSWORD);

            do {
                // Access the current row's user info and store it.
                long currentUserId = cursor.getLong(userIdColumnIdx);
                String currentUsername = cursor.getString(usernameColumnIdx);
                String currentPassword = cursor.getString(passwordColumnIdx);

                User currentUser = new User(currentUserId, currentUsername, currentPassword);
                allUsers.add(currentUser);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return allUsers;
    }

    /**
     * Deletes the User from the database.
     * This should be preceded by the user entering their password
     * to confirm the action.
     *
     * <p>This is equivalent to the following SQLite query:</p>
     *
     * <p>DELETE FROM logins WHERE username = 'username'</p>
     *
     * @param username the User's username
     * @return the number of updated rows.
     */
    public int deleteUser(String username) {
        SQLiteDatabase database = this.getWritableDatabase();
        String whereClause = COLUMN_USERNAME + "=?";
        String[] whereArgs = new String[]{username};

        return database.delete(TABLE_LOGINS, whereClause, whereArgs);
    }

    /**
     * Update the current user's password.
     *
     * @param queryValues the query values from the User object representing the current user.
     * @return the numbeer of updated rows.
     */
    public int updateUserPassword (User queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, queryValues.getUsername());
        values.put(COLUMN_PASSWORD, queryValues.getPassword());
        queryValues.setUserId(database.insert(TABLE_LOGINS, null, values));
        database.close();

        return database.update(TABLE_LOGINS, values, COLUMN_USER_ID + " = ?",
                                new String[] {String.valueOf(queryValues.getUserId())});
    }

    /**
     * Inserts the filepath into the database for the current game being played and associates it
     * with the owner (username) of it.
     *
     * @param queryValues the query values from the SlidingTileSavedGame object.
     * @return the Sliding Tile saved game object.
     */
    public SlidingTileSavedGame insertSlidingTileSavedGame(SlidingTileSavedGame queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ST_OWNER, queryValues.getOwner());
        values.put(COLUMN_ST_PATH, queryValues.getSlidingTileSavedGamePath());
        queryValues.set_id(database.insert(TABLE_SLIDING_TILE_SAVED, null, values));
        database.close();

        return queryValues;
    }

    /**
     * Gets Sliding Tile saved game.
     *
     * @param filepath the filepath for the Sliding Tile game in question.
     * @return the sliding tile saved game.
     */
    public SlidingTileSavedGame getSlidingTileSavedGame(String filepath) {
        String query = "SELECT " + COLUMN_ST_ID + ", "
                + COLUMN_ST_PATH + " FROM "
                + TABLE_SLIDING_TILE_SAVED + " WHERE "
                + COLUMN_ST_PATH + " = '" + filepath + "'";

        SlidingTileSavedGame mySavedGame = new SlidingTileSavedGame(0, filepath, "");
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            int idColumnIdx = cursor.getColumnIndexOrThrow(COLUMN_ST_ID);
            int ownerColumnIdx = cursor.getColumnIndexOrThrow(COLUMN_ST_OWNER);

            do {
                mySavedGame.set_id(cursor.getLong(idColumnIdx));
                mySavedGame.setOwner(cursor.getString(ownerColumnIdx));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return mySavedGame;
    }

    /**
     * Update the Sliding Tile saved game's file path.
     *
     * @param queryValues the query values from the SlidingTileSavedGame object.
     * @return the number of updated rows.
     */
    public int updateSlidingTileSavedGamePath (SlidingTileSavedGame queryValues) {
       SQLiteDatabase database = this.getWritableDatabase();
       ContentValues values = new ContentValues();
       values.put(COLUMN_ST_OWNER, queryValues.getOwner());
       values.put(COLUMN_ST_PATH, queryValues.getSlidingTileSavedGamePath());
       queryValues.set_id(database.insert(TABLE_SLIDING_TILE_SAVED, null, values));
       database.close();

       String whereClause = COLUMN_ST_ID + "=?";
       String[] whereArgs = new String[] { String.valueOf(queryValues.get_id()) };

       return database.update(TABLE_SLIDING_TILE_SAVED, values,whereClause, whereArgs);
    }

    /**
     * Deletes the Sliding Tile saved game associated with the given file path.
     * It is equivalent to the following SQLite query:
     *
     *<p>DELETE FROM slidingTileSavedGames WHERE filepath = 'filepath'</p>
     *
     * @param filepath the file path of the Sliding Tile saved game.
     * @return the number of updated rows.
     */
    public int deleteSlidingTileSavedGame (String filepath) {
        SQLiteDatabase database = this.getWritableDatabase();
        String whereClause = COLUMN_ST_PATH + "=?";
        String[] whereArgs = new String[]{ filepath };

        return database.delete(TABLE_SLIDING_TILE_SAVED, whereClause, whereArgs);
    }

    /**
     * Inserts Sliding Tile score for a user.
     *
     * @param queryValues the query values from the Score object.
     * @return the Score object.
     */
    public Score insertSlidingTileScore(Score queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ST_SCORE_OWNER, queryValues.getUserName());
        values.put(COLUMN_ST_SCORE_VALUE, queryValues.getUserScore());
        queryValues.set_id(database.insert(TABLE_SLIDING_TILE_SCORES, null, values));
        database.close();

        return queryValues;
    }

    /**
     * Returns an ArrayList of Score objects that are associated with
     * the user.
     *
     * @param owner the current user
     * @return the user's sliding tile scores as an ArrayList<Score>.
     */
    public ArrayList<Score> getUserSlidingTileScores(String owner) {
        ArrayList<Score> userSlidingTileScores = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        // Cursor arguments
        String[] columnToSearch = new String[] { COLUMN_ST_SCORE_VALUE, COLUMN_ST_SCORE_ID, COLUMN_ST_OWNER };
        String whereClause = COLUMN_ST_SCORE_OWNER + "=?";
        String[] whereArgs = new String[] { owner };
        Cursor cursor = database.query(TABLE_SLIDING_TILE_SCORES, columnToSearch,
                whereClause, whereArgs, null, null, null);

        if (cursor.moveToFirst()) {
            // Assign the correct column index for each query part.
            int scoreIdColumnIdx = cursor.getColumnIndexOrThrow(COLUMN_ST_SCORE_ID);
            int scoreOwnerColumnIdx = cursor.getColumnIndexOrThrow(COLUMN_ST_SCORE_OWNER);
            int scoreValueColumnIdx = cursor.getColumnIndexOrThrow(COLUMN_ST_SCORE_VALUE);
            do {
                // Access the current row's user info and store it.
                long currentScoreId = cursor.getLong(scoreIdColumnIdx);
                // TODO: Reimplement this for consistency later.
                String currentOwner = cursor.getString(scoreOwnerColumnIdx);
                //String currentOwner = LoginActivity.myUser.getUsername();
                int currentScoreValue = cursor.getInt(scoreValueColumnIdx);
                // TODO: Change the way User is passed in here as an arg.
                Score currentScore = new Score(currentScoreId, currentScoreValue, currentOwner);
                userSlidingTileScores.add(currentScore);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return userSlidingTileScores;

    }
}



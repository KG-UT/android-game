package fall2018.csc2017.gamecentre.database;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import java.util.List;

import fall2018.csc2017.gamecentre.database.asyncInterfaces.AsyncTTTGamesResult;
import fall2018.csc2017.gamecentre.database.dao.TTTBoardManagerDao;
import fall2018.csc2017.gamecentre.database.entity.TicTacToeBoardManager;

public class TTTGamesRepository implements AsyncTTTGamesResult {
    /**
     * The live data of all tic tac toe board managers for the logged in user.
     */
    private MutableLiveData<List<TicTacToeBoardManager>> ticTacToeGames = new MutableLiveData<>();

    /**
     * The TTT Board Manager DAO.
     */
    private TTTBoardManagerDao tttBoardManagerDao;

    /**
     * Instantiates a new Ttt games repository.
     *
     * @param application the application for the db's context.
     */
    public TTTGamesRepository(Application application) {
        AppDatabase db;
        db = AppDatabase.getAppDatabase(application);
        tttBoardManagerDao = db.tttBoardManagerDao();
    }

    /**
     * Stores the ST Board Managers associated with a specific username into slidingTileGames.
     *
     * @param username the user's username
     */
    public void getUserGames(String username) {
        new queryAsyncTask(tttBoardManagerDao).execute(username);
    }
//
//    /** TODO: COPY CODE FOR TTT BOARD MANAGER INTO THIS BRANCH
//     * Insert game.
//     *
//     * @param tttBoardManager the st board manager
//     */
//    public void insertGame(TTTBoardManager tttBoardManager) {
//        queryAsyncTask.insertAsyncTask task = new queryAsyncTask.insertAsyncTask(tttBoardManagerDao);
//        task.delegate = this;
//        task.execute(tttBoardManager);
//    }

    @Override
    public void asyncFinished(List<TicTacToeBoardManager> tttBoardManagers) {
        ticTacToeGames.setValue(tttBoardManagers);
    }

    /**
     * The outer layer of queryAsyncTask handles returning all the TTT Board Managers
     * that are associated with a specific username.
     *
     * The inner private class is for inserting into the DB.
     */
    private static class queryAsyncTask extends
            AsyncTask<String, Void, List<TicTacToeBoardManager>> {
        /**
         * The TTT Board Manager DAO.
         */
        private TTTBoardManagerDao asyncTaskDao;

        /**
         * The Sliding Tile Games repository instance.
         */
        private TTTGamesRepository delegate = null;

        /**
         * Instantiates a new async get user games task.
         */
        queryAsyncTask(TTTBoardManagerDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected void onPostExecute(List<TicTacToeBoardManager> ticTacToeTileBoardManagers) {
            delegate.ticTacToeGames.setValue(ticTacToeTileBoardManagers);
        }

        @Override
        protected List<TicTacToeBoardManager> doInBackground(String... strings) {
            return asyncTaskDao.getUserSavedGames(strings[0]);
        }

        /**
         * The async task for inserting TTT Board Managers into the database.
         */
        private static class insertAsyncTask extends
                AsyncTask<TicTacToeBoardManager, Void, Void> {
            /**
             * The TTT Board Manager DAO.
             */
            private TTTBoardManagerDao asyncTaskDao;

            /**
             * The Tic Tac Toe Games repository instance.
             */
            private TTTGamesRepository delegate = null;

            /**
             * Instantiates a new async get user games task.
             */
            insertAsyncTask(TTTBoardManagerDao dao) {
                asyncTaskDao = dao;
            }

            @Override
            protected Void doInBackground(TicTacToeBoardManager... params) {
                asyncTaskDao.insert(params[0]);
                return null;
            }
        }
    }
}

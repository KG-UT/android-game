package fall2018.csc2017.gamecentre.database;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import java.util.List;

import fall2018.csc2017.gamecentre.database.asyncInterfaces.AsyncSTGamesResult;
import fall2018.csc2017.gamecentre.database.dao.STBoardManagerDao;
import fall2018.csc2017.gamecentre.database.entity.SlidingTileBoardManager;

public class STGamesRepository implements AsyncSTGamesResult {
    /**
     * The live data of all Sliding Tile board managers for the logged in user.
     */
    private MutableLiveData<List<SlidingTileBoardManager>> slidingTileGames = new MutableLiveData<>();

    /**
     * The ST Board Manager DAO
     */
    private STBoardManagerDao stBoardManagerDao;

    /**
     * Instantiates a new St games repository.
     *
     * @param application the application for the db's context.
     */
    public STGamesRepository(Application application) {
        AppDatabase db;
        db = AppDatabase.getAppDatabase(application);
        stBoardManagerDao = db.stBoardManagerDao();
    }

    /**
     * Stores the ST Board Managers associated with a specific username into slidingTileGames.
     *
     * @param username the username
     */
    public void getUserGames(String username) {
        new queryAsyncTask(stBoardManagerDao).execute(username);
    }

    /**
     * Insert game.
     *
     * @param stBoardManager the st board manager
     */
    public void insertGame(SlidingTileBoardManager stBoardManager) {
        queryAsyncTask.insertAsyncTask task = new queryAsyncTask.insertAsyncTask(stBoardManagerDao);
        task.delegate = this;
        task.execute(stBoardManager);
    }

    @Override
    public void asyncFinished(List<SlidingTileBoardManager> stBoardManagers) {
        slidingTileGames.setValue(stBoardManagers);
    }

    /**
     * The outer layer of queryAsyncTask handles returning all the ST Board Managers
     * that are associated with a specific username.
     *
     * The inner private class is for inserting into the DB.
     */
    private static class queryAsyncTask extends
            AsyncTask<String, Void, List<SlidingTileBoardManager>> {
        /**
         * The ST Board Manager DAO.
         */
        private STBoardManagerDao asyncTaskDao;

        /**
         * The Sliding Tile Games repository instance.
         */
        private STGamesRepository delegate = null;

        /**
         * Instantiates a new async get user games task.
         */
        queryAsyncTask(STBoardManagerDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected void onPostExecute(List<SlidingTileBoardManager> slidingTileBoardManagers) {
            delegate.slidingTileGames.setValue(slidingTileBoardManagers);
        }

        @Override
        protected List<SlidingTileBoardManager> doInBackground(String... strings) {
            return asyncTaskDao.getUserSavedGames(strings[0]);
        }

        /**
         * The async task for inserting ST Board Managers into the db.
         */
        private static class insertAsyncTask extends
                AsyncTask<SlidingTileBoardManager, Void, Void> {
            /**
             * The ST Board Manager DAO.
             */
            private STBoardManagerDao asyncTaskDao;

            /**
             * The Sliding Tile Games repository instance.
             */
            private STGamesRepository delegate = null;

            /**
             * Instantiates a new async get user games task.
             */
            insertAsyncTask(STBoardManagerDao dao) {
                asyncTaskDao = dao;
            }

            @Override
            protected Void doInBackground(SlidingTileBoardManager... params) {
                asyncTaskDao.insert(params[0]);
                return null;
            }
        }
    }
}

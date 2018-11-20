package fall2018.csc2017.gamecentre.Database;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import java.util.List;

import fall2018.csc2017.gamecentre.Database.AsyncInterfaces.AsyncGoGamesResult;
import fall2018.csc2017.gamecentre.Database.Dao.GoBoardManagerDao;
import fall2018.csc2017.gamecentre.Database.Dao.STBoardManagerDao;
import fall2018.csc2017.gamecentre.Database.Entity.GoBoardManager;

public class GoGamesRepository implements AsyncGoGamesResult {
    /**
     * The live data of all Go board managers for the logged in user.
     */
    private MutableLiveData<List<GoBoardManager>> goGames = new MutableLiveData<>();

    /**
     * The Go Board Manager DAO.
     */
    private GoBoardManagerDao goBoardManagerDao;

    @Override
    public void asyncFinished(List<GoBoardManager> goBoardManagers) {
        goGames.setValue(goBoardManagers);
    }

    public GoGamesRepository(Application application) {
        AppDatabase db;
        db = AppDatabase.getAppDatabase(application);
        goBoardManagerDao = db.goBoardManagerDao();
    }

    /**
     * Stores the ST Board Managers associated with a specific username into slidingTileGames.
     *
     * @param username the user's username
     */
    public void getUserGames(String username) {
        new queryAsyncTask(goBoardManagerDao).execute(username);
    }

    /**
     * Insert game.
     *
     * @param goBoardManager the st board manager
     */
    public void insertGame(GoBoardManager goBoardManager) {
        queryAsyncTask.insertAsyncTask task = new queryAsyncTask.insertAsyncTask(goBoardManagerDao);
        task.delegate = this;
        task.execute(goBoardManager);
    }

    /**
     * The outer layer of queryAsyncTask handles returning all the Go Board Managers
     * that are associated with a specific username.
     *
     * The inner private class is for inserting into the DB.
     */
    private static class queryAsyncTask extends
            AsyncTask<String, Void, List<GoBoardManager>> {
        /**
         * The Go Board Manager DAO.
         */
        private GoBoardManagerDao asyncTaskDao;

        /**
         * The Go Games repository instance.
         */
        private GoGamesRepository delegate = null;

        /**
         * Instantiates a new async get user games task.
         */
        queryAsyncTask(GoBoardManagerDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected void onPostExecute(List<GoBoardManager> goBoardManagers) {
            delegate.goGames.setValue(goBoardManagers);
        }

        @Override
        protected List<GoBoardManager> doInBackground(String... strings) {
            return asyncTaskDao.getUserSavedGames(strings[0]);
        }

        /**
         * The async task for inserting Go Board Managers into the database.
         */
        private static class insertAsyncTask extends
                AsyncTask<GoBoardManager, Void, Void> {
            /**
             * The Go Board Manager DAO.
             */
            private GoBoardManagerDao asyncTaskDao;

            /**
             * The Go Games repository instance.
             */
            private GoGamesRepository delegate = null;

            /**
             * Instantiates a new async get user games task.
             */
            insertAsyncTask(GoBoardManagerDao dao) {
                asyncTaskDao = dao;
            }

            @Override
            protected Void doInBackground(GoBoardManager... params) {
                asyncTaskDao.insert(params[0]);
                return null;
            }
        }
    }
}

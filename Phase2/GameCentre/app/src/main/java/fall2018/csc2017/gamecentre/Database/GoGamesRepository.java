package fall2018.csc2017.gamecentre.Database;

import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import java.util.List;

import fall2018.csc2017.gamecentre.Database.AsyncInterfaces.AsyncGoGamesResult;
import fall2018.csc2017.gamecentre.Database.Dao.GoBoardManagerDao;
import fall2018.csc2017.gamecentre.Database.Entity.GoBoardManager;

public class GoGamesRepository implements AsyncGoGamesResult {
    /**
     * The live data of all sliding tile games for the logged in user.
     */
    private MutableLiveData<List<GoBoardManager>> goGames = new MutableLiveData<>();

    @Override
    public void asyncFinished(List<GoBoardManager> goBoardManagers) {
        goGames.setValue(goBoardManagers);
    }

    private static class queryAsyncGetUsersGames extends
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
        queryAsyncGetUsersGames(GoBoardManagerDao dao) {
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

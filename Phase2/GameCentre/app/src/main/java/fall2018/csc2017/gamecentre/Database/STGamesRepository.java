package fall2018.csc2017.gamecentre.Database;

import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import java.util.List;

import fall2018.csc2017.gamecentre.Database.AsyncInterfaces.AsyncSTGamesResult;
import fall2018.csc2017.gamecentre.Database.Dao.STBoardManagerDao;
import fall2018.csc2017.gamecentre.Database.Entity.SlidingTileBoardManager;

public class STGamesRepository implements AsyncSTGamesResult {
    /**
     * The live data of all sliding tile games for the logged in user.
     */
    private MutableLiveData<List<SlidingTileBoardManager>> slidingTileGames = new MutableLiveData<>();

    @Override
    public void asyncFinished(List<SlidingTileBoardManager> stBoardManagers) {
        slidingTileGames.setValue(stBoardManagers);
    }

    private static class queryAsyncGetUsersGames extends
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
        queryAsyncGetUsersGames(STBoardManagerDao dao) {
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
    }

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

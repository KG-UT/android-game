package fall2018.csc2017.gamecentre.Database;

import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import java.util.List;

import fall2018.csc2017.gamecentre.Database.AsyncInterfaces.AsyncTTTGamesResult;
import fall2018.csc2017.gamecentre.Database.Dao.TTTBoardManagerDao;
import fall2018.csc2017.gamecentre.Database.Entity.TicTacToeBoardManager;

public class TTTGamesRepository implements AsyncTTTGamesResult {
    /**
     * The live data of all sliding tile games for the logged in user.
     */
    private MutableLiveData<List<TicTacToeBoardManager>> ticTacToeGames = new MutableLiveData<>();

    @Override
    public void asyncFinished(List<TicTacToeBoardManager> tttBoardManagers) {
        ticTacToeGames.setValue(tttBoardManagers);
    }

    private static class queryAsyncGetUsersGames extends
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
        queryAsyncGetUsersGames(TTTBoardManagerDao dao) {
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

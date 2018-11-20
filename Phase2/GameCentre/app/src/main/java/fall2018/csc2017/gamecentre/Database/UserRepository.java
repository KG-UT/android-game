package fall2018.csc2017.gamecentre.Database;

import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import java.util.List;

import fall2018.csc2017.gamecentre.Database.AsyncInterfaces.AsyncUserResult;
import fall2018.csc2017.gamecentre.Database.Dao.UserDao;
import fall2018.csc2017.gamecentre.Database.Entity.User;

/**
 * The Data Repository handles interfacing data with the database itself.
 *
 * Adapted from: https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#7
 * https://github.com/googlesamples/android-architecture-components/blob/master/BasicSample/app/src/main/java/com/example/android/persistence/DataRepository.java
 * https://www.techotopia.com/index.php/An_Android_Room_Database_and_Repository_Tutorial
 */
public class UserRepository implements AsyncUserResult {
    /**
     * The live data for the currently logged in user.
     */
    private MutableLiveData<User> currentUser = new MutableLiveData<>();

    /**
     * The live data for all users in the database.
     */
    private MutableLiveData<List<User>> allUsers = new MutableLiveData<>();

    @Override
    public void asyncFinished(User user) {
        currentUser.setValue(user);
    }

    /**
     * The async task for getting all users.
     */
    private static class queryAsyncGetAllTask extends AsyncTask<Void, Void, List<User>> {
        /**
         * The User DAO.
         */
        private UserDao asyncTaskDao;

        /**
         * The User repository instance.
         */
        private UserRepository delegate = null;

        /**
         * Instantiates a new async get all task.
         *
         * @param dao the dao
         */
        queryAsyncGetAllTask(UserDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected List<User> doInBackground(Void... voids) {
            return asyncTaskDao.getAll();
        }

        @Override
        protected void onPostExecute(List<User> users) {
            delegate.allUsers.setValue(users);
        }
    }

    /**
     * The Async task for retrieving a user.
     */
    private static class queryAsyncGetUserTask extends AsyncTask<String, Void, User> {
        /**
         * The User DAO.
         */
        private UserDao asyncTaskDao;
        /**
         * The User Repository instance.
         */
        private UserRepository delegate = null;

        /**
         * Instantiates a new async task for users..
         *
         * @param dao the dao
         */
        queryAsyncGetUserTask(UserDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected User doInBackground(final String... params) {
            return asyncTaskDao.findByName(params[0]);
        }

        @Override
        protected void onPostExecute(User result) {
            delegate.asyncFinished(result);
        }
    }

    /**
     * The async task for inserting users.
     */
    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {
        /**
         * The User DAO.
         */
        private UserDao asynTaskDao;

        /**
         * Initializes the insert async task.
         *
         * @param dao   the user dao.
         */
        insertAsyncTask(UserDao dao) {
            asynTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            asynTaskDao.insert(params[0]);
            return null;
        }
    }


}

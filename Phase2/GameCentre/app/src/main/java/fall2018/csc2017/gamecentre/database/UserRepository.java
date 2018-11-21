package fall2018.csc2017.gamecentre.database;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import java.util.List;

import fall2018.csc2017.gamecentre.database.asyncInterfaces.AsyncUserResult;
import fall2018.csc2017.gamecentre.database.dao.UserDao;
import fall2018.csc2017.gamecentre.database.entity.User;

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

    /**
     * The User DAO
     */
    private UserDao userDao;

    /**
     * Instantiates the User Repository.
     *
     * @param application   the application context for the db.
     */
    public UserRepository(Application application) {
        AppDatabase db;
        db = AppDatabase.getAppDatabase(application);
        userDao = db.userDao();
    }

    /**
     * Insert a user into the database.
     *
     * @param user the user
     */
    public void insertUser(User user) {
        new queryAsyncTask.insertAsyncTask(userDao).execute(user);
   }

    /**
     * Stores a specific user into currentUser by username.
     *
     * @param username  the username of the user.
     */
   public void getUserByUsername(String username) {
        queryAsyncTask.getUserAsyncTask task = new queryAsyncTask.getUserAsyncTask(userDao);
        task.delegate = this;
        task.execute(username);
   }

   /**
    * Stores all users in the database into allUsers.
    */
   public void getAllUsers() {
        queryAsyncTask task = new queryAsyncTask(userDao);
        task.delegate = this;
        task.execute();
   }

    @Override
    public void asyncFinished(User user) {
        currentUser.setValue(user);
    }

    /**
     * The async task for getting all user operations.
     * queryAsyncTask's outside layer handles getting all users in the db.
     * The private inner classes are for retrieving indiviudal users and inserting users.
     */
    private static class queryAsyncTask extends AsyncTask<Void, Void, List<User>> {
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
        queryAsyncTask(UserDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected List<User> doInBackground(final Void... voids) {
            return asyncTaskDao.getAll();
        }

        @Override
        protected void onPostExecute(List<User> users) {
            delegate.allUsers.setValue(users);
        }

        /**
         * The async task for inserting users.
         */
        private static class insertAsyncTask extends AsyncTask<User, Void, Void> {
            /**
             * The User DAO.
             */
            private UserDao asyncTaskDao;

            /**
             * Initializes the insert async task.
             *
             * @param dao   the user dao.
             */
            insertAsyncTask(UserDao dao) {
                asyncTaskDao = dao;
            }

            @Override
            protected Void doInBackground(final User... params) {
                asyncTaskDao.insert(params[0]);
                return null;
            }
        }

        /**
         * The Async task for retrieving a user.
         */
        private static class getUserAsyncTask extends AsyncTask<String, Void, User> {
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
            getUserAsyncTask(UserDao dao) {
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

    }






}

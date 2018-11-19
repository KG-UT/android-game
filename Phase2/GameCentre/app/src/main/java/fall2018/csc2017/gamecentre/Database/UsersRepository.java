package fall2018.csc2017.gamecentre.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import java.util.List;

import fall2018.csc2017.gamecentre.Database.AsyncInterfaces.AsyncUserResult;
import fall2018.csc2017.gamecentre.Database.Dao.GoBoardManagerDao;
import fall2018.csc2017.gamecentre.Database.Dao.STBoardManagerDao;
import fall2018.csc2017.gamecentre.Database.Dao.TTTBoardManagerDao;
import fall2018.csc2017.gamecentre.Database.Dao.UserDao;
import fall2018.csc2017.gamecentre.Database.Entity.GoBoardManager;
import fall2018.csc2017.gamecentre.Database.Entity.User;

/**
 * The Data Repository handles interfacing data with the database itself.
 *
 * Adapted from: https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#7
 * https://github.com/googlesamples/android-architecture-components/blob/master/BasicSample/app/src/main/java/com/example/android/persistence/DataRepository.java
 */
public class UsersRepository implements AsyncUserResult {

    private MutableLiveData<User> currentUser = new MutableLiveData<>();
    /**
     * Instantiates a new Data repository.
     *
     * @param application the application
     */
    private UsersRepository(Application application) {
        AppDatabase mDatabase = AppDatabase.getAppDatabase(application);
        UserDao mUserDao = mDatabase.userDao();
    }

    @Override
    public void asyncFinished(User user) {
        currentUser.setValue(user);
    }

    private static class queryAsyncUserTask extends AsyncTask<String, Void, User> {
        private UserDao asyncTaskDao;
    }



    private static class queryAsyncTask extends
            AsyncTask<String, Void, List<Product>> {

        private ProductDao asyncTaskDao;
        private ProductRepository delegate = null;

        queryAsyncTask(ProductDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected List<Product> doInBackground(final String... params) {
            return asyncTaskDao.findProduct(params[0]);
        }

        @Override
        protected void onPostExecute(List<Product> result) {
            delegate.asyncFinished(result);
        }
    }
}

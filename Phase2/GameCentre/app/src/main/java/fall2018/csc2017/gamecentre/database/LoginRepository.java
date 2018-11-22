package fall2018.csc2017.gamecentre.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import fall2018.csc2017.gamecentre.database.dao.LoginDao;
import fall2018.csc2017.gamecentre.database.entity.User;
import fall2018.csc2017.gamecentre.database.entity.UserTable;

/**
 * The Login Repository handles logging in and storing handling login data matters.
 *
 * Adapted from: https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#7
 * https://github.com/googlesamples/android-architecture-components/blob/master/BasicSample/app/src/main/java/com/example/android/persistence/DataRepository.java
 * https://www.techotopia.com/index.php/An_Android_Room_Database_and_Repository_Tutorial
 */
public class LoginRepository {
    private LoginDao loginDao;
    private LiveData<List<UserTable>> allData;
    private static LiveData<UserTable> currentUser;
    private static int insertedUserId;

    public LoginRepository(Application application) {
        AppDatabase db = AppDatabase.getAppDatabase(application);
        loginDao = db.loginDao();
        allData = loginDao.getDetails();
    }

    public void deleteData() {
        loginDao.deleteAllData();
    }

    public LiveData<List<UserTable>> getAllData() {
        return allData;
    }

    public LiveData<UserTable> getCurrentUser(String email) {
       new LoginGetUser(loginDao).execute(email);
       return currentUser;
    }

    public int insertData(UserTable data) {
        new LoginInsertion(loginDao).execute(data);
        // Cast to int for creation of User.
        return insertedUserId;
    }

    private static class LoginInsertion extends AsyncTask<UserTable, Void, Void> {

        private LoginDao loginDao;

        private LoginInsertion(LoginDao loginDao) {

            this.loginDao = loginDao;

        }

        @Override
        protected Void doInBackground(UserTable... data) {
            insertedUserId = (int) loginDao.insertDetails(data[0]);
            return null;
        }
    }

    private static class LoginGetUser extends AsyncTask<String, Void, Void> {
        private LoginDao loginDao;

        private LoginGetUser(LoginDao loginDao) {
            this.loginDao = loginDao;
        }

        @Override
        protected Void doInBackground(String... strings) {
             currentUser = loginDao.getUserTableByEmail(strings[0]);
             return null;
        }
    }
}

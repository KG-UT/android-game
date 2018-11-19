package fall2018.csc2017.gamecentre.Database;

import android.app.Application;

import fall2018.csc2017.gamecentre.Database.Dao.GoBoardManagerDao;
import fall2018.csc2017.gamecentre.Database.Dao.STBoardManagerDao;
import fall2018.csc2017.gamecentre.Database.Dao.TTTBoardManagerDao;
import fall2018.csc2017.gamecentre.Database.Dao.UserDao;

/**
 * The Data Repository handles interfacing data with the database itself.
 *
 * Adapted from: https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#7
 */
public class DataRepository {

    private UserDao mUserDao;
    private STBoardManagerDao mSTBoardManagerDao;
    private TTTBoardManagerDao mTTTBoardManagerDao;
    private GoBoardManagerDao mGoBoardManagerDao;

    DataRepository(Application application) {

    }
}

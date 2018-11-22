package fall2018.csc2017.gamecentre.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.support.annotation.NonNull;

import java.util.List;

import fall2018.csc2017.gamecentre.database.entity.UserTable;

/**
 * The User Data Access Object
 *
 * Code adapted from: https://medium.com/@ajaysaini.official/building-database-with-room-persistence-library-ecf7d0b8f3e9
 * https://medium.com/@umang.burman.micro/android-room-persistence-library-a-login-example-with-livedata-b6019fe23b0
*/
@Dao
public interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertDetails(UserTable data);

    @Query("SELECT * FROM LoginDetails")
    LiveData<List<UserTable>> getDetails();

    @Query("SELECT * FROM LoginDetails WHERE Email = :email")
    LiveData<UserTable> getUserTableByEmail(String email);

    @Query("DELETE FROM LoginDetails")
    void deleteAllData();
}

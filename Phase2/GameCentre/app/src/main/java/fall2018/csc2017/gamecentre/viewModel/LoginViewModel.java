package fall2018.csc2017.gamecentre.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import fall2018.csc2017.gamecentre.database.LoginRepository;
import fall2018.csc2017.gamecentre.database.entity.UserTable;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository loginRepository;
    private LiveData<List<UserTable>> getAllData;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        loginRepository = new LoginRepository(application);
        getAllData = loginRepository.getAllData();
    }

    public int insert(UserTable data) {
        return loginRepository.insertData(data);
    }

    public LiveData<List<UserTable>> getAllUsers() {
        return getAllData;
    }

    public LiveData<UserTable> getCurrentUser(String email) {
        return loginRepository.getCurrentUser(email);
    }
}

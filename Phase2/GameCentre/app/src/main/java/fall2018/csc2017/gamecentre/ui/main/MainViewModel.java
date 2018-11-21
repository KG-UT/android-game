package fall2018.csc2017.gamecentre.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import fall2018.csc2017.gamecentre.database.UserRepository;
import fall2018.csc2017.gamecentre.database.entity.User;

public class MainViewModel extends AndroidViewModel {
    /**
     * The user repository
     */
    private UserRepository userRepository;

    /**
     * A mutable list of all users in the database.
     */
    private MutableLiveData<List<User>> allUsers;
    private MutableLiveData<User> currentUser;

    public MainViewModel(Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();

    }
}

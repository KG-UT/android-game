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

    /**
     * Instantiates the Main view model.
     *
     * @param application the application context for the db and AndroidViewModel super class.
     */
    public MainViewModel(Application application) {
        super(application);
        // The user repository
        userRepository = new UserRepository(application);
        // Get all users and the current user.
        allUsers = userRepository.getAllUsers();
        currentUser = userRepository.getCurrentUser();
        
    }
}

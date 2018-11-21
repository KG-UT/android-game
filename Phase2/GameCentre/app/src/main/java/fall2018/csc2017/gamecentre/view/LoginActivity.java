package fall2018.csc2017.gamecentre.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.View;

import java.util.List;
import java.util.Objects;

import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.dataBinding.Listener;
import fall2018.csc2017.gamecentre.database.entity.UserTable;
import fall2018.csc2017.gamecentre.database.entity.User;
import fall2018.csc2017.gamecentre.databinding.ActivityLoginBinding;
import fall2018.csc2017.gamecentre.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements Listener {

    public static User myUser;

    private LoginViewModel loginViewModel;

    private ActivityLoginBinding binding;

    /**
     * Makes use of data binding, and uses the Observer pattern to make note of changes
     * to the list of all user tables in the login details table.
     *
     * @param savedInstanceState    the instance state that was saved.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);

        binding.setClickListener(this);

        loginViewModel = ViewModelProviders.of(LoginActivity.this).get(LoginViewModel.class);

        loginViewModel.getAllUsers().observe(this, new Observer<List<UserTable>>() {
            @Override
            public void onChanged(@Nullable List<UserTable> data) {
                try {
                    binding.email.setText((Objects.requireNonNull(data).get(0).getEmail()));
                    binding.password.setText((Objects.requireNonNull(data.get(0).getPassword())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** TODO: Auth process. Hashing as well, I suppose.
     * Attempts to log the user in.
     *
     * @param view  The view
     */
    @Override
    public void onClick(View view) {

        String strEmail = binding.email.getText().toString().trim();
        String strPassword = binding.password.getText().toString().trim();

        UserTable data = new UserTable();
        // TODO: Use the go-to email regex online as verification.
        if (TextUtils.isEmpty(strEmail)) {
            binding.email.setError("Please Enter Your E-mail Address");
        }
        // TODO: make some kind of minimum password requirements.
        else if (TextUtils.isEmpty(strPassword)) {
            binding.email.setError("Please Enter Your Password");
        }
        else {
            data.setEmail(strEmail);
            data.setPassword(strPassword);
            loginViewModel.insert(data);
            // Here we start making the currently logged in User available globally.
            UserTable loggedInUser = loginViewModel.getCurrentUser(strEmail).getValue();
            // TODO: Handle null pointer case.
            int userId = loggedInUser.getId();
            String userEmail = loggedInUser.getEmail();
            String userPassword = loggedInUser.getPassword();
            // Set the value for the myUser globally available variable.
            myUser = new User(userId, userEmail, userPassword);
        }

    }
}
package fall2018.csc2017.gamecentre.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fall2018.csc2017.gamecentre.R;
// import fall2018.csc2017.gamecentre.dataBinding.Listener;    // TODO: Impliment this most likely
import fall2018.csc2017.gamecentre.dataBinding.Listener;
import fall2018.csc2017.gamecentre.database.entity.UserTable;
import fall2018.csc2017.gamecentre.database.entity.User;
import fall2018.csc2017.gamecentre.databinding.ActivityLoginBinding;
import fall2018.csc2017.gamecentre.viewModel.LoginViewModel;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity implements Listener {  // TODO: make this implement listener?

    /**
     * Assigned to the correct user upon successful sign up / login.
     */
    public static User myUser;

    /**
     * The login view model.
     */
    private LoginViewModel loginViewModel;

    // Adapted from: https://stackoverflow.com/questions/8204680/java-regex-email
    /**
     * The email validation regex.
     */
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * The data binder for this activity and view.
     */
    private ActivityLoginBinding binding;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        loginViewModel.getAllUsers().observe(this, new Observer<List<UserTable>>() {
            @Override
            public void onChanged(@Nullable List<UserTable> data) {
                try {
                    binding.email.setText((Objects.requireNonNull((data).get(0).getEmail())));
                    binding.password.setText((Objects.requireNonNull(data.get(0).getPassword())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        binding.emailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }
    // TODO: Need to implement this or rethink the design a bit.
    @Override
    public void onClick(Button button) {
        attemptLogin();
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }
        // Reset errors.
        binding.email.setError(null);
        binding.password.setError(null);

        // Store values at the time of the login attempt.
        String email = binding.email.getText().toString();
        String password = binding.password.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            binding.password.setError(getString(R.string.error_invalid_password));
            focusView = binding.password;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            binding.email.setError(getString(R.string.error_field_required));
            focusView = binding.email;
            cancel = true;
        } else if (!isEmailValid(email)) {
            binding.email.setError(getString(R.string.error_invalid_email));
            focusView = binding.email;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password, this);
            mAuthTask.execute((Void) null);
        }
    }

    /**
     * Validates an inputted email.
     *
     * @param emailStr the email str
     * @return the boolean
     */
    public static boolean isEmailValid(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            binding.loginProgress.setVisibility(show ? View.GONE : View.VISIBLE);
            binding.loginProgress.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    binding.loginProgress.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            binding.loginForm.setVisibility(show ? View.VISIBLE : View.GONE);
            binding.loginForm.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    binding.loginForm.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            binding.loginForm.setVisibility(show ? View.VISIBLE : View.GONE);
            binding.loginProgress.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /** TODO: figure out this leakage bullshit
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private final Context mContext;

        UserLoginTask(String email, String password, Context context) {
            mEmail = email;
            mPassword = password;
            mContext = context;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            LiveData<UserTable> currentUserTable = loginViewModel.getCurrentUser(mEmail);
            if (currentUserTable == null) {
                return true;
            }
            return currentUserTable.getValue().getId() > 0;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                LiveData<UserTable> currentUser = loginViewModel.getCurrentUser(mEmail);
                if (currentUser.getValue() != null) {
                    // Retrieve the user since they're already in the database.
                    // This lets us get the user's id without worry of null pointer exceptions.
                    int myUserId = currentUser.getValue().getId();
                    myUser = new User(myUserId, mEmail, mPassword);

                    Intent myIntent = new Intent(mContext, GameChoiceActivity.class);
                    LoginActivity.this.startActivity(myIntent);
                    finish();

                } else {
                    DialogInterface.OnClickListener dialogClickListener =
                            new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    try {
                                        finish();
                                        // Setting up the arguments for
                                        // creating myUser and the User Table.
                                        UserTable newUser = new UserTable();
                                        newUser.setEmail(mEmail);
                                        newUser.setPassword(mPassword);

                                        int myUserId = loginViewModel.insert(newUser);

                                        myUser = new User(myUserId, mEmail, mPassword);

                                        Intent myIntent = new Intent(LoginActivity.this, GameChoiceActivity.class);
                                        LoginActivity.this.startActivity(myIntent);
                                    } finally {
                                        System.out.println("temp");     // TODO: something here
                                    }
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    binding.password.setError(getString(R.string.error_incorrect_password));
                                    binding.password.requestFocus();
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
                    builder.setMessage(R.string.confirm_registry).setPositiveButton(R.string.yes, dialogClickListener)
                            .setNegativeButton(R.string.no, dialogClickListener).show();
                }
            } else {
                binding.password.setError(getString(R.string.error_incorrect_password));
                binding.password.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
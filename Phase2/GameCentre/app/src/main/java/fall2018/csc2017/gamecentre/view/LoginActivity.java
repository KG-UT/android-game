package fall2018.csc2017.gamecentre.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import fall2018.csc2017.gamecentre.R;
import fall2018.csc2017.gamecentre.User;

// Code adapted from: https://github.com/firebase/quickstart-android/tree/master/auth/app/src/main/java/com/google/firebase/quickstart
// https://www.androidhive.info/2016/10/android-working-with-firebase-realtime-database/

/**
 * The login activity.
 */
public class LoginActivity extends BaseLoginActivity implements View.OnClickListener {
    // Logging
    private static final String TAG = "LoginActivity";

    /**
     * The user's id in database.
     */
    private String userId;

    // Views
    private EditText mEmailField;
    private EditText mPasswordField;

    // Auth
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthListener mAuthListener;

    // Firebase User and database references.
    public static FirebaseUser currentUser;
    public DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_firebase);

        // Views
        mEmailField = findViewById(R.id.fieldEmail);
        mPasswordField = findViewById(R.id.fieldPassword);

        // Buttons
        findViewById(R.id.emailSignInButton).setOnClickListener(this);
        findViewById(R.id.emailCreateAccountButton).setOnClickListener(this);
        findViewById(R.id.signOutButton).setOnClickListener(this);
        findViewById(R.id.goToGamesButton).setOnClickListener(this);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize database instance.
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Code adapted from https://stackoverflow.com/questions/41533159/firebase-getcurrentuser-is-returning-null-in-new-android-activity
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                if (currentUser != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + currentUser.getUid());
                    finish();

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    /**
     * Updates the UI depending on whether the user has been authenticated or not.
     *
     * @param user  The FirebaseUser.
     */
    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {
            findViewById(R.id.emailPasswordButtons).setVisibility(View.GONE);
            findViewById(R.id.emailPasswordFields).setVisibility(View.GONE);
            findViewById(R.id.signedInButtons).setVisibility(View.VISIBLE);
            // Ignore that a user isn't email verified yet, we don't actually care.
            findViewById(R.id.goToGamesButton).setEnabled(!user.isEmailVerified());
        } else {
            findViewById(R.id.emailPasswordButtons).setVisibility(View.VISIBLE);
            findViewById(R.id.emailPasswordFields).setVisibility(View.VISIBLE);
            findViewById(R.id.signedInButtons).setVisibility(View.GONE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    public void onResume() {
        super.onResume();

        currentUser = mAuth.getCurrentUser();

    }

    /**
     * Creates a user account.
     *
     * @param email The new user's email.
     * @param password  The new user's password.
     */
    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                            // TODO: See if this is actually an issue.
                            // I don't think it is, since creating new users is fine so far
                            // and changes are reflected in the database.
                            addUserToDatabase(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }
    // Code adapted from https://firebase.google.com/docs/database/android/read-and-write
    /**
     *  Adds the new user to the database.
     *
     * @param newUser   The authenticated new user.
     */
    private void addUserToDatabase(final FirebaseUser newUser) {
        // Gets the userId for where it'll be stored in the database.
        userId = mDatabase.push().getKey();
        String newUserEmail = newUser.getEmail();

        // The new user.
        User userToInsert = new User(userId, newUserEmail);

        // Adds on success and on failure listeners to the user being inserted.
        mDatabase.child("users").child(userId).setValue(userToInsert)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "insertedUserIntoDatabase:success");
                        // Given success of creating new user, we can guarantee that
                        // it will be non-null.
                        currentUser = newUser;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "insertUserIntoDatabase:failure");
                    }
                });

        addUserChangeListener();
    }

    /**
     * Attempts to login given an email and password.
     *
     * @param email The user's email.
     * @param password  The user's password.
     */
    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        // Attempts to sign in with an email and password.
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }

    /**
     * Signs a user out and returns view to sign up.
     */
    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }

    /**
     * Moves to GameChoiceActivity.
     */
    private void goToGames() {
        // Enable button
        findViewById(R.id.goToGamesButton).setEnabled(false);
        // Creates the intent to go to Game Choice Activity.
        Intent goToGamesIntent = new Intent(LoginActivity.this, GameChoiceActivity.class);
        LoginActivity.this.startActivity(goToGamesIntent);
    }

    /** TODO: figure out what android studio is complaining about here. Not essential.
     * Validates the login / sign up form.
     *
     * @return the status of the form validation as a boolean.
     */
    private boolean validateForm() {
        // Check to make sure there's an email.
        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            return false;
        } else {
            mEmailField.setError(null);
        }
        // Checks for password.
        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            return false;
        } else {
            mPasswordField.setError(null);
        }

        return true;
    }

    /**
     * User data change listener
     */
    private void addUserChangeListener() {
        // User data change listener
        mDatabase.child("users").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                // Check for null
                if (user == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "User data is changed!" + user.getUsername());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.emailCreateAccountButton) {
            createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
        } else if (i == R.id.emailSignInButton) {
            signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
        } else if (i == R.id.signOutButton) {
            signOut();
        } else if (i == R.id.goToGamesButton) {
            goToGames();
        }
    }
}
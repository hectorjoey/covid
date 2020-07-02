package mobile.fhi360.covid_19selfscreeningtool.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import mobile.fhi360.covid_19selfscreeningtool.Api.RetrofitClient;
import mobile.fhi360.covid_19selfscreeningtool.R;
import mobile.fhi360.covid_19selfscreeningtool.model.Supervisor;
import mobile.fhi360.covid_19selfscreeningtool.model.Users;
import mobile.fhi360.covid_19selfscreeningtool.sharedPref.PH;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    EditText mEmail, mPassword;
    Button mLogin, mSupLogin;
    ProgressDialog loadingBar;
    String sharedPrefUserId = "UserId";
    Users users;
    Long userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail = findViewById(R.id.et_email);
        mPassword = findViewById(R.id.et_password);
        mLogin = findViewById(R.id.btn_login);
        mSupLogin = findViewById(R.id.supLogin);
        loadingBar = new ProgressDialog(this);
        loadingBar = new ProgressDialog(this);
        users = new Users();
        mSupLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (email.isEmpty()) {
                    mEmail.setError("Email is required");
                    mEmail.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    mEmail.setError("Enter a valid email");
                    mEmail.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    mPassword.setError("Password required");
                    mPassword.requestFocus();
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password should be at least 6 character long");
                    mPassword.requestFocus();
                }
                loginSup(email, password);
            }
        });


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (email.isEmpty()) {
                    mEmail.setError("Email is required");
                    mEmail.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    mEmail.setError("Enter a valid email");
                    mEmail.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    mPassword.setError("Password required");
                    mPassword.requestFocus();
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password should be at least 6 character long");
                    mPassword.requestFocus();
                }
                loginUser(email, password);
            }
        });
    }

    private void loginSup(String email, String password) {
        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .supLogin(email, password);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Authenticating please wait...");
        progressDialog.show();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    Intent supIntent = new Intent(LoginActivity.this, SupervisorActivity.class);
                    assert response.body() != null;
//                    supIntent.putExtra("SP", response.body().getUserType());
                    startActivity(supIntent);
                    finish();
                    Toast.makeText(LoginActivity.this, "Welcome Sup!", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed!", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "failed!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void loginUser(String email, String password) {
        //do registration API call

        Call<Users> call = RetrofitClient
                .getInstance()
                .getApi().login(email, password);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Authenticating please wait...");
        progressDialog.show();

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    users = response.body();
//                    assert response.body() != null;
//                    userID = response.body().getId();
                    System.out.println("USER ID ==> " + userID);
                    assert users != null;
                    //save userId to shared pref
                    PH.get().setString(getApplicationContext(), sharedPrefUserId, String.valueOf(users.getId()));

                    if (users.getUserType().equalsIgnoreCase("admin")) {
                        progressDialog.dismiss();
                        Intent adminIntent = new Intent(LoginActivity.this, DashboardActivity.class);
                        assert response.body() != null;
                        adminIntent.putExtra("Admin", response.body().getUserType());
                        startActivity(adminIntent);
                        finish();
                        Toast.makeText(LoginActivity.this, "Welcome Admin!", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    } else if (users.getUserType().equalsIgnoreCase("user")) {
                        Intent intent = new Intent(LoginActivity.this, UserHealthDataActivity.class);
                        assert response.body() != null;
                        intent.putExtra("User", response.body().getUserType());
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Welcome user!", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                } else {
                    System.out.println("RESPONSE ==>> " + response);
                    Toast.makeText(LoginActivity.this, "Cannot login! Check Credentials.!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                System.out.println("throwing ..." + t);
                loadingBar.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}

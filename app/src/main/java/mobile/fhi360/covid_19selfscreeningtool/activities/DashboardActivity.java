package mobile.fhi360.covid_19selfscreeningtool.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import mobile.fhi360.covid_19selfscreeningtool.Api.RetrofitClient;
import mobile.fhi360.covid_19selfscreeningtool.R;
import mobile.fhi360.covid_19selfscreeningtool.RegisterUserActivity;
import mobile.fhi360.covid_19selfscreeningtool.adapter.UserAdapter;
import mobile.fhi360.covid_19selfscreeningtool.adapter.UserHealthAdapter;
import mobile.fhi360.covid_19selfscreeningtool.model.UserHealthData;
import mobile.fhi360.covid_19selfscreeningtool.model.Users;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    ProgressDialog loadingBar;

    private RecyclerView rv;
    private List<Users> userList;
    private UserAdapter adapter;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        rv = findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        setTitle("Risk Assessment Dashboard");
        loadingBar = new ProgressDialog(this);
        fetchData();
    }

    private void fetchData() {
        Call<List<Users>> call = RetrofitClient
                .getInstance()
                .getApi()
                .getUsers();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                loadingBar.dismiss();
                userList = response.body();
                System.out.println("Respondingg >>>>" + userList);
                System.out.println("Respondingg ++++++" + response);
                adapter = new UserAdapter(userList, DashboardActivity.this);
                System.out.println(adapter);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

                Toast.makeText(DashboardActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.main_create_supervisor:
                createSupervisor();
                return true;
            case R.id.main_settings:
//                settings();
                return true;

            case R.id.main_create_user:
                createUser();
                return true;
//            case R.id.main_update_user:
//                updateUser();
//                return true;
            case R.id.main_refresh:
                fetchData();
                return true;
            case R.id.main_logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createSupervisor() {
        Intent regIntent = new Intent(DashboardActivity.this, RegisterUserActivity.class);
        startActivity(regIntent);
        finish();
    }

    private void createUser() {
        Intent regIntent = new Intent(DashboardActivity.this, RegisterActivity.class);
        startActivity(regIntent);
        finish();
    }

    private void logout() {
        Intent logOut = new Intent(DashboardActivity.this, LoginActivity.class);
        startActivity(logOut);
        finish();
    }

//    private void createUserHealth() {
//        Intent intent = new Intent(DashboardActivity.this, UserHealthDataActivity.class);
//        startActivity(intent);
//        finish();
//    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Want to go back?")
                .setMessage("Are you sure you want to go back?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).create().show();
    }

}
package mobile.fhi360.covid_19selfscreeningtool.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mobile.fhi360.covid_19selfscreeningtool.Api.RetrofitClient;
import mobile.fhi360.covid_19selfscreeningtool.R;
import mobile.fhi360.covid_19selfscreeningtool.adapter.UserAdapter;
import mobile.fhi360.covid_19selfscreeningtool.model.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SupervisorActivity extends AppCompatActivity {
    String TAG = "SUPERVISOR ACTIVITY";
    private Toolbar mToolbar;

    String userId;
    String sharedPrefUserId = "UserId";
    ProgressDialog loadingBar;
    private RecyclerView rv;
    private List<Users> usersList;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor);
        rv = findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        setTitle("Supervisor Dashboard");
        loadingBar = new ProgressDialog(this);
        fetchData();
    }

    public void fetchData() {
        Users users = new Users();
       Long supervisor =  users.getSupervisorId();
        Call<List<Users>> call = RetrofitClient
                .getInstance()
                .getApi()
                .getUsersBySupervisorId(supervisor);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if (response.isSuccessful()) {
                    loadingBar.dismiss();
                    usersList  = response.body();
                    Log.d(TAG, "onResponse: Listing>>>>>>" + usersList);
                    usersList = new ArrayList<>();
                    adapter = new UserAdapter(usersList, SupervisorActivity.this);
                    System.out.println("userlist ==>> " + usersList);
                    System.out.println("adapter ===>> " + adapter);

                    Log.d(TAG, "onResponse: RESPONSE BODY " + response.body());
                    rv.setAdapter(adapter);
                } else {
                    System.out.println("FAiled!");
                    Log.d(TAG, "onResponse: ERROR BODY ==>> "+response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+ t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dash_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
//            case R.id.main_create_user_health:
//                createUserHealth();
//                return true;
            case R.id.main_settings:
//                settings();
                return true;
            case R.id.main_refresh:
//                fetchData(supervisorId);
                return true;
            case R.id.main_logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        Intent intent = new Intent(SupervisorActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Want to go back?")
                .setMessage("Are you sure you want to go back?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(SupervisorActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).create().show();
    }
}
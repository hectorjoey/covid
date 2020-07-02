package mobile.fhi360.covid_19selfscreeningtool.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mobile.fhi360.covid_19selfscreeningtool.Api.RetrofitClient;
import mobile.fhi360.covid_19selfscreeningtool.R;
import mobile.fhi360.covid_19selfscreeningtool.adapter.UserHealthAdapter;
import mobile.fhi360.covid_19selfscreeningtool.model.UserHealthData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HealthListActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    ProgressDialog loadingBar;

    private RecyclerView rv;
    private List<UserHealthData>userHealthDataList;
    private UserHealthAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_list);

        rv= findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        setTitle("Supervisor Dashboard");
        loadingBar = new ProgressDialog(this);
        fetchData();
    }

    private void fetchData() {
        Call<List<UserHealthData>> call = RetrofitClient
                .getInstance()
                .getApi()
                .getUserHealthData();
        call.enqueue(new Callback<List<UserHealthData>>() {
            @Override
            public void onResponse(Call<List<UserHealthData>> call, Response<List<UserHealthData>> response) {
                loadingBar.dismiss();
                userHealthDataList = response.body();
                adapter = new UserHealthAdapter(userHealthDataList, HealthListActivity.this);
                System.out.println(adapter);
                rv.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<UserHealthData>> call, Throwable t) {

                Toast.makeText(HealthListActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.options_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle item selection
//        switch (item.getItemId()) {
//            case R.id.main_create_user_health:
//                createUserHealth();
//                return true;
//            case R.id.main_settings:
////                settings();
//                return true;
//            case R.id.main_refresh:
//                fetchData();
//                return true;
//            case R.id.main_logout:
//                logout();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    private void logout() {
        Intent logOut = new Intent(HealthListActivity.this, LoginActivity.class);
        startActivity(logOut);
        finish();
    }

    private void createUserHealth() {
        Intent intent = new Intent(HealthListActivity.this, UserHealthDataActivity.class);
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
                        Intent intent = new Intent(HealthListActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).create().show();
    }
}
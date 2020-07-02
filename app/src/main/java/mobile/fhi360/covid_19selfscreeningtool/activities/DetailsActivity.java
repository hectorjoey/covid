package mobile.fhi360.covid_19selfscreeningtool.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import mobile.fhi360.covid_19selfscreeningtool.R;

public class DetailsActivity extends AppCompatActivity {
    String tvFirstName, tvLastName, tvState, tvDesignation, tvUserType,
            tvStatus;
    CardView cardView;
    TextView firstname, lastname, state, designation, userType, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        firstname = findViewById(R.id.tvFirstname);
        lastname = findViewById(R.id.tvLastname);
        state = findViewById(R.id.tvState);
        designation = findViewById(R.id.tvDesignation);
        userType = findViewById(R.id.tvUserType);
        status = findViewById(R.id.tvStatus);


        tvFirstName = getIntent().getStringExtra("firstname");
        tvLastName = getIntent().getStringExtra("lastname");
        tvState = getIntent().getStringExtra("tvDesignation");
    }
}
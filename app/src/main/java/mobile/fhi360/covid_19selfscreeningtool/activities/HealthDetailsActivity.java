package mobile.fhi360.covid_19selfscreeningtool.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import mobile.fhi360.covid_19selfscreeningtool.R;

public class HealthDetailsActivity extends AppCompatActivity {
     TextView tvFullname;
     TextView tvDate;
     TextView tvFeverSymptom;
     TextView tvCoughSymptom;
     TextView tvDifficultyInBreathingSymptom;
     TextView tvSneezingSymptoms;

     String fullname, date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        tvFullname = findViewById(R.id.tvFullname);
        tvDate = findViewById(R.id.tvDate);
        tvFeverSymptom = findViewById(R.id.tvFever);
        tvCoughSymptom = findViewById(R.id.tvCoughSymptom);
        tvDifficultyInBreathingSymptom = findViewById(R.id.tvDifficultyInBreathingSymptom);
        tvSneezingSymptoms = findViewById(R.id.tvSneezingSymptoms);

        fullname = getIntent().getStringExtra("full_name");
        date = getIntent().getStringExtra("date");

        tvFullname.setText(fullname);
        tvDate.setText(date);

    }
}
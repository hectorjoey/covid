package mobile.fhi360.covid_19selfscreeningtool.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import mobile.fhi360.covid_19selfscreeningtool.R;

public class LowRiskActivity extends AppCompatActivity {
    TextView mTollFree;
    Button mLowRiskCloseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_low_risk);

        mTollFree = findViewById(R.id.toll_free);
        mLowRiskCloseBtn = findViewById(R.id.lowRiskCloseBtn);

        mLowRiskCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LowRiskActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //invoking call from the activity
        mTollFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //method for invoking call
                String phone = mTollFree.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);

            }
        });
    }
}

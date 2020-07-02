package mobile.fhi360.covid_19selfscreeningtool;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ikhiloyaimokhai.nigeriastatesandlgas.Nigeria;

import java.lang.reflect.Field;
import java.util.List;

import mobile.fhi360.covid_19selfscreeningtool.Api.RetrofitClient;
import mobile.fhi360.covid_19selfscreeningtool.activities.RegisterActivity;
import mobile.fhi360.covid_19selfscreeningtool.model.Supervisor;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUserActivity extends AppCompatActivity {

    private static final int SPINNER_HEIGHT = 500;
    EditText mFirstname, mLastname, mPhone, mEmail, mPassword, mDesignation, mSupervisorId;
    Button supRegister;
    //    TextView mLoadUser;
    Spinner mUserTypeSpinner, mStateSpinner,
            mGender, mAgeSpinner;
    private ProgressDialog loadingBar;
    private String mState, mLga, mAge;
    private List<String> states;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mStateSpinner = findViewById(R.id.stateSpinner);
        mFirstname = findViewById(R.id.Supfirstname);
        mPhone = findViewById(R.id.Supphone);
        mEmail = findViewById(R.id.Supemail);
        mPassword = findViewById(R.id.Suppassword);
        mUserTypeSpinner = findViewById(R.id.SupUsertype_spinner);
        mStateSpinner = findViewById(R.id.SupstateSpinner);
        supRegister = findViewById(R.id.supRegister);
        mDesignation = findViewById(R.id.Supdesignation);
        loadingBar = new ProgressDialog(this);

        resizeSpinner(mStateSpinner, SPINNER_HEIGHT);
//        resizeSpinner(mLgaSpinner, SPINNER_HEIGHT);

        states = Nigeria.getStates();

        //call to method that'll set up state and lga spinner
        setupSpinners();

     supRegister.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final String email = mEmail.getText().toString().trim();
            final String password = mPassword.getText().toString().trim();
            final String fullname = mFirstname.getText().toString().trim();
            final String phone = mPhone.getText().toString().trim();
            final String userType = String.valueOf(mUserTypeSpinner.getSelectedItem());
            final String state = String.valueOf(mStateSpinner.getSelectedItem());
            final String designation = mDesignation.getText().toString().trim();

            if (TextUtils.isEmpty(fullname)) {
                mFirstname.setError("first name is required!");
                mFirstname.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(phone)) {
                mPhone.setError("Phone number is required!");
                mPhone.requestFocus();
            }

            if (TextUtils.isEmpty(email)) {
                mEmail.setError("Email is required!");
                mEmail.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                mEmail.setError("Enter a valid email!");
                mEmail.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                mPassword.setError("Password is required!");
                mPassword.requestFocus();
                return;
            }
            if (password.length() < 6) {
                mPassword.setError("Password length is too short!");
                mPassword.requestFocus();
            }
            if (TextUtils.isEmpty(designation)) {
                mDesignation.setError("Designation is required!");
            }

            registerSup(fullname, phone, email, password,
                    userType, state, designation);
        }

    });

    // Create an ArrayAdapter using the string array and a default spinner layout
    ArrayAdapter<CharSequence> userTypeAdapter = ArrayAdapter.createFromResource(this,
            R.array.userType_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        mUserTypeSpinner.setAdapter(userTypeAdapter);
}

    /**
     * Method to set up the spinners
     */
    public void setupSpinners() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        //populates the quantity spinner ArrayList

        ArrayAdapter<String> statesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, states);

        // Specify dropdown layout style - simple list view with 1 item per line
        statesAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        // Apply the adapter to the spinner
        statesAdapter.notifyDataSetChanged();
        mStateSpinner.setAdapter(statesAdapter);

        // Set the integer mSelected to the constant values
        mStateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mState = (String) parent.getItemAtPosition(position);
//                setUpStatesSpinner(position);
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Unknown
            }
        });
    }

    private void resizeSpinner(Spinner spinner, int height) {
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            //Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinner);

            //set popupWindow height to height
            popupWindow.setHeight(height);

        } catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    public void registerSup(String fullname, String phone, String email, String password,
                             String userType, String state, String designation) {

        //do registration API call
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up Supervisor...");
        progressDialog.show();

        Call<Supervisor> call = RetrofitClient
                .getInstance()
                .getApi()
                .createSupervisor(fullname, phone, email, password, userType, state, designation);
        call.enqueue(new Callback<Supervisor>() {
            @Override
            public void onResponse(Call<Supervisor> call, Response<Supervisor> response) {
//                clearFields();
                Toast.makeText(RegisterUserActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                System.out.println("Responding ::: " + response);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Supervisor> call, Throwable t) {
                Toast.makeText(RegisterUserActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("throwing " + t);
            }
        });
    }
}
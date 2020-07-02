//package mobile.fhi360.covid_19selfscreeningtool.activities;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.ProgressDialog;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import com.ikhiloyaimokhai.nigeriastatesandlgas.Nigeria;
//
//import java.lang.reflect.Field;
//import java.util.List;
//
//import mobile.fhi360.covid_19selfscreeningtool.Api.RetrofitClient;
//import mobile.fhi360.covid_19selfscreeningtool.R;
//import mobile.fhi360.covid_19selfscreeningtool.model.Users;
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class UpdateUserActivity extends AppCompatActivity {
//    private static final int SPINNER_HEIGHT = 500;
//    EditText mEmail;
//    EditText editSupervisorId;
//    Button mButtonUpdate, mCancel;
//    Spinner mUpdateEnableSpinner, mStateSpinner, mAgeSpinner, mUserTypeSpinner, mGender;
//    EditText mUserId;
//    EditText mFirstname, mLastname, mPhone, mPassword, mDesignation;
//    private List<String> states;
//    private String mState;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_update_user);
//        editSupervisorId = findViewById(R.id.input_supervisor);
//        mStateSpinner = findViewById(R.id.stateSpinner);
//        mFirstname = findViewById(R.id.firstname);
//        mLastname = findViewById(R.id.lastname);
//        mPhone = findViewById(R.id.phone);
//        mEmail = findViewById(R.id.email);
//        mPassword = findViewById(R.id.password);
//        mUserTypeSpinner = findViewById(R.id.userType_spinner);
//        mStateSpinner = findViewById(R.id.stateSpinner);
//        mButtonUpdate = findViewById(R.id.btn_update);
//        mGender = findViewById(R.id.genderSpinner);
//        mAgeSpinner = findViewById(R.id.age_spinner);
//        mDesignation = findViewById(R.id.designation);
////        mUserId = findViewById(R.id.input_id);
//        mCancel = findViewById(R.id.btn_cancel);
//        mUpdateEnableSpinner = findViewById(R.id.updateEnableSpinner);
//
//
//        resizeSpinner(mStateSpinner, SPINNER_HEIGHT);
////        resizeSpinner(mLgaSpinner, SPINNER_HEIGHT);
//
//        states = Nigeria.getStates();
//
//        //call to method that'll set up state and lga spinner
//        setupSpinners();
//
//        mButtonUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String email = mEmail.getText().toString().trim();
//                final String password = mPassword.getText().toString().trim();
//                final String supervisorId = editSupervisorId.getText().toString().trim();
//                final String enabled = mUpdateEnableSpinner.getSelectedItem().toString();
//                final String firstname = mFirstname.getText().toString().trim();
//                final String lastname = mLastname.getText().toString().trim();
//                final String age = String.valueOf(mAgeSpinner.getSelectedItem());
//                final String phone = mPhone.getText().toString().trim();
//                final String userType = String.valueOf(mUserTypeSpinner.getSelectedItem());
//                final String gender = String.valueOf(mGender.getSelectedItem());
//                final String state = String.valueOf(mStateSpinner.getSelectedItem());
//                final String designation = mDesignation.getText().toString().trim();
//
//                if (email.isEmpty() || supervisorId.isEmpty()) {
//                    Toast.makeText(UpdateUserActivity.this, "Enter fields", Toast.LENGTH_SHORT).show();
//                }
//                updateUser(firstname,
//                        lastname,
//                        phone,
//                        email,
//                        age, password, userType, gender, state, designation, supervisorId, enabled);
//
//            }
//        });
//        // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> userTypeAdapter = ArrayAdapter.createFromResource(this,
//                R.array.userType_array, android.R.layout.simple_spinner_item);
//// Specify the layout to use when the list of choices appears
//        userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//// Apply the adapter to the spinner
//        mUserTypeSpinner.setAdapter(userTypeAdapter);
//
//
//        // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,
//                R.array.gender_array, android.R.layout.simple_spinner_item);
//// Specify the layout to use when the list of choices appears
//        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//// Apply the adapter to the spinner
//        mGender.setAdapter(genderAdapter);
//
//        // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> ageAdapter = ArrayAdapter.createFromResource(this,
//                R.array.age_array, android.R.layout.simple_spinner_item);
//// Specify the layout to use when the list of choices appears
//        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//// Apply the adapter to the spinner
//        mAgeSpinner.setAdapter(ageAdapter);
//
//
//        // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> enableAdapter = ArrayAdapter.createFromResource(this,
//                R.array.enable_spinner, android.R.layout.simple_spinner_item);
//// Specify the layout to use when the list of choices appears
//        enableAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//// Apply the adapter to the spinner
//        mUpdateEnableSpinner.setAdapter(enableAdapter);
//    }
//
//    public void setupSpinners() {
//        // Create adapter for spinner. The list options are from the String array it will use
//        // the spinner will use the default layout
//        //populates the quantity spinner ArrayList
//
//        ArrayAdapter<String> statesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, states);
//
//        // Specify dropdown layout style - simple list view with 1 item per line
//        statesAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//        // Apply the adapter to the spinner
//        statesAdapter.notifyDataSetChanged();
//        mStateSpinner.setAdapter(statesAdapter);
//
//        // Set the integer mSelected to the constant values
//        mStateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                mState = (String) parent.getItemAtPosition(position);
////                setUpStatesSpinner(position);
//            }
//
//            // Because AdapterView is an abstract class, onNothingSelected must be defined
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // Unknown
//            }
//        });
//    }
//
//    private void resizeSpinner(Spinner spinner, int height) {
//        try {
//            Field popup = Spinner.class.getDeclaredField("mPopup");
//            popup.setAccessible(true);
//
//            //Get private mPopup member variable and try cast to ListPopupWindow
//            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinner);
//
//            //set popupWindow height to height
//            popupWindow.setHeight(height);
//
//        } catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void updateUser(String firstname, String lastname, String age, String phone, String email, String password,
//                           String userType, String gender, String state, String designation, String supervisorId, String enabled) {
//        //do registration API call
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Updating Up...");
//        progressDialog.show();
//
//        Call<ResponseBody> call = RetrofitClient
//                .getInstance()
//                .getApi()
//                .updateUser(firstname, lastname, age, phone, email, password,
//                        userType, gender, state, designation, supervisorId, enabled);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                progressDialog.dismiss();
//                Toast.makeText(UpdateUserActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(UpdateUserActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                System.out.println("throwing " + t);
//            }
//        });
//    }
//}
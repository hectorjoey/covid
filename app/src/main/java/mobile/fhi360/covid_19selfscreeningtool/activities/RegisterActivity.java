package mobile.fhi360.covid_19selfscreeningtool.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import androidx.appcompat.app.AppCompatActivity;

import com.ikhiloyaimokhai.nigeriastatesandlgas.Nigeria;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import mobile.fhi360.covid_19selfscreeningtool.Api.RetrofitClient;
import mobile.fhi360.covid_19selfscreeningtool.R;
import mobile.fhi360.covid_19selfscreeningtool.adapter.SupervisorAdapter;
import mobile.fhi360.covid_19selfscreeningtool.model.Supervisor;
import mobile.fhi360.covid_19selfscreeningtool.model.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {
    private static final int SPINNER_HEIGHT = 500;
    EditText mFirstname, mLastname, mPhone, mEmail, mPassword, mDesignation;
    Button mButtonRegister;
    Spinner mUserTypeSpinner, mStateSpinner,
            mGender, mAgeSpinner, mSupervisorSpinner;
    private ProgressDialog loadingBar;
    private String mState, mLga, mAge;
    private List<String> states;
    ProgressDialog loading;
    String URL="https://new-covid.herokuapp.com/api/v1/supervisors/";
    ArrayList<String> Supervisors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mStateSpinner = findViewById(R.id.stateSpinner);
        mFirstname = findViewById(R.id.firstname);
        mLastname = findViewById(R.id.lastname);
        mPhone = findViewById(R.id.phone);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mUserTypeSpinner = findViewById(R.id.userType_spinner);
        mStateSpinner = findViewById(R.id.stateSpinner);
        mButtonRegister = findViewById(R.id.btnRegister);
        mGender = findViewById(R.id.genderSpinner);
        mAgeSpinner = findViewById(R.id.age_spinner);
        mDesignation = findViewById(R.id.designation);
        loadingBar = new ProgressDialog(this);

        Supervisors =new ArrayList<>();
        mSupervisorSpinner= findViewById(R.id.supervisorSpinner);
        loadSpinnerData(URL);
        resizeSpinner(mStateSpinner, SPINNER_HEIGHT);
//        resizeSpinner(mLgaSpinner, SPINNER_HEIGHT);

        states = Nigeria.getStates();

        //call to method that'll set up state and lga spinner
        setupSpinners();
        initSupervisorSpinner();
        mSupervisorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String supervisorList=   mSupervisorSpinner.getItemAtPosition(mSupervisorSpinner.getSelectedItemPosition()).toString();
                Toast.makeText(RegisterActivity.this,supervisorList,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();
                final String firstname = mFirstname.getText().toString().trim();
                final String lastname = mLastname.getText().toString().trim();
                final String age = String.valueOf(mAgeSpinner.getSelectedItem());
                final String phone = mPhone.getText().toString().trim();
                final String userType = String.valueOf(mUserTypeSpinner.getSelectedItem());
                final String gender = String.valueOf(mGender.getSelectedItem());
                final String state = String.valueOf(mStateSpinner.getSelectedItem());
                final String designation = mDesignation.getText().toString().trim();
                final String supervisor = mSupervisorSpinner.getSelectedItem().toString();

                if (TextUtils.isEmpty(firstname)) {
                    mFirstname.setError("first name is required!");
                    mFirstname.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(lastname)) {
                    mLastname.setError("last name is required!");
                    mLastname.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    mPhone.setError("Phone number is required!");
                    mPhone.requestFocus();
                }

//                if (phone.length() != 11 && !(phone.startsWith("070"))
//                        || !(phone.startsWith("080"))
//                        || !(phone.startsWith("090"))
//                        || !(phone.startsWith("081"))) {
//                    mPhone.setError("Enter a valid phone number!");
//                    mPhone.requestFocus();
//                    return;
//                }

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

                registerUser(firstname, lastname, age, phone, email, password,
                        userType, gender, state, designation, supervisor);
            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> userTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.userType_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        mUserTypeSpinner.setAdapter(userTypeAdapter);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        mGender.setAdapter(genderAdapter);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> ageAdapter = ArrayAdapter.createFromResource(this,
                R.array.age_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        mAgeSpinner.setAdapter(ageAdapter);
    }

    private void loadSpinnerData(String url) {
        Call<Supervisor>call = RetrofitClient
                .getInstance()
                .getApi()
                .getSupervisors();
        call.enqueue(new Callback<Supervisor>() {
            @Override
            public void onResponse(Call<Supervisor> call, Response<Supervisor> response) {

                try{
                    JSONObject jsonObject=new JSONObject ().getJSONObject(String.valueOf(response));

                    if(jsonObject.getInt("success")==1){
                        JSONArray jsonArray=jsonObject.getJSONArray("Name");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String sup=jsonObject1.getString("Supervisor");
                            Supervisors.add(sup);
                        }
                    }
                    mSupervisorSpinner.setAdapter(new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, Supervisors));
                }catch (JSONException e){e.printStackTrace();
                }
//                      // Set hasil result json ke dalam adapter spinner
//                      ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterActivity.this,
//                              android.R.layout.simple_spinner_item, mSupSpinner);
//                      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                      mSupervisorSpinner.setAdapter(adapter);
            }
//              }else {
////                  loading.dismiss();
//                  Toast.makeText(RegisterActivity.this, "Gagal mengambil data dosen", Toast.LENGTH_SHORT).show();

            @Override
            public void onFailure(Call<Supervisor> call, Throwable t) {

            }
        });

    }

    public void initSupervisorSpinner() {

       Call<Supervisor>call = RetrofitClient
               .getInstance()
               .getApi()
               .getSupervisors();
       call.enqueue(new Callback<Supervisor>() {
           @Override
           public void onResponse(Call<Supervisor> call, Response<Supervisor> response) {
               try{
                   JSONObject jsonObject=new JSONObject(String.valueOf(response));
                   if(jsonObject.getInt("success")==1){
                       JSONArray jsonArray=jsonObject.getJSONArray("Name");
                       for(int i=0;i<jsonArray.length();i++){
                           JSONObject jsonObject1=jsonArray.getJSONObject(i);
                           String sup=jsonObject1.getString("Supervisor");
                           Supervisors.add(sup);
                       }
                   }
                   mSupervisorSpinner.setAdapter(new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, Supervisors));
               }catch (JSONException e){e.printStackTrace();
           }
//                      // Set hasil result json ke dalam adapter spinner
//                      ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterActivity.this,
//                              android.R.layout.simple_spinner_item, mSupSpinner);
//                      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                      mSupervisorSpinner.setAdapter(adapter);
                  }
//              }else {
////                  loading.dismiss();
//                  Toast.makeText(RegisterActivity.this, "Gagal mengambil data dosen", Toast.LENGTH_SHORT).show();

           @Override
           public void onFailure(Call<Supervisor> call, Throwable t) {

           }
       });
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

    public void registerUser(String firstname, String lastname, String age, String phone, String email, String password,
                             String userType, String gender, String state, String designation, String supervisor) {

        //do registration API call
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();

        Call<Users> call = RetrofitClient
                .getInstance()
                .getApi()
                .createUser(firstname, lastname, age, phone, email, password, userType, gender, state, designation, supervisor);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                Toast.makeText(RegisterActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                System.out.println("Responding ::: " + response);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("throwing " + t);
            }
        });
    }

    public void clearFields() {
        mEmail.setText("");
        mPassword.setText("");
        mFirstname.setText("");
        mLastname.setText("");
        mPhone.setText("");
        mDesignation.setText("");
//        mSupervisorId.setText("");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }
}
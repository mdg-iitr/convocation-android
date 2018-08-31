package ac.in.iitr.mdg.convocation;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import ac.in.iitr.mdg.convocation.network.ApiClient;
import ac.in.iitr.mdg.convocation.network.ConvoApi;
import ac.in.iitr.mdg.convocation.responsemodels.CommonResponse;
import ac.in.iitr.mdg.convocation.responsemodels.OauthResponse;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivity extends AppCompatActivity {

    private Spinner sizeOfDress;
    private Button peopleDetailNone, peopleDetailOne, peopleDetailTwo, vehicleDetailNone, vehicleDetailOne, submit;
    private CheckBox checkBox_terms;

    private int numPeopleSelected = 0;
    private boolean isFourWheeler = false;

    private EditText basicName, basicEnrNo, basicEmail, basicPhNo, basicTransactionID;
    private EditText additionalAddress;

    private Intent intentFilterIntent;
    private Uri intentUri;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar_register);
        toolbar.setTitle("");
        TextView textView = findViewById(R.id.registration);
        textView.setText("Registration");
        setSupportActionBar(toolbar);

        basicName = findViewById(R.id.register_name);
        basicName.setEnabled(false);
        basicEnrNo = findViewById(R.id.register_enrollment);
        basicEnrNo.setEnabled(false);
        basicEmail = findViewById(R.id.register_email);
        basicPhNo = findViewById(R.id.register_phone);
        basicTransactionID = findViewById(R.id.register_bankTransactionId);

        additionalAddress = findViewById(R.id.register_address);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setIndeterminateDrawable(ContextCompat.getDrawable(this, R.drawable.progress));

        ImageView imageView = findViewById(R.id.back_button_register);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sizeOfDress = findViewById(R.id.register_sizeOfDress);
        String[] items = new String[]{"Select size of dress"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        sizeOfDress.setAdapter(adapter);

        peopleDetailNone = findViewById(R.id.register_numberOfPeople_none);
        peopleDetailOne = findViewById(R.id.register_numberOfPeople_one);
        peopleDetailTwo = findViewById(R.id.register_numberOfPeople_two);
        vehicleDetailNone = findViewById(R.id.register_numberOfVehicle_none);
        vehicleDetailOne = findViewById(R.id.register_numberOfVehicle_one);
        submit = findViewById(R.id.register_submit);
        checkBox_terms = findViewById(R.id.register_terms);

        submit.setClickable(false);
        checkBox_terms.setChecked(false);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateData()) {
                    progressDialog.setMessage("Registering");
                    progressDialog.show();
                    ApiClient.getAuthClient(RegisterActivity.this)
                            .create(ConvoApi.class)
                            .registerUser(
                                    basicEnrNo.getText().toString(),
                                    basicPhNo.getText().toString(),
                                    basicName.getText().toString(),
                                    basicEmail.getText().toString(),
                                    numPeopleSelected,
                                    (isFourWheeler) ? 1 : 0,
                                    basicTransactionID.getText().toString())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<CommonResponse>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(CommonResponse commonResponse) {
                                    progressDialog.dismiss();
                                    if (!commonResponse.getStatus().equals("error")) {
                                        updateIsRegisteredInSharedPrefs(true);
                                        Toast.makeText(RegisterActivity.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        if (commonResponse.getMessage().toLowerCase().contains("already")) {
                                            updateIsRegisteredInSharedPrefs(true);
                                            Toast.makeText(RegisterActivity.this, "Already registered", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "Failed to register, Please Try Again", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(RegisterActivity.this, "Failed to register, Please Try Again", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }

                                @Override
                                public void onComplete() {
                                    progressDialog.dismiss();
                                }
                            });
                }
            }
        });

        peopleDetailNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peopleDetailNone.setBackground(getResources().getDrawable(R.drawable.gradient));
                peopleDetailNone.setTextColor(getResources().getColor(R.color.white));
                peopleDetailOne.setBackgroundColor(getResources().getColor(R.color.white));
                peopleDetailOne.setTextColor(getResources().getColor(R.color.textColor));
                peopleDetailTwo.setBackgroundColor(getResources().getColor(R.color.white));
                peopleDetailTwo.setTextColor(getResources().getColor(R.color.textColor));

                numPeopleSelected = 0;
            }
        });

        peopleDetailNone.performClick();

        peopleDetailOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peopleDetailOne.setBackground(getResources().getDrawable(R.drawable.gradient));
                peopleDetailOne.setTextColor(getResources().getColor(R.color.white));
                peopleDetailNone.setBackgroundColor(getResources().getColor(R.color.white));
                peopleDetailNone.setTextColor(getResources().getColor(R.color.textColor));
                peopleDetailTwo.setBackgroundColor(getResources().getColor(R.color.white));
                peopleDetailTwo.setTextColor(getResources().getColor(R.color.textColor));

                numPeopleSelected = 1;
            }
        });

        peopleDetailTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peopleDetailTwo.setBackground(getResources().getDrawable(R.drawable.gradient));
                peopleDetailTwo.setTextColor(getResources().getColor(R.color.white));
                peopleDetailOne.setBackgroundColor(getResources().getColor(R.color.white));
                peopleDetailOne.setTextColor(getResources().getColor(R.color.textColor));
                peopleDetailNone.setBackgroundColor(getResources().getColor(R.color.white));
                peopleDetailNone.setTextColor(getResources().getColor(R.color.textColor));

                numPeopleSelected = 2;
            }
        });

        vehicleDetailNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehicleDetailNone.setBackground(getResources().getDrawable(R.drawable.gradient));
                vehicleDetailNone.setTextColor(getResources().getColor(R.color.white));
                vehicleDetailOne.setBackgroundColor(getResources().getColor(R.color.white));
                vehicleDetailOne.setTextColor(getResources().getColor(R.color.textColor));

                isFourWheeler = false;
            }
        });
        vehicleDetailNone.performClick();

        vehicleDetailOne.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                vehicleDetailOne.setBackground(getResources().getDrawable(R.drawable.gradient));
                vehicleDetailOne.setTextColor(getResources().getColor(R.color.white));
                vehicleDetailNone.setBackgroundColor(getResources().getColor(R.color.white));
                vehicleDetailNone.setTextColor(getResources().getColor(R.color.textColor));

                isFourWheeler = true;
            }
        });

        checkBox_terms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("NewApi")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    submit.setClickable(true);
                    submit.setTextColor(getResources().getColor(R.color.white));
                } else {
                    submit.setClickable(false);
                    submit.setTextColor(getResources().getColor(R.color.whiteTransparent));
                }
            }
        });

        getIntentFilter();
    }

    private void getIntentFilter() {

        //get intent filter for a param(channeliRedirectUriCode) and send request to verify whether user in a new user or an already existing user
        intentFilterIntent = getIntent();
        if (intentFilterIntent != null) {
            intentUri = intentFilterIntent.getData();
            String code;
            if (intentUri != null) {
                code = intentUri.getQueryParameter("code");
                Log.d("URI_CODE", code);
                progressDialog.setMessage("Retrieving Data");
                progressDialog.show();
                ApiClient.getClientWithoutAuth(this).create(ConvoApi.class)
                        .getOauth(code)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<OauthResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(OauthResponse oauthResponse) {

                                progressDialog.dismiss();

                                if (oauthResponse.getToken() != null && !oauthResponse.getToken().isEmpty()) {
                                    updateTokenInSharedPrefs(oauthResponse.getToken());
                                }

                                if (oauthResponse.isRegistered()) {
                                    updateIsRegisteredInSharedPrefs(true);
                                    Toast.makeText(RegisterActivity.this, "You have already registered successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                    return;
                                }

                                basicName.setText(oauthResponse.getName());
                                basicEnrNo.setText(oauthResponse.getEnrollmentNumber());
                                basicEmail.setText(oauthResponse.getEmail());
                                basicPhNo.setText(oauthResponse.getPhoneNumber());
                            }

                            @Override
                            public void onError(Throwable e) {
                                progressDialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "Failed to fetch details, please try again", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onComplete() {
                                progressDialog.dismiss();
                            }
                        });
            }
        }

    }

    private void updateTokenInSharedPrefs(String token) {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putString(getString(R.string.token_identifier), token);
        sharedPrefEditor.commit();
    }

    private void updateIsRegisteredInSharedPrefs(boolean isRegistered) {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putBoolean(getString(R.string.is_registered_identifier), isRegistered);
        sharedPrefEditor.commit();
    }

    private boolean validateData() {
        boolean isValid = true;

        if (basicPhNo.getText().toString().isEmpty() || !Patterns.PHONE.matcher(basicPhNo.getText().toString()).matches()) {
            isValid = false;
            basicPhNo.setError("Please enter phone number");
        }

        if (basicEmail.getText().toString().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(basicEmail.getText().toString()).matches()) {
            isValid = false;
            basicEmail.setError("Please enter valid email address");
        }

        if (basicTransactionID.getText().toString().isEmpty()) {
            isValid = false;
            basicTransactionID.setError("Transaction Id can't be empty");
        }

        return isValid;
    }
}

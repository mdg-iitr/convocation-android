package ac.in.iitr.mdg.convocation.views;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;


public class RegisterActivity extends AppCompatActivity {
    Spinner sizeOfDress;
    Button peopleDetailNone,peopleDetailOne,peopleDetailTwo,vehicleDetailNone,vehicleDetailOne,submit;
    CheckBox checkBox_terms;

    private Intent intentFilterIntent;
    private Uri intentUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
                Toast.makeText(this, "Machaya : " + code, Toast.LENGTH_SHORT).show();
            }
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_register);
        toolbar.setTitle("");
        TextView textView = findViewById(R.id.registration);
        textView.setText("Registration");
        setSupportActionBar(toolbar);

        ImageButton imageButton = (ImageButton) findViewById(R.id.back_button_register);
        imageButton.setOnClickListener(new View.OnClickListener() {
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

        peopleDetailNone.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                peopleDetailNone.setBackground(getResources().getDrawable(R.drawable.gradient));
                peopleDetailNone.setTextColor(getResources().getColor(R.color.white));
                peopleDetailOne.setBackgroundColor(getResources().getColor(R.color.white));
                peopleDetailOne.setTextColor(getResources().getColor(R.color.textColor));
                peopleDetailTwo.setBackgroundColor(getResources().getColor(R.color.white));
                peopleDetailTwo.setTextColor(getResources().getColor(R.color.textColor));

            }
        });

        peopleDetailNone.performClick();

        peopleDetailOne.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                peopleDetailOne.setBackground(getResources().getDrawable(R.drawable.gradient));
                peopleDetailOne.setTextColor(getResources().getColor(R.color.white));
                peopleDetailNone.setBackgroundColor(getResources().getColor(R.color.white));
                peopleDetailNone.setTextColor(getResources().getColor(R.color.textColor));
                peopleDetailTwo.setBackgroundColor(getResources().getColor(R.color.white));
                peopleDetailTwo.setTextColor(getResources().getColor(R.color.textColor));

            }
        });

        peopleDetailTwo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                peopleDetailTwo.setBackground(getResources().getDrawable(R.drawable.gradient));
                peopleDetailTwo.setTextColor(getResources().getColor(R.color.white));
                peopleDetailOne.setBackgroundColor(getResources().getColor(R.color.white));
                peopleDetailOne.setTextColor(getResources().getColor(R.color.textColor));
                peopleDetailNone.setBackgroundColor(getResources().getColor(R.color.white));
                peopleDetailNone.setTextColor(getResources().getColor(R.color.textColor));

            }
        });

        vehicleDetailNone.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                vehicleDetailNone.setBackground(getResources().getDrawable(R.drawable.gradient));
                vehicleDetailNone.setTextColor(getResources().getColor(R.color.white));
                vehicleDetailOne.setBackgroundColor(getResources().getColor(R.color.white));
                vehicleDetailOne.setTextColor(getResources().getColor(R.color.textColor));

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

            }
        });

        checkBox_terms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("NewApi")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    submit.setClickable(true);
                    submit.setTextColor(getResources().getColor(R.color.white));
                }
                else {
                    submit.setClickable(false);
                    submit.setTextColor(getResources().getColor(R.color.whiteTransparent));
                }
            }
        });



    }

}

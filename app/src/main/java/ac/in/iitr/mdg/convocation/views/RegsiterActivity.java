package ac.in.iitr.mdg.convocation.views;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class RegsiterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        final EditText name = (EditText) findViewById(R.id.register_name);
        final EditText enrollment_no = (EditText) findViewById(R.id.register_enno);
        final EditText email = (EditText) findViewById(R.id.register_email);
        final EditText ph_no = (EditText) findViewById(R.id.register_phno);
        final EditText bank_trans = (EditText) findViewById(R.id.register_banktransaction);
        final EditText addr = (EditText) findViewById(R.id.register_addr);
        final EditText mob_no = (EditText) findViewById(R.id.register_mobno);
        final EditText email_id = (EditText) findViewById(R.id.register_emailid);

        String people;
        String vehicle;

        final Button people_none = (Button) findViewById(R.id.people_none);
        Button people_1 = (Button) findViewById(R.id.people_1);
        Button people_2 = (Button) findViewById(R.id.people_2);

        final Integer[] people_pressed = {null};

        people_none.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people_pressed[0] = 0;
            }
        });

        people_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people_pressed[0] = 1;
            }
        });

        people_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people_pressed[0] = 2;
            }
        });

        Button vehicle_none = (Button) findViewById(R.id.vehicle_none);
        Button vehicle_1 = (Button) findViewById(R.id.vehicle_1);

        final Integer[] vehicle_pressed = {null};

        vehicle_none.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehicle_pressed[0] = 0;
            }
        });

        vehicle_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehicle_pressed[0] = 1;
            }
        });


        final CheckBox terms1 = (CheckBox) findViewById(R.id.checkbox_terms1);
        final CheckBox terms2 = (CheckBox) findViewById(R.id.checkbox_terms2);

        Button submit = findViewById(R.id.register_submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = null;
                    user = new User(name.getText().toString(),enrollment_no.getText().toString(),email.getText().toString(),ph_no.getText().toString(),
                            bank_trans.getText().toString(),addr.getText().toString(),mob_no.getText().toString(),email_id.getText().toString(),people_pressed[0],vehicle_pressed[0],terms1.isChecked(),terms2.isChecked());
                if(conditionCheck(user)){


                }else{
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(RegsiterActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(RegsiterActivity.this);
                    }
                    builder.setTitle("Error")
                            .setMessage("Please check all the details, form is incomplete or wrongly filled!")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });


    }

    public boolean conditionCheck(User user){
        return user.getName() != null && user.getEnrollmentNo() != null && user.getPhone_no() != null && user.getAddress() != null && user.getEmail() != null && user.getBank_trans_id() != null
                && user.getAddress() != null && user.getPeople_details() != null && user.getVehicle_details() != null && user.getTerms1() && user.getTerms2();
    }
}

package ac.in.iitr.mdg.convocation.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ac.in.iitr.mdg.convocation.views.R;

public class ChiefGuestDescription extends AppCompatActivity {

            private TextView headNameText,headDesigText,headDataText;

            @Override
    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.guest_desc);
        
                        String headingName = getIntent().getExtras().getString("headingName");
                String headingDesig = getIntent().getExtras().getString("headingDesig")                                                                                                             ;
                String body = getIntent().getExtras().getString("data");
        
                headNameText = (TextView) findViewById(R.id.guest_desc_head_name);
                headDesigText = (TextView) findViewById(R.id.guest_desc_head_desig);
                headDataText = (TextView) findViewById(R.id.guest_desc_body);
        
                
                headNameText.setText(headingName);
                headDesigText.setText(headingDesig);
                headDataText.setText(body);
        
                
                            }

        }


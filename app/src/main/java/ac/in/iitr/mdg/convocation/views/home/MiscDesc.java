package ac.in.iitr.mdg.convocation.views.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ac.in.iitr.mdg.convocation.views.R;

public class MiscDesc extends AppCompatActivity {

            private TextView headText,headDataText;

            @Override
    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.guest_desc);
        
                        String headingName = getIntent().getExtras().getString("headingName");
                String body = getIntent().getExtras().getString("data");
        
                        headText = (TextView) findViewById(R.id.misc_desc_head_name);
                headDataText = (TextView) findViewById(R.id.misc_desc_body);
        
                
                                headText.setText(headingName);
                headDataText.setText(body);
        
                
                            }

        }


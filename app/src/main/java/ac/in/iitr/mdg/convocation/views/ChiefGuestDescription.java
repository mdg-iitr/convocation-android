package ac.in.iitr.mdg.convocation.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ChiefGuestDescription extends AppCompatActivity {

    public static final String INTEN_EXTRA_GUEST_NAME = "guest_name";

    private TextView headNameText, headDesigText, headDataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_desc);

        String headingName = getIntent().getExtras().getString("headingName");
        String headingDesig = getIntent().getExtras().getString("headingDesig");
        String body = getIntent().getExtras().getString("data");

        headNameText = findViewById(R.id.guest_desc_head_name);
        headDesigText = findViewById(R.id.guest_desc_head_desig);
        headDataText = findViewById(R.id.guest_desc_body);

        headNameText.setText(headingName);
        headDesigText.setText(headingDesig);
        headDataText.setText(body);
    }

}


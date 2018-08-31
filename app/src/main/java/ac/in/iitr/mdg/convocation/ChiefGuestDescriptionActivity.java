package ac.in.iitr.mdg.convocation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ChiefGuestDescriptionActivity extends AppCompatActivity {

    public static final String INTENT_EXTRA_GUEST_NAME = "guest_name";
    public static final String INTENT_EXTRA_GUEST_DESIGNATION = "guest_designation";
    public static final String INTENT_EXTRA_GUEST_BIO = "guest_bio";

    private TextView headNameText, headDesigText, headDataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_desc);

        String headingName = getIntent().getExtras().getString(INTENT_EXTRA_GUEST_NAME);
        String headingDesig = getIntent().getExtras().getString(INTENT_EXTRA_GUEST_DESIGNATION);
        String body = getIntent().getExtras().getString(INTENT_EXTRA_GUEST_BIO);

        headNameText = findViewById(R.id.guest_desc_head_name);
        headDesigText = findViewById(R.id.guest_desc_head_desig);
        headDataText = findViewById(R.id.guest_desc_body);

        headNameText.setText(headingName);
        headDesigText.setText(headingDesig);
        headDataText.setText(body);
    }

}


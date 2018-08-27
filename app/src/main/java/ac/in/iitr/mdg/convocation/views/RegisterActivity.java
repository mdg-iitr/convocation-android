package ac.in.iitr.mdg.convocation.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

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
    }

}

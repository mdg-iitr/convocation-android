package ac.in.iitr.mdg.convocation.views;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        Drawable drawable = getResources().getDrawable(R.drawable.ic_navigate_before_black_24dp);
        ImageButton imageButton = (ImageButton) findViewById(R.id.back_button);
        imageButton.setImageDrawable(drawable);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}

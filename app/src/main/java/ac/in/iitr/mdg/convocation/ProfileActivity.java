package ac.in.iitr.mdg.convocation;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ac.in.iitr.mdg.convocation.network.ApiClient;
import ac.in.iitr.mdg.convocation.network.ConvoApi;
import ac.in.iitr.mdg.convocation.responsemodels.UserResponseModel;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private ScrollView profileWrapper;

    private ImageView profileImage;
    private TextView nameText, emailText, phoneText, enrText, depText, transactionIdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        profileImage = findViewById(R.id.profile_image);
        nameText = findViewById(R.id.profile_name);
        emailText = findViewById(R.id.profile_email);
        phoneText = findViewById(R.id.profile_contact_no);
        enrText = findViewById(R.id.enrolment_no_display);
        depText = findViewById(R.id.dept_display);
        transactionIdText = findViewById(R.id.transaction_id);

        progressBar = findViewById(R.id.profile_progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        profileWrapper = findViewById(R.id.profile_wrapper);
        profileWrapper.setVisibility(View.GONE);

        ApiClient.getAuthClient(this)
                .create(ConvoApi.class)
                .getProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponseModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserResponseModel userResponseModel) {
                        progressBar.setVisibility(View.GONE);
                        profileWrapper.setVisibility(View.VISIBLE);
                        updateDetails(userResponseModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBar.setVisibility(View.GONE);
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void updateDetails(UserResponseModel user) {
        if (user.getProfileImage() == null || user.getProfileImage().isEmpty()) {
            Picasso.get().load("null").placeholder(R.drawable.grey_card).into(profileImage);
        } else {
            Picasso.get().load(user.getProfileImage()).placeholder(R.drawable.grey_card).into(profileImage);
        }

        nameText.setText(user.getName());
        emailText.setText(user.getEmail());
        phoneText.setText(user.getPhoneNumber());

        enrText.setText(user.getEnrollmentNumber());
        depText.setText(user.getBranch());

//        transactionIdText.setText(user.getTransactionId());

    }

}

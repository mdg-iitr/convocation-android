package ac.in.iitr.mdg.convocation.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import ac.in.iitr.mdg.convocation.R;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class ApiClient {

    public static final String BASE_URL = "http://51.15.135.192/";

    private static Retrofit retrofit, retrofitAuth;
    private static SharedPreferences sharedPref;
    private static OkHttpClient mClient;

    public static Retrofit getClientWithoutAuth(Context context) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(new OkHttpClient.Builder()
                            .readTimeout(100, TimeUnit.SECONDS)
                            .connectTimeout(100, TimeUnit.SECONDS)
                            .build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getAuthClient(final Context context) {
        if (retrofitAuth == null) {
            retrofitAuth = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getClient(context))
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitAuth;
    }

    private static OkHttpClient getClient(final Context context) {
        if (mClient == null) {
            sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), MODE_PRIVATE);
            OkHttpClient.Builder ok_builder = new OkHttpClient.Builder();
            ok_builder.readTimeout(100, TimeUnit.SECONDS);
            ok_builder.connectTimeout(100, TimeUnit.SECONDS);
            ok_builder.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    String token_identifier = context.getString(R.string.token_identifier);
                    Request.Builder newRequest = request.newBuilder()
                            .addHeader(
                                    "Authorization", "Token " +
                                            sharedPref.getString(token_identifier, "")
                            ).addHeader(
                                    "UA", "an/" + getAndroidVersion() + " " + getAppVersion(context)
                            );
                    return chain.proceed(newRequest.build());
                }
            });
            mClient = ok_builder.build();
        }
        return mClient;
    }

    private static String getAppVersion(Context context) {
        String version;
        try {
            PackageInfo pInfo = null;
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(context, "Failed to get app version", Toast.LENGTH_SHORT).show();
            version = "0.0";
        }
        return version;
    }

    private static String getAndroidVersion() {
        StringBuilder builder = new StringBuilder();
        builder.append(Build.VERSION.RELEASE);

        Field[] fields = Build.VERSION_CODES.class.getFields();
        for (Field field : fields) {
            int fieldValue = -1;

            try {
                fieldValue = field.getInt(new Object());
            } catch (IllegalArgumentException | IllegalAccessException | NullPointerException e) {
                e.printStackTrace();
            }

            if (fieldValue == Build.VERSION.SDK_INT) {
                return builder.toString();
            }
        }
        return "0.0";
    }

}

package ac.in.iitr.mdg.convocation.network;

import java.util.ArrayList;

import ac.in.iitr.mdg.convocation.responsemodels.CommonResponse;
import ac.in.iitr.mdg.convocation.responsemodels.OauthResponse;
import ac.in.iitr.mdg.convocation.responsemodels.ScheduleModel;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ConvoApi {

    @GET("schedule/")
    Observable<ArrayList<ScheduleModel>> getSchedule();

    @GET("oauth/")
    Observable<OauthResponse> getOauth(@Query("code") String code);

    @FormUrlEncoded
    @POST("user/register/")
    Observable<CommonResponse> registerUser(@Field("enr_no") String enrollmentNumber,
                                            @Field("ph_no") String phoneNumber,
                                            @Field("name") String name,
                                            @Field("email") String email,
                                            @Field("adults") int adults,
                                            @Field("four_wheeler") int isFourWheeler,
                                            @Field("transaction_id") String transactionId);

}

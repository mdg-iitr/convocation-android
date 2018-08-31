package ac.in.iitr.mdg.convocation.network;

import java.util.ArrayList;

import ac.in.iitr.mdg.convocation.responsemodels.OauthResponse;
import ac.in.iitr.mdg.convocation.responsemodels.ScheduleModel;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ConvoApi {

    @GET("schedule/")
    Observable<ArrayList<ScheduleModel>> getSchedule();

    @GET("oauth/")
    Observable<OauthResponse> getOauth(@Query("code") String code);

}

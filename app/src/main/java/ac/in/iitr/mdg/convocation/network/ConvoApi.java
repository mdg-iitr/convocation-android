package ac.in.iitr.mdg.convocation.network;

import java.util.ArrayList;

import ac.in.iitr.mdg.convocation.responsemodels.ScheduleModel;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ConvoApi {

    @GET("schedule/")
    Observable<ArrayList<ScheduleModel>> getSchedule();

}

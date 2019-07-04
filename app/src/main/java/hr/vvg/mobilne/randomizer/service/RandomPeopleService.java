package hr.vvg.mobilne.randomizer.service;

import hr.vvg.mobilne.randomizer.model.People;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomPeopleService {

    @GET("?results=20")
    Call<People> getPeoples();

}

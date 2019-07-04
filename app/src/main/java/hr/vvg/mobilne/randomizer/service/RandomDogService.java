package hr.vvg.mobilne.randomizer.service;

import hr.vvg.mobilne.randomizer.model.Dog;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomDogService {

    @GET("woof.json")
    public Call<Dog> getDog();

}

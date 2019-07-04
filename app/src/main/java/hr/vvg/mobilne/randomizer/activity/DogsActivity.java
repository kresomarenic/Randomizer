package hr.vvg.mobilne.randomizer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import hr.vvg.mobilne.randomizer.R;
import hr.vvg.mobilne.randomizer.service.RandomDogService;
import hr.vvg.mobilne.randomizer.model.Dog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DogsActivity extends AppCompatActivity {

    private ImageView dogImageView;
    private Button buttonRefresh;
    private Button buttonPeoples;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);
        setupView();
    }

    private void setupView() {

        context = DogsActivity.this;

        dogImageView = findViewById(R.id.dogImageView);
        buttonRefresh = findViewById(R.id.buttonRefreshDog);
        buttonRefresh.setOnClickListener(e -> loadPicture());

        buttonPeoples = findViewById(R.id.buttonPeoples);
        buttonPeoples.setOnClickListener(e -> loadPeoples());

        loadPicture();
    }

    private void loadPicture() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://random.dog/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RandomDogService dogService = retrofit.create(RandomDogService.class);

        Call<Dog> call = dogService.getDog();

        call.enqueue(new Callback<Dog>() {
            @Override
            public void onResponse(Call<Dog> call, Response<Dog> response) {
                Dog dog = response.body();
                if (dog.getUrl().toLowerCase().contains(".jpg")) {
                    Glide.with(context)
                            .load(dog.getUrl())
                            .into(dogImageView);
                } else {
                    loadPicture();
                }
            }

            @Override
            public void onFailure(Call<Dog> call, Throwable t) {
                Toast.makeText(DogsActivity.this, "Oops! Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadPeoples() {
        Intent intent = new Intent(DogsActivity.this, PeopleActivity.class);
        startActivity(intent);
    }
}

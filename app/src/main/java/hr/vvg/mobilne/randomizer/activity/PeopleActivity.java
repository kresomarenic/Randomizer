package hr.vvg.mobilne.randomizer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import hr.vvg.mobilne.randomizer.adapter.PeopleAdapter;
import hr.vvg.mobilne.randomizer.R;
import hr.vvg.mobilne.randomizer.service.RandomPeopleService;
import hr.vvg.mobilne.randomizer.model.People;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeopleActivity extends AppCompatActivity {


    private RecyclerView peopleRecyclerView;
    private PeopleAdapter peopleAdapter;
    private People peoples = null;

    private Button buttonDogs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        setupView();
    }

    private void setupView() {

        peopleRecyclerView = findViewById(R.id.peopleList);
        peopleRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonDogs = findViewById(R.id.buttonDogs);
        buttonDogs.setOnClickListener(v -> loadDogs());

        loadList();
    }

    private void loadList() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RandomPeopleService request = retrofit.create(RandomPeopleService.class);
        Call<People> call1 = request.getPeoples();
        call1.enqueue(new Callback<People>() {
            @Override
            public void onResponse(Call<People> call, Response<People> response) {

                if (response.isSuccessful() && response.body() != null) {
                    peoples = response.body();
                    peopleAdapter = new PeopleAdapter(peoples, PeopleActivity.this);
                    peopleRecyclerView.setAdapter(peopleAdapter);
                }
            }

            @Override
            public void onFailure(Call<People> call, Throwable t) {
                Toast.makeText(PeopleActivity.this, "Oops! Something went wrong!", Toast.LENGTH_SHORT).show();
                System.out.println(t);
            }

        });
    }

    private void loadDogs() {
        Intent intent = new Intent(PeopleActivity.this, DogsActivity.class);
        startActivity(intent);
    }
}

package com.omni.dependencyinjection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.omni.dependencyinjection.adapter.RandomUserAdapter;
import com.omni.dependencyinjection.component.DaggerRandomUserComponent;
import com.omni.dependencyinjection.component.RandomUserComponent;
import com.omni.dependencyinjection.interfaces.RandomUsersApi;
import com.omni.dependencyinjection.module.ContextModule;
import com.squareup.picasso.Picasso;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    RecyclerView recyclerView;
    RandomUserAdapter mAdapter;
    RandomUsersApi randomUsersApi;
    Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        RandomUserComponent daggerRandomUserComponent   = DaggerRandomUserComponent.builder().contextModule(new ContextModule(this)).build();
        randomUsersApi = daggerRandomUserComponent.getRandomUserApi();
        picasso = daggerRandomUserComponent.getPicasso();

    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

//    private void populateUsers() {
//        Call<RandomUsers> randomUsersCall = randomUsersApi.getRandomUsers(10);
//        randomUsersCall.enqueue(new Callback<RandomUsers>() {
//            @Override
//            public void onResponse(Call<RandomUsers> call, @NonNull Response<RandomUsers> response) {
//                if(response.isSuccessful()) {
//                    mAdapter = new RandomUserAdapter();
//                    mAdapter.setItems(response.body().getResults());
//                    recyclerView.setAdapter(mAdapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RandomUsers> call, Throwable t) {
//                Timber.i(t.getMessage());
//            }
//        });
//    }




}

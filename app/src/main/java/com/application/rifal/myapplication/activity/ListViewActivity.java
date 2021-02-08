package com.application.rifal.myapplication.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.rifal.myapplication.adapter.AdapterOnItemClickListener;
import com.application.rifal.myapplication.adapter.ListAdapter;
import com.application.rifal.myapplication.R;
import com.application.rifal.myapplication.apiHelper.BaseApiService;
import com.application.rifal.myapplication.apiHelper.UtilsApi;
import com.application.rifal.myapplication.model.ModuleItem;
import com.application.rifal.myapplication.model.ModuleResponse;


import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewActivity extends AppCompatActivity {

    BaseApiService mApiService;
    String cat;

    ListAdapter List;
    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        mApiService = UtilsApi.getAPIService();

        cat = getIntent().getStringExtra("cat");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        showNews();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ListViewActivity.this);
        rv = findViewById(R.id.rvNews);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(mLayoutManager);
        layoutManager = new LinearLayoutManager(ListViewActivity.this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
    }

    private void showNews(){

        mApiService.showNews("6890e8dccee8436b97db7982af889fac",cat).enqueue(new Callback<ModuleResponse>()
        {
            @Override
            public void onResponse(@NonNull Call<ModuleResponse> call, @NonNull Response<ModuleResponse> response) {
                if (response.isSuccessful()) {
                    java.util.List<ModuleItem> dataItemList = response.body().getModuleDetail();
                    List = new ListAdapter(ListViewActivity.this, dataItemList, genProductAdapterListener());
                    rv.setAdapter(List);
                    List.notifyDataSetChanged();
                    Log.d("success", "onResponse: ");

                    Toast.makeText(ListViewActivity.this, " OK. The request was executed successfully.", Toast.LENGTH_SHORT).show();

                }else if (response.code() == 400) {
                    Toast.makeText(ListViewActivity.this, "Bad Request. The request was unacceptable, often due to a missing or misconfigured parameter.", Toast.LENGTH_SHORT).show();
                    try {
                        Log.v("Error code 400",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (response.code() == 401) {
                    Toast.makeText(ListViewActivity.this, "Unauthorized. Your API key was missing from the request, or wasn't correct.", Toast.LENGTH_SHORT).show();
                    try {
                        Log.v("Error code 401",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (response.code() == 429) {
                    Toast.makeText(ListViewActivity.this, " Too Many Requests. You made too many requests within a window of time and have been rate limited. Back off for a while.", Toast.LENGTH_SHORT).show();
                    try {
                        Log.v("Error code 429",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (response.code() == 500) {
                    Toast.makeText(ListViewActivity.this, " Server Error. Something went wrong on our side.", Toast.LENGTH_SHORT).show();
                    try {
                        Log.v("Error code 500",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ModuleResponse> call, Throwable t) {
                Log.d("timeout connection", "onFailure: ");
                Toast.makeText(ListViewActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private AdapterOnItemClickListener genProductAdapterListener() {
        return new AdapterOnItemClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        };
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

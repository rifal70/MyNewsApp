package com.application.rifal.myapplication.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.application.rifal.myapplication.R;
import com.application.rifal.myapplication.activity.MainActivity;
import com.application.rifal.myapplication.adapter.AdapterOnItemClickListener;
import com.application.rifal.myapplication.adapter.ListAdapter;
import com.application.rifal.myapplication.apiHelper.BaseApiService;
import com.application.rifal.myapplication.apiHelper.UtilsApi;
import com.application.rifal.myapplication.model.ModuleItem;
import com.application.rifal.myapplication.model.ModuleResponse;

import java.io.IOException;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class General extends Fragment implements SearchView.OnQueryTextListener {
    Context context;
    ListAdapter List;
    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ModuleItem> arraylist = new ArrayList<ModuleItem>();
    LinearLayout  linearLayoutMyCour;
    SwipeRefreshLayout mySwipeCourse;
    BaseApiService mApiService;
    SearchView editsearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_general, container, false);

        return inflater.inflate(R.layout.fragment_general, null);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //NGEHAPUS STATS BAR
//            getActivity().getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
                    , WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorAccent));

            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            // It never gets here //NGEHAPUS STATS BAR
//            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv = view.findViewById(R.id.rvGeneral);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(mLayoutManager);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        linearLayoutMyCour = view.findViewById(R.id.llGeneral);

        mApiService = UtilsApi.getAPIService();
        context = getActivity();

        //search
        // Pass results to ListViewAdapter Class
        List = new ListAdapter(context, arraylist,genProductAdapterListener());

        // Binds the Adapter to the ListView
        rv.setAdapter(List);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) view.findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //Log.i(tag, "keyCode: " + keyCode);
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    //Log.i(tag, "onKey Back listener is working!!!");
                    //getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                    return true;
                }
                return false;
            }
        });

        showNews();

        super.onViewCreated(view, savedInstanceState);


    }

    private void showNews(){
        mApiService.showNewsGeneral("6890e8dccee8436b97db7982af889fac").enqueue(new Callback<ModuleResponse>()
        {
            @Override
            public void onResponse(@NonNull Call<ModuleResponse> call, @NonNull Response<ModuleResponse> response) {
                if (response.isSuccessful()) {
                    java.util.List<ModuleItem> dataItemList = response.body().getModuleDetail();
                    List = new ListAdapter(context, dataItemList, genProductAdapterListener());
                    rv.setAdapter(List);
                    List.notifyDataSetChanged();
                    Log.d("success", "onResponse: ");

                    Toast.makeText(context, " OK. The request was executed successfully.", Toast.LENGTH_SHORT).show();

                }else if (response.code() == 400) {
                    Toast.makeText(context, "Bad Request. The request was unacceptable, often due to a missing or misconfigured parameter.", Toast.LENGTH_SHORT).show();
                    try {
                        Log.v("Error code 400",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (response.code() == 401) {
                    Toast.makeText(context, "Unauthorized. Your API key was missing from the request, or wasn't correct.", Toast.LENGTH_SHORT).show();
                    try {
                        Log.v("Error code 401",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (response.code() == 429) {
                    Toast.makeText(context, " Too Many Requests. You made too many requests within a window of time and have been rate limited. Back off for a while.", Toast.LENGTH_SHORT).show();
                    try {
                        Log.v("Error code 429",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (response.code() == 500) {
                    Toast.makeText(context, " Server Error. Something went wrong on our side.", Toast.LENGTH_SHORT).show();
                    try {
                        Log.v("Error code 500",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ModuleResponse> call, Throwable t) {
                Log.d("fail", "onFailure: ");
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
    public void onResume(){
        super.onResume();
        // put your code here...

    }

    @Override
    public boolean onQueryTextSubmit(String newText) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        List.filter(text);
        return false;
    }
}

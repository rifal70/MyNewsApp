package com.application.rifal.myapplication.adapter;


import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.application.rifal.myapplication.R;
import com.application.rifal.myapplication.activity.WebViewActivity;
import com.application.rifal.myapplication.adapter.AdapterOnItemClickListener;
import com.application.rifal.myapplication.apiHelper.BaseApiService;
import com.application.rifal.myapplication.apiHelper.UtilsApi;
import com.application.rifal.myapplication.model.ModuleItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static android.service.controls.ControlsProviderService.TAG;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.OnlineHolder> {
    List<ModuleItem> dataItemList;
    private ArrayList<ModuleItem> arraylist;
    Context mContext;
    RecyclerView mRecyclerView;
    AdapterView.OnItemClickListener listener;
    private ListAdapter.OnItemClicked onClick;
    private AdapterOnItemClickListener mListener;
    BaseApiService mApiService;
    ProgressDialog loading;
    Context context;
    String passCek,stats;

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        dataItemList.clear();
        if (charText.length() == 0) {
            dataItemList.addAll(arraylist);
        } else {
            for (ModuleItem wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    dataItemList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    public interface OnItemClicked {
        void onItemClick(int position);
    }

    //Context context, AdapterOnItemClickListener listener, AdapterOnTextChangeListener listener2, List<AndroidItem> orderList

    public ListAdapter(Context context, List<ModuleItem> dataItem, AdapterOnItemClickListener listener) {
        this.mContext = context;
        dataItemList = dataItem;
        this.mListener = listener;
        this.arraylist = new ArrayList<ModuleItem>();
        this.arraylist.addAll(dataItemList);
    }

    @Override
    public ListAdapter.OnlineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new ListAdapter.OnlineHolder(itemView, mListener);
    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ListAdapter.OnlineHolder holder, @SuppressLint("RecyclerView") final int position) {

        final ModuleItem item = dataItemList.get(position);


        holder.name.setText(item.getName());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getItemId(position);

                Toast.makeText(mContext, item.getUrl(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("urlnya", item.getUrl());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
                Log.d(TAG, "onClick: " + item.getUrl());

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class OnlineHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ImageView imageView;
        LinearLayout linearLayout;
        TextView title;
        TextView writer;
        TextView name,price2;
        CardView card;



        private OnlineHolder(View itemView, AdapterOnItemClickListener listener) {

            super(itemView);
            mApiService = UtilsApi.getAPIService();
            mListener = listener;
            context = itemView.getContext();
//            linearLayout.setOnClickListener(this);

            name = itemView.findViewById(R.id.tv_judulberita);

            card = itemView.findViewById(R.id.card0);

        }

        @Override
        public void onClick(View itemView) {
            mListener.onClick(itemView, getAdapterPosition());
        }
    }

    public List<ModuleItem> getItems() {
        return dataItemList;
    }

    public ModuleItem getItem(int position) {
        return dataItemList.get(position);
    }
    public interface AdapterOnItemClickListener {
        void onClick(View var1, int var2);
    }
}

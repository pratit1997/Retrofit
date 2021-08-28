package com.example.retrofit.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.retrofit.Model.Data;
import com.example.retrofit.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{
    private List<Data> DataList;
    private Context context;


    public CustomAdapter(List<Data> dataList, Context context) {
        DataList = dataList;
        this.context = context;
    }

    public void SetdataList(List<Data> DataList) {
        this.DataList = DataList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_single_item,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        try {
            holder.tv_albumId.setText("Album Id: "+String.valueOf(DataList.get(position).getAlbumId()));
            holder.tv_id.setText("Id: "+String.valueOf(DataList.get(position).getId()));
            holder.tv_title.setText("Title: "+DataList.get(position).getTitle().toString());


            String url=DataList.get(position).getUrl();
            String ThumbnailUrl=DataList.get(position).getThumbnailUrl();
            Log.d(TAG, "onBindViewHolder: "+url);

            Glide.with(context).load(url).apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background)).into(holder.iv_url);
            Glide.with(context).load(ThumbnailUrl).apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background)).into(holder.iv_ThumbnailUrl);
         //  Glide.with(context).load(ThumbnailUrl).apply(RequestOptions.centerCropTransform()).into(holder.iv_ThumbnailUrl);
        }catch (Exception e){
            Toast.makeText(context,"exception "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        if(DataList != null){
            return DataList.size();
        }
        return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tv_albumId,tv_id,tv_title;
        ImageView iv_ThumbnailUrl,iv_url;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_albumId = itemView.findViewById(R.id.tv_albumId);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_title = itemView.findViewById(R.id.tv_title);
            iv_url = itemView.findViewById(R.id.iv_url);
            iv_ThumbnailUrl = itemView.findViewById(R.id.iv_ThumbnailUrl);
        }

    }
}

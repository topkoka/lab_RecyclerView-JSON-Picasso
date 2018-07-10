package com.aditep.labrecyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aditep.labrecyclerview.JSONData;
import com.aditep.labrecyclerview.R;
import com.squareup.picasso.Picasso;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder> {
    private OnItemClickListener onItemClickListener;
    private List<JSONData> feedItemList;
    private Context mContext;

    public MyRecyclerViewAdapter(Context context, List<JSONData> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // การ Bind ข้อมูลผ่าน Layout ของ CardView ด้วย ViewHolder
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    // Bind ViewHolder
    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        final JSONData jsonData = feedItemList.get(i);

        //Render image using Picasso library
        if (!TextUtils.isEmpty(jsonData.getThumbnail())) {
            Picasso.get().load(jsonData.getThumbnail())
                    .error(R.drawable.load)
                    .placeholder(R.drawable.load)
                    .into(customViewHolder.imageView);
        }

        //Setting text view title
        customViewHolder.textView.setText(Html.fromHtml(jsonData.getTitle()));
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(jsonData);

            }
        };
        customViewHolder.imageView.setOnClickListener(listener);
        customViewHolder.textView.setOnClickListener(listener);

    }
    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    //เกิดการรอข้อมูลรูปภาพเราจะไปโหลดไฟล์รูปภาพ placeholder มาเก็บไว้ ด้วย Library ของ Picasso เก็บค่าจำนวนของชุดข้อมูลผ่าน Method นี้:
    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    // ไล่ Render ข้อมูลลง Widget ใน Method นี้:
    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.title);
        }
    }
}
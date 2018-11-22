package com.quanlyvatnuoi.admin.quanlyvatnuoi.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.quanlyvatnuoi.admin.quanlyvatnuoi.ItemClickListener;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class HolderVatNuoi extends RecyclerView.ViewHolder implements View.OnClickListener{

    public CircleImageView imgAvatar;
    public TextView tvName;
    public TextView tvSoluong;
    private ItemClickListener itemClickListener;
    public ImageView img_delete;


    public HolderVatNuoi(View convertView) {
        super(convertView);
        imgAvatar = convertView.findViewById(R.id.img_avatar);
        tvName = convertView.findViewById(R.id.tv_name);
        tvSoluong = convertView.findViewById(R.id.tv_soluong);
        img_delete = convertView.findViewById(R.id.img_delete);
        convertView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), true);
    }
}

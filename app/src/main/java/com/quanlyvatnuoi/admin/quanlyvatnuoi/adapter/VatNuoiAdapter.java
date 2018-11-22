package com.quanlyvatnuoi.admin.quanlyvatnuoi.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.ui.ChiTietVatNuoiActivity;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.ItemClickListener;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.R;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.dao.VatNuoiDAO;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.holder.HolderVatNuoi;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.model.VatNuoi;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.sqlite.DatabaseHelper;

import java.util.List;


public class VatNuoiAdapter extends RecyclerView.Adapter<HolderVatNuoi> {

    private Context context;
    private List<VatNuoi> vatNuoiList;
    private DatabaseHelper databaseHelper;
    private VatNuoiDAO dao;

    public VatNuoiAdapter(Context context, List<VatNuoi> vatNuoiList) {
        this.context = context;
        this.vatNuoiList = vatNuoiList;
    }

    @NonNull
    @Override
    public HolderVatNuoi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vatnuoi, parent, false);
        return new HolderVatNuoi(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderVatNuoi holder, final int position) {
        final VatNuoi vatNuoi = vatNuoiList.get(position);
        holder.tvName.setText("Tên vật nuôi: " +vatNuoiList.get(position).getTenvatnuoi());
        holder.tvSoluong.setText("Số lượng: " +vatNuoiList.get(position).getSoluong());
//        Bitmap bitmap = BitmapFactory.decodeByteArray(vatNuoi.getImage(), 0, vatNuoi.getImage().length);
//        holder.imgAvatar.setImageBitmap(bitmap);
        Glide.with(context).load(vatNuoi.getImage()).into(holder.imgAvatar);

        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa");
                builder.setMessage("Bạn có muốn xóa không?");
                builder.setCancelable(true);
                builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseHelper = new DatabaseHelper(context);
                        dao = new VatNuoiDAO(databaseHelper);
                        dao.delete(vatNuoiList.get(position));
                        vatNuoiList.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isOnCLick) {
                Intent intent = new Intent(context, ChiTietVatNuoiActivity.class);
                intent.putExtra("name", vatNuoiList.get(position).getTenvatnuoi());
                intent.putExtra("suckhoe", vatNuoiList.get(position).getTinhtrangsuckhoe());
                intent.putExtra("typefood", vatNuoiList.get(position).getLoaithucan());
                intent.putExtra("time", vatNuoiList.get(position).getThoigiannuoi());
                String sl = String.valueOf(vatNuoiList.get(position).getSoluong());
                intent.putExtra("soluong", sl);
                intent.putExtra("image", vatNuoi.getImage());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (vatNuoiList==null) return 0;
        return vatNuoiList.size();
    }
}

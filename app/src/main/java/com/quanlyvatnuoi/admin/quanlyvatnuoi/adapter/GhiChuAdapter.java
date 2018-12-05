package com.quanlyvatnuoi.admin.quanlyvatnuoi.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quanlyvatnuoi.admin.quanlyvatnuoi.R;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.dao.GhiChuDAO;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.model.Ghichu;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.sqlite.DatabaseHelper;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GhiChuAdapter extends BaseAdapter {

    private Context context;
    private List<Ghichu> ghichuList;
    private DatabaseHelper databaseHelper;
    private GhiChuDAO dao;

    public GhiChuAdapter(Context context, List<Ghichu> ghichuList) {
        this.context = context;
        this.ghichuList = ghichuList;
    }

    @Override
    public int getCount() {
        if (ghichuList == null) return 0;
        return ghichuList.size();
    }

    @Override
    public Object getItem(int position) {
        return ghichuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_ghichu, parent, false);
            viewHolder.imgAvatar = convertView.findViewById(R.id.img_avatar);
            viewHolder.tvtitle = convertView.findViewById(R.id.tvtitle);
            viewHolder.imgDelete = convertView.findViewById(R.id.img_delete);
            viewHolder.tvSTT = convertView.findViewById(R.id.tv_stt);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvtitle.setText("Tiêu đề: " + ghichuList.get(position).getTieude());
        viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
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
                        dao = new GhiChuDAO(databaseHelper);
                        dao.deleteNote(ghichuList.get(position));
                        ghichuList.remove(position);
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
        return convertView;
    }

    class ViewHolder {
        CircleImageView imgAvatar;
        TextView tvtitle;
        ImageView imgDelete;
        TextView tvSTT;
    }
}

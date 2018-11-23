package com.quanlyvatnuoi.admin.quanlyvatnuoi.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quanlyvatnuoi.admin.quanlyvatnuoi.R;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.dao.UserDao;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.dao.VatNuoiDAO;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.model.User;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.sqlite.DatabaseHelper;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private List<User> userList;
    private Context context;
    private DatabaseHelper databaseHelper;
    private Dialog dialog;
    private UserDao dao;

    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        databaseHelper = new DatabaseHelper(context);
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
            viewHolder.tvname = convertView.findViewById(R.id.tvname);
            viewHolder.tvphone = convertView.findViewById(R.id.tvphone);
            viewHolder.img_delete = convertView.findViewById(R.id.img_delete);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvphone.setText("Phone:  " + userList.get(position).getPhone());
        viewHolder.tvname.setText("UserName:  " + userList.get(position).getUserName());
        viewHolder.img_delete.setOnClickListener(new View.OnClickListener() {
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
                        dao = new UserDao(databaseHelper);
                        dao.deleteUser(userList.get(position));
                        userList.remove(position);
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
        private TextView tvname;
        private TextView tvphone;
        private ImageView img_delete;
    }
}

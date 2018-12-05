package com.quanlyvatnuoi.admin.quanlyvatnuoi.ui;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.quanlyvatnuoi.admin.quanlyvatnuoi.R;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.adapter.UserAdapter;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.dao.UserDao;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.model.User;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.sqlite.DatabaseHelper;

import java.util.List;

public class ListUserActivity extends AppCompatActivity {

    private ListView lvUser;
    private DatabaseHelper databaseHelper;
    private UserAdapter adapter;
    private List<User> userList;
    private UserDao dao;
    private FloatingActionButton floatingActionButton;
    private EditText edtUpdateUsername;
    private EditText edtUpdatePass;
    private EditText edtUpdateName;
    private EditText edtUpdatePhone;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        initView();
        setTitle("Danh Sách Người Dùng");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        databaseHelper = new DatabaseHelper(this);
        dao = new UserDao(databaseHelper);

        userList = dao.getAllUsers();
        adapter = new UserAdapter(userList, this);
        lvUser.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ThemNguoiDungActivity.class));
            }
        });

        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(Color
                .parseColor("#2175D4")));

        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Dialog dialog = new Dialog(ListUserActivity.this);
                dialog.setContentView(R.layout.dialog_updateuser);
                dialog.setTitle("Cập Nhập Thông Tin");
                dialog.show();
                edtUpdateUsername = dialog.findViewById(R.id.edt_updateUsername);
                edtUpdatePass = dialog.findViewById(R.id.edt_updatePass);
                edtUpdateName = dialog.findViewById(R.id.edt_updateName);
                edtUpdatePhone = dialog.findViewById(R.id.edt_updatePhone);
                btnUpdate = dialog.findViewById(R.id.btn_update);
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        User user = new User();
                        user.setUserName(edtUpdateUsername.getText().toString());
                        user.setPassword(edtUpdatePass.getText().toString());
                        user.setName(edtUpdateName.getText().toString());
                        user.setPhone(edtUpdatePhone.getText().toString());
                        dao.updateUser(user);
                        Toast.makeText(ListUserActivity.this, "Cập nhập thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        userList.clear();
                        userList.addAll(dao.getAllUsers());
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void initView() {
        lvUser = findViewById(R.id.lv_user);
        floatingActionButton = findViewById(R.id.floatingActionButton);
    }
}

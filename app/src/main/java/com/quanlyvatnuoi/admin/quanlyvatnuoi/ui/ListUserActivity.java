package com.quanlyvatnuoi.admin.quanlyvatnuoi.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog dialog = new Dialog(ListUserActivity.this);
                dialog.setContentView(R.layout.dialog_updateuser);
                dialog.setTitle("Cập Nhập Thông Tin");
                dialog.show();
            }
        });
    }

    private void initView() {
        lvUser = findViewById(R.id.lv_user);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
    }
}

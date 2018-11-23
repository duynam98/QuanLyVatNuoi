package com.quanlyvatnuoi.admin.quanlyvatnuoi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quanlyvatnuoi.admin.quanlyvatnuoi.R;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.dao.UserDao;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.model.User;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.sqlite.DatabaseHelper;

public class ThemNguoiDungActivity extends AppCompatActivity {

    private EditText edtAddUserName;
    private EditText edtAddPassWord;
    private EditText edtAddName;
    private EditText edtAddPhone;
    private Button btnAdduser;
    private Button btnListUser;
    private DatabaseHelper databaseHelper;
    private UserDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nguoi_dung);
        initView();
        setTitle("Thêm Người Dùng");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        databaseHelper = new DatabaseHelper(this);
        dao = new UserDao(databaseHelper);

        btnAdduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(edtAddUserName.getText().toString(), edtAddPassWord.getText().toString(), edtAddName.getText().toString(), edtAddPhone.getText().toString());
                dao.insertUser(user);
                Toast.makeText(ThemNguoiDungActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            }
        });

        btnListUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListUserActivity.class));
            }
        });
    }

    private void initView() {
        edtAddUserName = findViewById(R.id.edt_addUserName);
        edtAddPassWord = findViewById(R.id.edt_addPassWord);
        edtAddName = findViewById(R.id.edt_addName);
        edtAddPhone = findViewById(R.id.edt_addPhone);
        btnAdduser = findViewById(R.id.btn_adduser);
        btnListUser = findViewById(R.id.btn_listUser);
    }
}

package com.quanlyvatnuoi.admin.quanlyvatnuoi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.quanlyvatnuoi.admin.quanlyvatnuoi.R;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.dao.UserDao;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.model.User;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.sqlite.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPass;
    private Button btnLogin;
    private DatabaseHelper databaseHelper;
    private UserDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Đăng Nhập");
        initView();
        databaseHelper = new DatabaseHelper(this);
        dao = new UserDao(databaseHelper);

        User user = new User("admin", "admin", "", "");
        dao.insertUser(user);
        edtUsername.setText("admin");
        edtPass.setText("admin");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUsername.getText().toString();
                String password = edtPass.getText().toString();

                if(userName.trim().equalsIgnoreCase("")){
                    edtUsername.setError("Không được để trống");
                }
                if(password.trim().equalsIgnoreCase("")){
                    edtPass.setError("Không được để trống");
                }
                else{
                    User user = dao.getUser(userName);
                    if(user == null){
                        Toast.makeText(LoginActivity.this, "Tên đăng nhập không đúng", Toast.LENGTH_SHORT).show();
                    }else{
                        String pass = user.getPassword();
                        if(pass.equals(password)){
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    private void initView() {
        edtUsername = findViewById(R.id.edt_username);
        edtPass = findViewById(R.id.edt_pass);
        btnLogin = findViewById(R.id.btn_login);
    }
}

package com.quanlyvatnuoi.admin.quanlyvatnuoi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.quanlyvatnuoi.admin.quanlyvatnuoi.R;

public class HomeActivity extends AppCompatActivity {

    private ImageView imgQlvatnuoi;
    private ImageView imgThongtin;
    private ImageView imgNote;
    private ImageView imgUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        setTitle("HOME");

        imgQlvatnuoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ListVatNuoiActivity.class);
                startActivity(intent);
            }
        });

        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListUserActivity.class));
            }
        });

        imgNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListGhiChuActivity.class));
            }
        });

        imgThongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), WebActivity.class));
            }
        });
    }

    private void initView() {
        imgQlvatnuoi = (ImageView) findViewById(R.id.img_qlvatnuoi);
        imgThongtin = (ImageView) findViewById(R.id.img_thongtin);
        imgNote = (ImageView) findViewById(R.id.img_note);
        imgUser = (ImageView) findViewById(R.id.img_user);
    }
}

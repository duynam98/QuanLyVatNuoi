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
    private ImageView imgExit;

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
    }

    private void initView() {
        imgQlvatnuoi = (ImageView) findViewById(R.id.img_qlvatnuoi);
        imgThongtin = (ImageView) findViewById(R.id.img_thongtin);
        imgNote = (ImageView) findViewById(R.id.img_note);
        imgExit = (ImageView) findViewById(R.id.img_exit);
    }
}

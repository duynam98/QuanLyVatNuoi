package com.quanlyvatnuoi.admin.quanlyvatnuoi.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.quanlyvatnuoi.admin.quanlyvatnuoi.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChiTietVatNuoiActivity extends AppCompatActivity {


    private TextView tvName;
    private TextView tvTinhtrang;
    private ImageView imgBgr;
    private CircleImageView imgAvatar;
    private Button btnEdit;
    private TextView tvLoaithucan;
    private TextView tvThoigiannuoi;
    private TextView tvSoluong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_vat_nuoi);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        setTitle("Chi Tiết Vật Nuôi");

        final Intent intent = getIntent();
        tvName.setText(intent.getStringExtra("name"));
        tvTinhtrang.setText(intent.getStringExtra("suckhoe"));
        tvLoaithucan.setText(intent.getStringExtra("typefood"));
        tvThoigiannuoi.setText(intent.getStringExtra("time"));
        tvSoluong.setText(intent.getStringExtra("soluong"));
        imgAvatar.setImageURI(Uri.parse(intent.getStringExtra("image")));
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a  =new Intent(ChiTietVatNuoiActivity.this, SuaVatNuoiActivity.class);
                a.putExtra("name", tvName.getText());
                a.putExtra("suckhoe", tvTinhtrang.getText());
                a.putExtra("typefood", tvLoaithucan.getText());
                a.putExtra("time", tvThoigiannuoi.getText());
                a.putExtra("soluong", tvSoluong.getText());
                startActivity(a);
            }
        });
    }

    private void initView() {
        tvName = findViewById(R.id.tv_name);
        tvTinhtrang = findViewById(R.id.tv_tinhtrang);
        imgBgr = findViewById(R.id.img_bgr);
        imgAvatar = findViewById(R.id.img_avatar);
        btnEdit = findViewById(R.id.btn_edit);
        tvLoaithucan = findViewById(R.id.tv_loaithucan);
        tvThoigiannuoi = findViewById(R.id.tv_thoigiannuoi);
        tvSoluong = findViewById(R.id.tv_soluong);
    }
}

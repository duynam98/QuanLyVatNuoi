package com.quanlyvatnuoi.admin.quanlyvatnuoi.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.quanlyvatnuoi.admin.quanlyvatnuoi.ImageConverter;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.R;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.dao.VatNuoiDAO;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.model.VatNuoi;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.sqlite.DatabaseHelper;

import de.hdodenhof.circleimageview.CircleImageView;

public class SuaVatNuoiActivity extends AppCompatActivity {


    private CircleImageView imgAvatar;
    private TextInputEditText edtName;
    private TextInputEditText edtSuckhoe;
    private TextInputEditText edtTypefood;
    private TextInputEditText edtTime;
    private TextInputEditText edtSoluong;
    private Button btnSave;
    private Button btnCancel;
    private Button btnShow;
    private String image;
    private DatabaseHelper databaseHelper;
    private VatNuoiDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_vat_nuoi);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        setTitle("Sửa");
        databaseHelper = new DatabaseHelper(this);
        dao = new VatNuoiDAO(databaseHelper);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuaVatNuoiActivity.this, ListVatNuoiActivity.class);
                startActivity(intent);
            }
        });

        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.untitled1);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);
        imgAvatar.setImageBitmap(circularBitmap);

        Intent intent = getIntent();
        edtName.setText(intent.getStringExtra("name"));
        edtName.setEnabled(false);
        edtSuckhoe.setText(intent.getStringExtra("suckhoe"));
        edtTypefood.setText(intent.getStringExtra("typefood"));
        edtTime.setText(intent.getStringExtra("time"));
        edtSoluong.setText(intent.getStringExtra("soluong"));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VatNuoi vatNuoi = new VatNuoi();
                vatNuoi.setTenvatnuoi(edtName.getText().toString());
                vatNuoi.setImage(image);
                vatNuoi.setTinhtrangsuckhoe(edtSuckhoe.getText().toString());
                vatNuoi.setLoaithucan(edtTypefood.getText().toString());
                vatNuoi.setThoigiannuoi(edtTime.getText().toString());
                vatNuoi.setSoluong(Integer.parseInt(edtSoluong.getText().toString()));
                dao.updateVatNuoi(vatNuoi);
                Toast.makeText(SuaVatNuoiActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void initView() {
        imgAvatar = findViewById(R.id.img_avatar);
        edtName = findViewById(R.id.edt_name);
        edtSuckhoe = findViewById(R.id.edt_suckhoe);
        edtTypefood = findViewById(R.id.edt_typefood);
        edtTime = findViewById(R.id.edt_time);
        edtSoluong = findViewById(R.id.edt_soluong);
        btnSave = findViewById(R.id.btn_save);
        btnCancel = findViewById(R.id.btn_cancel);
        btnShow = findViewById(R.id.btn_show);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    Uri selectImage = data.getData();
                    image = String.valueOf(selectImage);
                    imgAvatar.setImageURI(selectImage);
                }
                break;
        }
    }
}

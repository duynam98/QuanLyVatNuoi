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
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.quanlyvatnuoi.admin.quanlyvatnuoi.ImageConverter;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.R;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.dao.VatNuoiDAO;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.model.VatNuoi;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.sqlite.DatabaseHelper;

public class ThemVatNuoiActivity extends AppCompatActivity {

    private ImageView imgAvatar;
    private TextInputEditText edtName;
    private TextInputEditText edtSuckhoe;
    private TextInputEditText edtTypefood;
    private TextInputEditText edtTime;
    private TextInputEditText edtSoluong;
    private Button btnAdd;
    private Button btnCancel;
    private DatabaseHelper databaseHelper;
    private VatNuoiDAO dao;
    private String image;
    private Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_vat_nuoi);
        setTitle("Thêm Vật Nuôi");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        databaseHelper = new DatabaseHelper(this);
        dao = new VatNuoiDAO(databaseHelper);

        edtSoluong.setImeOptions(EditorInfo.IME_ACTION_DONE);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemVatNuoiActivity.this, ListVatNuoiActivity.class);
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

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                VatNuoi vatNuoi = dao.getVatNuoi(name);
                String suckhoe = edtSuckhoe.getText().toString();
                String typefood = edtTypefood.getText().toString();
                String time = edtTime.getText().toString();
                String soluong = edtSoluong.getText().toString();
                if (name.isEmpty() || suckhoe.isEmpty() || typefood.isEmpty() || time.isEmpty() || soluong.isEmpty()) {
                    Toast.makeText(ThemVatNuoiActivity.this, "Không được để trống các trường thông tin", Toast.LENGTH_SHORT).show();
                } else if (vatNuoi==null){
                    themvatnuoi();
                }else {
                    Toast.makeText(ThemVatNuoiActivity.this, "Tên vật nuôi không được trùng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtName.setText("");
                edtSuckhoe.setText("");
                edtTypefood.setText("");
                edtTime.setText("");
                edtSoluong.setText("");
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
        btnAdd = findViewById(R.id.btn_add);
        btnCancel = findViewById(R.id.btn_cancel);
        btnShow = findViewById(R.id.btn_show);
    }


    private void themvatnuoi() {
        String name = edtName.getText().toString();
        String suckhoe = edtSuckhoe.getText().toString();
        String typefood = edtTypefood.getText().toString();
        String time = edtTime.getText().toString();
        int soluong = Integer.parseInt(edtSoluong.getText().toString());
        VatNuoi vatNuoi = new VatNuoi(name, suckhoe, typefood, time, soluong, image);
        dao.insertvatnuoi(vatNuoi);
        Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
    }

//    public byte[] ConverttoArrayByte(ImageView img)
//    {
//        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
//        Bitmap bitmap=bitmapDrawable.getBitmap();
//
//        ByteArrayOutputStream stream=new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        return stream.toByteArray();
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectImage = data.getData();
                    image = String.valueOf(selectImage);
                    imgAvatar.setImageURI(selectImage);
                    if (imgAvatar==null){
                        Toast.makeText(this, "Chọn ảnh", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}

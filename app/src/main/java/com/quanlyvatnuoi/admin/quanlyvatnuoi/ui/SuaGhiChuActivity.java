package com.quanlyvatnuoi.admin.quanlyvatnuoi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Toast;

import com.quanlyvatnuoi.admin.quanlyvatnuoi.R;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.dao.GhiChuDAO;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.model.Ghichu;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.sqlite.DatabaseHelper;

import de.hdodenhof.circleimageview.CircleImageView;

public class SuaGhiChuActivity extends AppCompatActivity {

    private CircleImageView imgAvatar;
    private TextInputEditText edtTitle;
    private TextInputEditText edtNoidung;
    private TextInputEditText edtTime;
    private Button btnAdd;
    private Button btnCancel;
    private Button btnShow;
    private DatabaseHelper databaseHelper;
    private GhiChuDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_ghi_chu);
        initView();
        databaseHelper = new DatabaseHelper(this);
        dao = new GhiChuDAO(databaseHelper);
        edtTime.setImeOptions(EditorInfo.IME_ACTION_DONE);

        final Intent intent = getIntent();
        edtTitle.setText(intent.getStringExtra("tieude"));
        edtNoidung.setText(intent.getStringExtra("noidung"));
        edtTime.setText(intent.getStringExtra("thoigian"));
        
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ghichu ghichu = new Ghichu();
                ghichu.setId(intent.getStringExtra("id"));
                ghichu.setTieude(edtTitle.getText().toString());
                ghichu.setNoidung(edtNoidung.getText().toString());
                ghichu.setThoigian(edtTime.getText().toString());
                dao.updateNote(ghichu);
                Toast.makeText(SuaGhiChuActivity.this, "Cập nhập ghi chú thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ListGhiChuActivity.class));
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListGhiChuActivity.class));
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
        edtTitle = findViewById(R.id.edt_title);
        edtNoidung = findViewById(R.id.edt_noidung);
        edtTime = findViewById(R.id.edt_time);
        btnAdd = findViewById(R.id.btn_add);
        btnCancel = findViewById(R.id.btn_cancel);
        btnShow = findViewById(R.id.btn_show);
    }
}

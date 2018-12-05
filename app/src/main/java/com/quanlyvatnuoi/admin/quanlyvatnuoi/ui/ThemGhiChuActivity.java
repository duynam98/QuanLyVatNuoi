package com.quanlyvatnuoi.admin.quanlyvatnuoi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Toast;

import com.quanlyvatnuoi.admin.quanlyvatnuoi.R;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.dao.GhiChuDAO;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.model.Ghichu;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.sqlite.DatabaseHelper;

public class ThemGhiChuActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_them_ghi_chu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        databaseHelper = new DatabaseHelper(this);
        dao = new GhiChuDAO(databaseHelper);
        edtTime.setImeOptions(EditorInfo.IME_ACTION_DONE);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTitle.getText().toString().isEmpty()){
                    Toast.makeText(ThemGhiChuActivity.this, "Không để trống tiêu đề", Toast.LENGTH_SHORT).show();
                }else {
                    Ghichu ghichu = new Ghichu(edtTitle.getText().toString(), edtNoidung.getText().toString(), edtTime.getText().toString());
                    dao.insertnote(ghichu);
                    Toast.makeText(ThemGhiChuActivity.this, "Thêm ghi chú thành công", Toast.LENGTH_SHORT).show();
                }
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
        edtTitle = findViewById(R.id.edt_title);
        edtNoidung = findViewById(R.id.edt_noidung);
        edtTime = findViewById(R.id.edt_time);
        btnAdd = findViewById(R.id.btn_add);
        btnCancel = findViewById(R.id.btn_cancel);
        btnShow = findViewById(R.id.btn_show);
    }
}

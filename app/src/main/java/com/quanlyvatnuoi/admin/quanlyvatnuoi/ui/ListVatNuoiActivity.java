package com.quanlyvatnuoi.admin.quanlyvatnuoi.ui;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.quanlyvatnuoi.admin.quanlyvatnuoi.R;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.adapter.VatNuoiAdapter;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.dao.VatNuoiDAO;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.model.VatNuoi;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.sqlite.DatabaseHelper;

import java.util.List;

public class ListVatNuoiActivity extends AppCompatActivity {

    private List<VatNuoi> vatNuoiList;
    private FloatingActionButton floatingActionButton;
    private VatNuoi vatNuoi;
    private VatNuoiAdapter adapter;
    private RecyclerView rvVatnuoi;
    private DatabaseHelper databaseHelper;
    private VatNuoiDAO dao;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_vat_nuoi);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        setTitle("Danh Sách Vật Nuôi");
        databaseHelper = new DatabaseHelper(this);
        dao = new VatNuoiDAO(databaseHelper);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ThemVatNuoiActivity.class));
            }
        });

        vatNuoiList = dao.getAllVatNuoi();
        adapter = new VatNuoiAdapter(this, vatNuoiList);
        layoutManager = new LinearLayoutManager(this);
        rvVatnuoi.setLayoutManager(layoutManager);
        rvVatnuoi.setAdapter(adapter);

        Log.e("List", vatNuoiList.size()+"");

        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(Color
                .parseColor("#2175D4")));
    }

    private void initView() {
        floatingActionButton = findViewById(R.id.floatingActionButton);
        rvVatnuoi = findViewById(R.id.rv_vatnuoi);
    }
}

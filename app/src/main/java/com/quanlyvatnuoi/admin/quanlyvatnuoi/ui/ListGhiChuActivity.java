package com.quanlyvatnuoi.admin.quanlyvatnuoi.ui;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.quanlyvatnuoi.admin.quanlyvatnuoi.R;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.adapter.GhiChuAdapter;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.dao.GhiChuDAO;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.model.Ghichu;
import com.quanlyvatnuoi.admin.quanlyvatnuoi.sqlite.DatabaseHelper;

import java.util.List;

public class ListGhiChuActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private GhiChuDAO dao;
    private GhiChuAdapter adapter;
    private ListView lvGhichu;
    private FloatingActionButton floatingActionButton;
    private List<Ghichu> ghichuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ghi_chu);
        setTitle("Ghi chú");
        initView();
        databaseHelper = new DatabaseHelper(this);
        dao = new GhiChuDAO(databaseHelper);

        ghichuList = dao.getAllNote();
        adapter = new GhiChuAdapter(this, ghichuList);
        lvGhichu.setAdapter(adapter);

        lvGhichu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListGhiChuActivity.this, SuaGhiChuActivity.class);
                intent.putExtra("id", ghichuList.get(position).getId());
                intent.putExtra("tieude", ghichuList.get(position).getTieude());
                intent.putExtra("noidung", ghichuList.get(position).getNoidung());
                intent.putExtra("thoigian", ghichuList.get(position).getThoigian());
                startActivity(intent);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ThemGhiChuActivity.class));
            }
        });

        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(Color
                .parseColor("#2175D4")));
    }

    private void initView() {
        lvGhichu = findViewById(R.id.lv_ghichu);
        floatingActionButton = findViewById(R.id.floatingActionButton);
    }
}

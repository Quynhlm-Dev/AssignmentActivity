package com.quynhlm.dev.assignmentactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class Activity_Phong_ban extends AppCompatActivity {

    ListView listView;
    Toolbar toolbar;

    SearchView searchView;

    ArrayList<Phong_Ban_Model> list;


    PhongBan_Adapter phongBan_adapter;

    private void mapview() {
        listView = findViewById(R.id.listview1);
        toolbar = findViewById(R.id.toolbar_phongBan);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Phòng ban");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phongban);
        mapview();
        list.add(new Phong_Ban_Model(R.drawable.department,"Nhân sự"));
        list.add(new Phong_Ban_Model(R.drawable.department,"Hành Chính"));
        list.add(new Phong_Ban_Model(R.drawable.department,"Đào tạo"));

        phongBan_adapter = new PhongBan_Adapter(this,list,list);
        listView.setAdapter(phongBan_adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Activity_Phong_ban.this,"Phòng "+ list.get(position).getPhongban(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_phong_ban,menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                phongBan_adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                phongBan_adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            startActivity(new Intent(Activity_Phong_ban.this, Acticity_Trang_Chu.class));
            finish();
        }
        if(item.getItemId() == R.id.dang_xuat_phong_ban){
            startActivity(new Intent(Activity_Phong_ban.this,Activity_Dang_Nhap.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
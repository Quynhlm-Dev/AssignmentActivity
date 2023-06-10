package com.quynhlm.dev.assignmentactivity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Activity_Nhan_Vien extends AppCompatActivity {

    ListView listView;
    ArrayList<Nhan_Vien_Model> list;
    Button btnThemMoi;
    Toolbar toolbar;

    SearchView searchView;
    NhanVien_Adapter nhanVienAdapter;

    ActivityResultLauncher<Intent> nhan_du_lieu = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        String name = intent.getStringExtra(Activity_add_or_update.KEY_NAME);
                        String ID = intent.getStringExtra(Activity_add_or_update.KEY_ID);
                        String phongban = intent.getStringExtra(Activity_add_or_update.KEY_PHONGBAN);
                        String gioitinh = intent.getStringExtra(Activity_add_or_update.KEY_GIOITINH);
                        int img;

                        if (gioitinh.equals("Nam")) {
                            img = R.drawable.profile;
                        } else {
                            img = R.drawable.profile_nu;
                        }

                        list.add(new Nhan_Vien_Model(img, ID, name, phongban, gioitinh));
                        fill();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Nhân Viên");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        docFile_NV();

        listView = findViewById(R.id.lview);
        btnThemMoi = findViewById(R.id.btnthem);
        list = new ArrayList<>();
        list.add(new Nhan_Vien_Model(R.drawable.profile, "NV1", "Lê Mạnh Quỳnh", "Đào tạo", "Nam"));
        list.add(new Nhan_Vien_Model(R.drawable.profile_nu, "NV2", "Tống Doanh Chính ", "Nhân viên", "Nữ"));
        list.add(new Nhan_Vien_Model(R.drawable.profile, "NV3", "Lê Văn Đức", "Nhân sự", "Nam"));
        list.add(new Nhan_Vien_Model(R.drawable.profile_nu, "NV4", "Bùi Thị Thảo", "Nhân viên", "Nữ"));

        fill();

        ghiFile_NV();

//        btnThemMoi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Activity_Nhan_Vien.this, Activity_add_or_update.class);
//                nhan_du_lieu.launch(i);
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                nhanVienAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                nhanVienAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int su_kien = item.getItemId();
        if (su_kien == android.R.id.home) {
            startActivity(new Intent(Activity_Nhan_Vien.this, Acticity_Trang_Chu.class));
            finish();
        }
        if (su_kien == R.id.them_moi) {
            Intent i = new Intent(Activity_Nhan_Vien.this, Activity_add_or_update.class);
            nhan_du_lieu.launch(i);
        }
        if (su_kien == R.id.so_luong) {
            Toast.makeText(this, "Số lượng nhân viên:" + list.size(), Toast.LENGTH_SHORT).show();
        }
        if (su_kien == R.id.dang_xuat) {
            Intent intent = new Intent(Activity_Nhan_Vien.this, Activity_Dang_Nhap.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void fill() {
        nhanVienAdapter = new NhanVien_Adapter(this, list, list);
        listView.setAdapter(nhanVienAdapter);
    }

    public void Delete(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Nhan_Vien.this);
        builder.setTitle("Xóa Nhân Viên");
        builder.setMessage("Bạn có chắc chắn muốn xóa nhân viên này không ?");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                list.remove(position);
                fill();
            }
        });
        builder.setNegativeButton("Hủy", null);
        builder.show();
    }

    public Nhan_Vien_Model nvModel;

    ActivityResultLauncher<Intent> sua_thong_tin = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        String name = intent.getStringExtra(Activity_add_or_update.KEY_NAME);
                        String ID = intent.getStringExtra(Activity_add_or_update.KEY_ID);
                        String phongban = intent.getStringExtra(Activity_add_or_update.KEY_PHONGBAN);
                        String gioitinh = intent.getStringExtra(Activity_add_or_update.KEY_GIOITINH);

                        int img;
                        if (gioitinh.equals("Nam")) {
                            img = R.drawable.profile;
                        } else {
                            img = R.drawable.profile_nu;
                        }
                        nvModel.setImglogo(img);
                        nvModel.setId(ID);
                        nvModel.setName(name);
                        nvModel.setPhongBan(phongban);
                        nvModel.setGioiTinh(gioitinh);
                        fill();
                    }
                }
            }
    );
    public static final String KEY_NV_MODEL = "nv_model";

    public void Update(int position) {
        Intent i = new Intent(Activity_Nhan_Vien.this, Activity_add_or_update.class);
        nvModel = list.get(position);
        i.putExtra(KEY_NV_MODEL, nvModel);
        sua_thong_tin.launch(i);
    }

    public static String FILE_NAME = "nv.txt";

    public void ghiFile_NV() {
        try {
            FileOutputStream fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void docFile_NV() {
        try {
            FileInputStream fileInputStream = openFileInput(FILE_NAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            list = (ArrayList<Nhan_Vien_Model>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
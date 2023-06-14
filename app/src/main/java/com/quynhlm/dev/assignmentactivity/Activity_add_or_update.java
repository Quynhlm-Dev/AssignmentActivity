package com.quynhlm.dev.assignmentactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class Activity_add_or_update extends AppCompatActivity {

    public boolean isChuoi(String str) {
        return str.matches("[a-zA-Z0-9 ]+");
    }

    TextInputEditText edtUserName;
    TextInputEditText edtID;
    TextInputEditText edtPhongBan;
    Button btnSubmid;

    ArrayList<Nhan_Vien_Model> list = new ArrayList<>();

    Spinner spinner;
    public static String KEY_ID = "ID";
    public static String KEY_NAME = "name";
    public static String KEY_PHONGBAN = "phongban";

    public static String KEY_GIOITINH = "Gioitinh";

    private void minmap() {
        edtUserName = findViewById(R.id.edt_NameNhanVien);
        edtID = findViewById(R.id.edtId);
        edtPhongBan = findViewById(R.id.edtPhongBan);
        btnSubmid = findViewById(R.id.btnSubmit);
        spinner = findViewById(R.id.spiner_gioiTinh);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_update);
        minmap();

        Nhan_Vien_Model nvModel = (Nhan_Vien_Model) getIntent().getSerializableExtra(Activity_Nhan_Vien.KEY_NV_MODEL);

        if (nvModel != null) {
            edtUserName.setText(nvModel.getName());
            edtPhongBan.setText(nvModel.getPhongBan());
            edtID.setText(nvModel.getId());
        }

        ArrayList<String> list = new ArrayList<>();
        list.add("Nam");
        list.add("Nữ");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        spinner.setAdapter(adapter);


        btnSubmid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = spinner.getSelectedItemPosition();
                String gioiTinh = list.get(position).toString();
                String name = edtUserName.getText().toString();
                String id = edtID.getText().toString();
                String phongBan = edtPhongBan.getText().toString();

                if (name.trim().equals("") && id.trim().equals("") && phongBan.trim().equals("")) {
                    Toast.makeText(Activity_add_or_update.this, "Chua nhap du dinh dang form", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra(KEY_NAME, name);
                    intent.putExtra(KEY_ID, id);
                    intent.putExtra(KEY_PHONGBAN, phongBan);
                    intent.putExtra(KEY_GIOITINH, gioiTinh);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
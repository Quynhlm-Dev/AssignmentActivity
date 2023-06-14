package com.quynhlm.dev.assignmentactivity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;

public class Activity_Dang_Nhap extends AppCompatActivity {

    public boolean isChuoi(String str) {
        return str.matches("[a-z A-Z 0-9]+");
    }

    EditText edtname;
    EditText edtpass;
    Button btndangnhap;
    TextView txtdangky;
    CheckBox chk_remember;

    String username_dk;

    String password_dk;

    String username;

    String password;

    private void minMap() {
        edtname = findViewById(R.id.edtUsername);
        edtpass = findViewById(R.id.edtPassWord);
        btndangnhap = findViewById(R.id.btndangnhap);
        txtdangky = findViewById(R.id.txtdangky);
        chk_remember = findViewById(R.id.chk_remember);
    }

    ActivityResultLauncher<Intent> danhNhap = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        edtname.setText(intent.getStringExtra(Activity_Dang_Ky.KEY_USERNAME));
                        edtpass.setText(intent.getStringExtra(Activity_Dang_Ky.KEY_PASSWORD));
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        minMap();
        docShared();
        DocFile();
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = edtname.getText().toString();
                password = edtpass.getText().toString();
                if (username.trim().equals("") && password.trim().equals("")) {
                    Toast.makeText(Activity_Dang_Nhap.this, "Chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!isChuoi(username)) {
                    Toast.makeText(Activity_Dang_Nhap.this, "Nhap sai dinh dang", Toast.LENGTH_SHORT).show();
                } else if (username.equals(username_dk) && password.equals(password_dk)) {
                    Intent intent = new Intent(Activity_Dang_Nhap.this, Acticity_Trang_Chu.class);
                    startActivity(intent);
                    ghiShared();
                    Toast.makeText(Activity_Dang_Nhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Activity_Dang_Nhap.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        txtdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Dang_Nhap.this, Activity_Dang_Ky.class);
                danhNhap.launch(intent);
            }
        });
    }

    public void DocFile() {
        try {
            FileInputStream fileInputStream = openFileInput("account.txt");
            int read = -1;
            StringBuilder builder = new StringBuilder();
            while ((read = fileInputStream.read()) != -1) {
                builder.append((char) read);
            }
            String data = builder.toString();
            String arrdata[] = data.split(",");
            if (arrdata != null && arrdata.length > 0) {
                username_dk = arrdata[0];
                password_dk = arrdata[1];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void ghiShared() {
        if (chk_remember.isChecked()) {
            String usernameShared = edtname.getText().toString();
            String passwordShared = edtpass.getText().toString();
            SharedPreferences data = getSharedPreferences("abc", MODE_PRIVATE);
            SharedPreferences.Editor editor = data.edit();
            editor.putString("username", usernameShared);
            editor.putString("password", passwordShared);
            editor.apply();
        }
    }
    private void docShared() {
        SharedPreferences sharedPreferences = getSharedPreferences("abc", MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("username", "");
        String savedPassword = sharedPreferences.getString("password", "");
        edtname.setText(savedUsername);
        edtpass.setText(savedPassword);
    }
}
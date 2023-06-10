package com.quynhlm.dev.assignmentactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Activity_Dang_Ky extends AppCompatActivity {


    Button btndangky;
    TextView txttrove;
    EditText edtusername;
    EditText edtpassword;
    EditText edtNhaplai;

    public static String KEY_USERNAME = "username";
    public static String KEY_PASSWORD = "password";
    String username;
    String password;
    String nhaplai;

    private void minmap() {
        btndangky = findViewById(R.id.btndangky);
        txttrove = findViewById(R.id.txttrove);

        edtusername = findViewById(R.id.edtUsername);
        edtpassword = findViewById(R.id.edtPassWord);
        edtNhaplai = findViewById(R.id.edtlaiPassWord);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        minmap();

        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = edtusername.getText().toString();
                password = edtpassword.getText().toString();
                nhaplai = edtNhaplai.getText().toString();


                if (username.trim().equals("")) {
                    Toast.makeText(Activity_Dang_Ky.this, "Chua nhap username", Toast.LENGTH_SHORT).show();
                } else if (password.trim().equals("")) {
                    Toast.makeText(Activity_Dang_Ky.this, "Chua nhap password", Toast.LENGTH_SHORT).show();
                } else if (nhaplai.trim().equals("")) {
                    Toast.makeText(Activity_Dang_Ky.this, "Chua nhap lai password", Toast.LENGTH_SHORT).show();
                } else if (!nhaplai.equals(password)) {
                    Toast.makeText(Activity_Dang_Ky.this, "password nhap lai khong dung", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Activity_Dang_Ky.this, Activity_Dang_Nhap.class);
                    intent.putExtra(KEY_USERNAME, username);
                    intent.putExtra(KEY_PASSWORD, password);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                ghiDuLieu();
            }
        });
        txttrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Activity_Dang_Ky.this, Activity_Dang_Nhap.class);
                startActivity(intent1);
            }
        });
    }

    public void ghiDuLieu() {
        try {
            FileOutputStream fileOutputStream = openFileOutput("account.txt", MODE_PRIVATE);
            fileOutputStream.write(username.getBytes());
            fileOutputStream.write(",".getBytes());
            fileOutputStream.write(password.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Ghi File Thanh Cong", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
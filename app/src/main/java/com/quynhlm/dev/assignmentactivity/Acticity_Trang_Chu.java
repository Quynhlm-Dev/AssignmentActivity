package com.quynhlm.dev.assignmentactivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Acticity_Trang_Chu extends AppCompatActivity {

    TextView txttitle;
    TextView txtphongban;
    TextView txtnhanvien;
    TextView txtthoat;

    private void mimmap() {
        txtphongban = findViewById(R.id.txtphongban);
        txtnhanvien = findViewById(R.id.txtnhanvien);
        txtthoat = findViewById(R.id.txtthoat);
        txttitle = findViewById(R.id.textView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);

        mimmap();
        Animation animation2 = AnimationUtils.loadAnimation(Acticity_Trang_Chu.this, R.anim.hien_thi);
        Animation animation = AnimationUtils.loadAnimation(Acticity_Trang_Chu.this, R.anim.an_hien);
        txtnhanvien.startAnimation(animation);
        txtthoat.startAnimation(animation);
        txtphongban.startAnimation(animation);
        txttitle.startAnimation(animation2);
        txtphongban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Acticity_Trang_Chu.this, Activity_Phong_ban.class));
            }
        });
        txtnhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Acticity_Trang_Chu.this, Activity_Nhan_Vien.class));
            }
        });
        txtthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Acticity_Trang_Chu.this);
                builder.setTitle("Thoát ứng dụng");
                builder.setMessage("Bạn có chắc chắn muốn thoát khỏi ứng dụng không ?");
                builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });
                builder.setNegativeButton("Hủy", null);
                builder.show();
            }
        });
    }
}
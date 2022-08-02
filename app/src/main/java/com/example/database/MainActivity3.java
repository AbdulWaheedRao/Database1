package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    TextView tvrollNo;
    EditText tvname, tvfee;
    DBHelper dbHelper;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tvrollNo = findViewById(R.id.rollNo);
        tvfee = findViewById(R.id.fee);
        tvname = findViewById(R.id.name);
        btn = findViewById(R.id.btn);
        dbHelper = DBHelper.getInstance(this);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int rollno = intent.getIntExtra("rollNo", 0);
        double fee = intent.getDoubleExtra("fee", 0);
        tvrollNo.setText(Integer.toString(rollno));
        tvfee.setText(Double.toString(fee));
        tvname.setText(name);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dbHelper.updatetStudent(new Student(Integer.parseInt(tvrollNo.getText().toString()),tvname.getText().toString(),Double.parseDouble(tvfee.getText().toString())))) {
                    finish();
                    Toast.makeText(MainActivity3.this, "update", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity3.this, "Not update", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
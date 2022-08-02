package com.example.database;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etName, etRollNo, etFee;
    Button btnAdd, btnRead;
    DBHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=DBHelper.getInstance(this);
        btnRead=findViewById(R.id.btRead);
        btnAdd=findViewById(R.id.btAdd);
        etName = findViewById(R.id.etName);
        etRollNo = findViewById(R.id.etRollNo);
        etFee = findViewById(R.id.etFee);



        //method for find id
        // btnClickListener method
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog();


            }
        });
    }
    public void customDialog(){
        if (etRollNo.getText().toString().isEmpty()) {
            etRollNo.setError("Please enter ROllNo");
            etRollNo.hasFocus();
        } else if (etName.getText().toString().isEmpty()) {
            etName.setError("Please enter Name");
            etName.hasFocus();
        } else if (etFee.getText().toString().isEmpty()) {
            etFee.setError("Please enter fee");
            etFee.hasFocus();

        }else {
            String name = etName.getText().toString();
            int RollNo = Integer.parseInt(etRollNo.getText().toString());
            double fee = Double.parseDouble(etFee.getText().toString());

            if (dbHelper.insertStudent(new Student(RollNo, name, fee))) {
                Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Data not inserted", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
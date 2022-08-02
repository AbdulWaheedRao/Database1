package com.example.database;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    Adopter adopter;
    RecyclerView recyclerView;
    DBHelper dbHelper;
    List<Student>students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView=findViewById(R.id.rvxml);
        dbHelper=DBHelper.getInstance(this);
        students=dbHelper.fetchStudents();
        adopter=new Adopter(students,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adopter);
        recyclerView.setHasFixedSize(true);

        adopter.setMyLongClick(new Adopter.MyLongClick() {
            @Override
            public void LongClick(View view, int index) {
                Student position=students.get(index);
                if (dbHelper.deleteStudent(position)){
                    Toast.makeText(MainActivity2.this, "DELETED", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity2.this, "Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void click(View view, int index) {
                Student position=students.get(index);
                int rollNo=position.getRollNo();
                double fee= position.getFee();
                String name=position.getName();
                Intent intent=new Intent(MainActivity2.this,MainActivity3.class);
                intent.putExtra("name",name);
                intent.putExtra("rollNo",rollNo);
                intent.putExtra("fee",fee);
                startActivity(intent);
            }

            @Override
            public void longPress(View view, int index) {
                Toast.makeText(MainActivity2.this, "LongClick", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
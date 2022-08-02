package com.example.database;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adopter extends RecyclerView.Adapter<Adopter.ProductViewHolder>{
    List<Student> students;
    DBHelper dbHelper;
    MyLongClick myLongClick;
    Context context;

    public void setMyLongClick(MyLongClick myLongClick) {
        this.myLongClick = myLongClick;
    }

    public Adopter(List<Student> students,Context context) {
        this.students = students;
        this.context = context;
        dbHelper=DBHelper.getInstance(context);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootview= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout,null);

        return new ProductViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Student student=students.get(position);
        holder.tvrollNo.setText(Integer.toString(student.getRollNo()));
        holder.tvName.setText(student.getName());
        holder.tvFee.setText(Double.toString(student.getFee()));
//        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int rollNo=Integer.parseInt(holder.tvrollNo.getText().toString());
//                double fee=Double.parseDouble(holder.tvFee.getText().toString());
//                String name=holder.tvName.getText().toString();
//                Intent intent=new Intent();
//                intent.putExtra("name",name);
//                intent.putExtra("rollNo",rollNo);
//                intent.putExtra("fee",fee);
////                if (dbHelper.updatetStudent(new Student(rollNo,name,fee))){
////                    Toast.makeText(context, "update", Toast.LENGTH_SHORT).show();
////                }else{
////                    Toast.makeText(context, "Not update", Toast.LENGTH_SHORT).show();
////                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{
        EditText tvName,tvFee;
        TextView tvrollNo;
        Button btnUpdate,btnDelete;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvrollNo = itemView.findViewById(R.id.tvrollNo);
            tvName = itemView.findViewById(R.id.tvName);
            tvFee = itemView.findViewById(R.id.tvFee);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myLongClick.click(view,getAdapterPosition());
                }
            });
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (myLongClick!=null){
                        myLongClick.LongClick(view,getAdapterPosition());
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    myLongClick.longPress(view,getAdapterPosition());
                    return true;
                }
            });
        }
    }
    interface MyLongClick{
        void LongClick(View view,int index);
        void click(View view,int index);
        void longPress(View view,int index);

    }
}

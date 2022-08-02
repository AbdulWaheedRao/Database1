package com.example.database;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static String DB_Name="ELMS";
    public static int DB_Version=1;
    public static DBHelper instance;


    public static DBHelper getInstance(Context context){
        if (instance==null){
            instance= new DBHelper(context);
        }
        return instance;
    }
    private DBHelper(@Nullable Context context) {
        super(context, DB_Name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Student.CREATE_TABLE);
    }

    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            sqLiteDatabase.execSQL(Student.DROP_TABLE);
            sqLiteDatabase.execSQL(Student.CREATE_TABLE);
        }
    }
    public List<Student> fetchStudents(){
        SQLiteDatabase database=getReadableDatabase();
        Cursor cursor=database.rawQuery(Student.SELECT_STUDENTS,null);
        List<Student> students=new ArrayList<>(cursor.getCount());
        if (cursor.moveToFirst()){
            do {
                Student student=new Student();
                int index=cursor.getColumnIndex(Student.KEY_ROLL_NO);
                student.setRollNo(cursor.getInt(index));
                index=cursor.getColumnIndex(Student.KEY_NAME);
                student.setName(cursor.getString(index));
                index=cursor.getColumnIndex(Student.KEY_FEE);
                student.setFee(cursor.getDouble(index));
                students.add(student);

            }while (cursor.moveToNext());
        }
        return students;
    }
    //    public Student fetchStudent(Student student){
//        SQLiteDatabase database=getReadableDatabase();
//        Cursor cursor=database.rawQuery(Student.selectStudent(student),null);
//        Student newStudent=null;
//        if (cursor.moveToFirst() && cursor.getCount() != 1){
//            newStudent=new Student();
//            int index = cursor.getColumnIndex(Student.KEY_ROLL_NO);
//            student.setRollNo(cursor.getInt(index));
//            index = cursor.getColumnIndex(Student.KEY_NAME);
//            student.setName(cursor.getString(index));
//            index = cursor.getColumnIndex(Student.KEY_FEE);
//            student.setFee(cursor.getDouble(index));
//        }
//        return student;
//    }
    public boolean insertStudent(Student student){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Student.KEY_ROLL_NO,student.getRollNo());
        contentValues.put(Student.KEY_NAME,student.getName());
        contentValues.put(Student.KEY_FEE,student.getFee());
        long effectedRows=database.insert(Student.TABLE_NAME,null,contentValues);
        return effectedRows>0;
    }
    public boolean updatetStudent(Student student){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Student.KEY_ROLL_NO,student.getRollNo());
        contentValues.put(Student.KEY_NAME,student.getName());
        contentValues.put(Student.KEY_FEE,student.getFee());
        long effectedRows=database.update(Student.TABLE_NAME,contentValues,Student.KEY_ROLL_NO+" =?",new String[]{Integer.toString(student.getRollNo())});
        return effectedRows >= 1;
    }
    public boolean deleteStudent(Student student){
        SQLiteDatabase database=getWritableDatabase();
        long effectedRows=database.delete(Student.TABLE_NAME,Student.KEY_ROLL_NO+"=?",new String[]{Integer.toString(student.getRollNo())});
        return effectedRows==1;
    }
}


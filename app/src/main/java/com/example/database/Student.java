package com.example.database;

public class Student {
    private int rollNo;
    private String name;
    private double fee;

    public static final String TABLE_NAME="Student";
    public static final String KEY_ROLL_NO="RollNo";
    public static final String KEY_NAME="Name";
    public static final String KEY_FEE="Fee";
    //    public static final String CREATE_TABLE="CREATE TABLE"+TABLE_NAME+"IF NOT EXISTS ("+KEY_ROLL_NO+" INTEGER PRIMARY KEY, "+KEY_NAME+"TEXT,"+KEY_FEE+"REAL)";
    public static final String CREATE_TABLE=String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY ,%s TEXT,%s REAL)",TABLE_NAME,KEY_ROLL_NO,KEY_NAME,KEY_FEE);
    public static final String DROP_TABLE="DROP TABLE"+TABLE_NAME+"IF EXISTS";
    public static final String SELECT_STUDENTS="SELECT * FROM "+TABLE_NAME;
    public static String selectStudent(Student student){
        return TABLE_NAME;

    }

    public Student() {
    }

    public Student(int rollNo, String name, double fee) {
        this.rollNo = rollNo;
        this.name = name;
        this.fee = fee;
    }

    public Student(String name, double fee) {
        this.name = name;
        this.fee = fee;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollNo=" + rollNo +
                ", name='" + name + '\'' +
                ", fee=" + fee +
                '}';
    }
}

package com.example.myappga;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class  Student {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Name")
    private String Name;

    @ColumnInfo(name = "Mobno")
    private String Mobno;

    @ColumnInfo(name = "Email")
    private String Email;

    @ColumnInfo(name = "Password")
    private String Password;

    Student(int id, String Name, String Mobno, String Email, String Password){
        this.id = id;
        this.Name= Name;
        this.Mobno = Mobno;
        this.Email=Email;
        this.Password=Password;
    }

    @Ignore
    Student(String Name, String Mobno, String Email, String Password){
        this.Name= Name;
        this.Mobno = Mobno;
        this.Email=Email;
        this.Password=Password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobno() {
        return Mobno;
    }

    public void setMobno(String mobno) {
        Mobno = mobno;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
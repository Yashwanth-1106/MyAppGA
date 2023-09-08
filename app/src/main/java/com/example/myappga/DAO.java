package com.example.myappga;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DAO {

    @Query(" select * from Student ")
    List<Student> getAllUsers();

    @Query("SELECT * FROM Student WHERE Email = :email")
    Student getUserByEmail(String email);

    @Query("SELECT * FROM Student WHERE Email = :email AND Password = :password")
    Student getUserByEmailAndPassword(String email, String password);


    @Insert
    void adduser(Student student);

    @Update
    void updateUser(Student student);

    @Delete
    void deleteUser(Student student);

}
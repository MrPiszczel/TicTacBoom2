package me.mateusz.tictacboom2.Model.TimeModel;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import me.mateusz.tictacboom2.Model.TimeModel.Time;

@Dao
public interface TimeDao {

    @Insert
    void insert(Time time);

    @Update
    void update(Time time);

    @Delete
    void delete(Time time);
}

package me.mateusz.tictacboom2.Model.AlarmModel;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import me.mateusz.tictacboom2.Model.AlarmModel.Alarm;
import me.mateusz.tictacboom2.Model.TimeModel.Time;

@Dao
public interface AlarmDao {

    @Insert
    void insert(Alarm alarm);

    @Update
    void update(Alarm alarm);

    @Delete
    void delete(Alarm alarm);

    @Query("DELETE FROM alarm_table")
    void deleteAllNotes();

    @Query("SELECT * FROM alarm_table")
    LiveData<List<Alarm>> getAllAlarms();

    @Query("SELECT hour,minute, second FROM alarm_table")
    LiveData<List<Time2>> getAllTimes();

    @Query("SELECT hour,minute,second FROM alarm_table")
    LiveData<List<Time2>> getHMS();
}

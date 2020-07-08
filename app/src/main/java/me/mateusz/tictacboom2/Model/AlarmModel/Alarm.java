package me.mateusz.tictacboom2.Model.AlarmModel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "alarm_table")
public class Alarm {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private int hour;
    private int minute;
    private int second;

    public Alarm(String title, String description, int hour, int minute, int second) {
        this.title = title;
        this.description = description;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }
}

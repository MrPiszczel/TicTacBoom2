package me.mateusz.tictacboom2.Model.TimeModel;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import me.mateusz.tictacboom2.Model.AlarmModel.Alarm;
import me.mateusz.tictacboom2.Model.AlarmModel.AlarmDao;
import me.mateusz.tictacboom2.Model.AlarmModel.AlarmDatabase;

@Database(entities = {Time.class}, version = 3)
public abstract class TimeDatabase extends RoomDatabase  {

    private static TimeDatabase instance;

    public abstract TimeDao timeDao();

    public static synchronized TimeDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), TimeDatabase.class, "time_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new TimeDatabase.PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TimeDao timeDao;

        private PopulateDbAsyncTask(TimeDatabase db) {
            timeDao = db.timeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            timeDao.insert(new Time(5,5,5));
            timeDao.insert(new Time(6,6,6));
            timeDao.insert(new Time(6,6,6));
            return null;
        }
    }
}

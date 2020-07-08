package me.mateusz.tictacboom2.Model.AlarmModel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import me.mateusz.tictacboom2.Model.AlarmModel.Alarm;
import me.mateusz.tictacboom2.Model.AlarmModel.AlarmDao;
import me.mateusz.tictacboom2.Model.AlarmModel.AlarmDatabase;

public class AlarmRepository {

    private AlarmDao alarmDao;
    private LiveData<List<Alarm>> listAlarms;
    private LiveData<List<Time2>> listTimes;

    public AlarmRepository(Application application) {
        AlarmDatabase database = AlarmDatabase.getInstance(application);
        alarmDao = database.alarmDao();
        listAlarms = alarmDao.getAllAlarms();
        listTimes = alarmDao.getAllTimes();
    }

    public void insert(Alarm note) { new InsertAlarmAsyncTask(alarmDao).execute(note); }

    public void update(Alarm note) {
        new UpdateAlarmAsyncTask(alarmDao).execute(note);
    }

    public void delete(Alarm note) {
        new DeleteAlarmAsyncTask(alarmDao).execute(note);
    }

    public void deleteAllAlarms() {
        new DeleteAllAlarmsAsyncTask(alarmDao).execute();
    }

    public LiveData<List<Alarm>> getAllAlarms() {
        return listAlarms;
    }

    public LiveData<List<Time2>> getAllTimes(){return listTimes;}

    private static class InsertAlarmAsyncTask extends AsyncTask<Alarm, Void, Void> {
        private AlarmDao alarmDao;

        private InsertAlarmAsyncTask(AlarmDao alarmDao) {
            this.alarmDao = alarmDao;
        }

        @Override
        protected Void doInBackground(Alarm... notes) {
            alarmDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateAlarmAsyncTask extends AsyncTask<Alarm, Void, Void> {
        private AlarmDao alarmDao;

        private UpdateAlarmAsyncTask(AlarmDao alarmDao) {
            this.alarmDao = alarmDao;
        }

        @Override
        protected Void doInBackground(Alarm... notes) {
            alarmDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteAlarmAsyncTask extends AsyncTask<Alarm, Void, Void> {
        private AlarmDao alarmDao;

        private DeleteAlarmAsyncTask(AlarmDao alarmDao) {
            this.alarmDao = alarmDao;
        }

        @Override
        protected Void doInBackground(Alarm... notes) {
            alarmDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllAlarmsAsyncTask extends AsyncTask<Void, Void, Void> {
        private AlarmDao alarmDao;

        private DeleteAllAlarmsAsyncTask(AlarmDao alarmDao) {
            this.alarmDao = alarmDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            alarmDao.deleteAllNotes();
            return null;
        }
    }
}

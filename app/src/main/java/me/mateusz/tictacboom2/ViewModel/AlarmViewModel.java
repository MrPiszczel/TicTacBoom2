package me.mateusz.tictacboom2.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import me.mateusz.tictacboom2.Model.AlarmModel.Alarm;
import me.mateusz.tictacboom2.Model.AlarmModel.AlarmRepository;
import me.mateusz.tictacboom2.Model.AlarmModel.Time2;

public class AlarmViewModel extends AndroidViewModel {
    private AlarmRepository repository;
    private LiveData<List<Alarm>> allAlarms;
    private LiveData<List<Time2>> allTimes;

    public AlarmViewModel(@NonNull Application application) {
        super(application);
        repository = new AlarmRepository(application);
        allAlarms = repository.getAllAlarms();
        allTimes = repository.getAllTimes();
    }

    public void insert(Alarm alarm){
        repository.insert(alarm);
    }

    public void update(Alarm alarm){
        repository.update(alarm);
    }

    public void delete(Alarm alarm){
        repository.delete(alarm);
    }

    public void deleteAllAlarms(Alarm alarm){
        repository.deleteAllAlarms();
    }

    public LiveData<List<Alarm>> getAllAlarms(){
        return allAlarms;
    }

    public LiveData<List<Time2>> getAllTimes() {return allTimes;}
}

package me.mateusz.tictacboom2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import me.mateusz.tictacboom2.Model.AlarmModel.Alarm;
import me.mateusz.tictacboom2.Model.AlarmModel.Time2;
import me.mateusz.tictacboom2.Thread.TimeThread;
import me.mateusz.tictacboom2.ViewModel.AlarmAdapter;
import me.mateusz.tictacboom2.ViewModel.AlarmViewModel;

public class MainActivity extends AppCompatActivity {

    private AlarmViewModel alarmViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.add_alarm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddAlarm.class);
                startActivityForResult(intent,1);
            }
        });

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final AlarmAdapter adapter = new AlarmAdapter();
        recyclerView.setAdapter(adapter);

        alarmViewModel = ViewModelProviders.of(this).get(AlarmViewModel.class);
        alarmViewModel.getAllAlarms().observe(this, new Observer<List<Alarm>>() {
            @Override
            public void onChanged(@Nullable List<Alarm> alarms) {
                adapter.submitList(alarms);
            }
        });

        alarmViewModel.getAllTimes().observe(this, new Observer<List<Time2>>() {
            @Override
            public void onChanged(@Nullable List<Time2> times) {
                TimeThread timeThread = new TimeThread(times, getApplicationContext());
                timeThread.startAlarm();
            }
        });

        adapter.setOnItemClickListener(new AlarmAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Alarm alarm) {
                Intent intent = new Intent(MainActivity.this, AlarmDetails.class);
                intent.putExtra(AlarmDetails.EXTRA_ID,alarm.getId());
                intent.putExtra(AlarmDetails.EXTRA_TITLE, alarm.getTitle());
                intent.putExtra(AlarmDetails.EXTRA_DESCRIPTION, alarm.getDescription());
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 1) {
            String title = data.getStringExtra(AddAlarm.EXTRA_TITLE);
            String description = data.getStringExtra(AddAlarm.EXTRA_DESCRIPTION);
            int hour = data.getIntExtra(AddAlarm.EXTRA_HOUR, 0);
            int minute = data.getIntExtra(AddAlarm.EXTRA_MINUTE, 0);
            int second = data.getIntExtra(AddAlarm.EXTRA_SECOND, 0);

            Alarm alarm = new Alarm(title, description,hour, minute, second);
            alarmViewModel.insert(alarm);
        }else if(resultCode == 2){
            String title = data.getStringExtra(AlarmDetails.EXTRA_TITLE);
            String description = data.getStringExtra(AlarmDetails.EXTRA_DESCRIPTION);
            int id = data.getIntExtra(AlarmDetails.EXTRA_ID, -1);

            Alarm alarm = new Alarm(title, description, 0, 0, 0);
            alarm.setId(id);
            alarmViewModel.update(alarm);
            Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
        }else if(resultCode == 3){
            String title = data.getStringExtra(AlarmDetails.EXTRA_TITLE);
            String description = data.getStringExtra(AlarmDetails.EXTRA_DESCRIPTION);
            int id = data.getIntExtra(AlarmDetails.EXTRA_ID, -1);

            Alarm alarm = new Alarm(title, description, 0, 0, 0);
            alarm.setId(id);
            alarmViewModel.delete(alarm);
            Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
        }
    }
}

package me.mateusz.tictacboom2.Thread;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import me.mateusz.tictacboom2.Model.AlarmModel.Time2;

public class TimeThread {

    private Handler handler = new Handler();
    private List<Time2> times = new ArrayList<>();
    private Context context;
    int howManyTimes = 1;

    public TimeThread(List<Time2> times, Context context) {
        this.context = context;
        this.times = times;
    }

    public void startAlarm(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //while (howManyTimes == 1) {
                while(true){
                //    howManyTimes = 0;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (checkTime()) {
                                Toast.makeText(context, "sa takie same godziny", Toast.LENGTH_SHORT).show();

                                try {
                                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                    Ringtone r = RingtoneManager.getRingtone(context, notification);
                                    r.play();
                                } catch (Exception f) {
                                    f.printStackTrace();
                                }
                            }
                        }
                    });
                }
            }

        }).start();
    }


    public boolean checkTime(){

        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:m:s");
        String dateString = sdf.format(date);

        for(Time2 myList: times){
            if(dateString.equals(String.valueOf(myList.getHour())+":"+String.valueOf(myList.getMinute())+":"+String.valueOf(myList.getSecond()) )){
                return true;
            }
        }

        return false;
    }
}

package ro.pub.cs.systems.eim.practicaltest01var03;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var03Service extends Service {
    ProcessingThread thread;

    public PracticalTest01Var03Service () {
    }

    @Override
    public IBinder onBind (Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand (Intent intent, int flags, int startId) {
        int first = intent.getIntExtra(PracticalTest01Var03MainActivity.EXTRA_NUMBER1, -1);
        int second = intent.getIntExtra(PracticalTest01Var03MainActivity.EXTRA_NUMBER2, -1);
        Log.d("[Service]", "Started thread");
        thread = new ProcessingThread(this, first, second);
        thread.start();
        return Service.START_REDELIVER_INTENT;
    }
}
package ro.pub.cs.systems.eim.practicaltest01var03;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PracticalTest01Var03BroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive (Context context, Intent intent) {
        Log.d(PracticalTest01Var03MainActivity.BROADCAST_RECEIVER_TAG, intent.getStringExtra(PracticalTest01Var03MainActivity.EXTRA_BROADCAST));
    }
}

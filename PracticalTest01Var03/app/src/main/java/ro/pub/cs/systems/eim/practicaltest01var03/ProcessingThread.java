package ro.pub.cs.systems.eim.practicaltest01var03;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ProcessingThread extends Thread{
    public static final String THREAD_TAG = "test01var03.THREADLOG";
    public boolean running = true;
    private Context context = null;
    int first;
    int second;
    int minus;
    int plus;

    public ProcessingThread (Context context, int first, int second) {
        this.context = context;
        this.first = first;
        this.second = second;
        minus = first - second;
        plus = first + second;
    }

    @Override
    public void run () {
        Log.d(THREAD_TAG, "Thread has started!");
        sendMessage(0);
        sleep();
        sendMessage(1);
        sleep();
        Log.d(THREAD_TAG, "Thread has stopped");
        running = false;
    }

    private void sendMessage(int actiontype) {
        Intent intent = new Intent();
        intent.setAction(PracticalTest01Var03MainActivity.actionTypes[actiontype]);
        intent.putExtra(PracticalTest01Var03MainActivity.EXTRA_BROADCAST, "Sum: " + plus + " Diff: " + minus);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}

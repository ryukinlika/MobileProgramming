package id.ac.umn.week10c_29240;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

public class CustomService extends Service {
    public CustomService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("CUSTOMSERVICE", "onBind: Service Bind");
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("CUSTOMSERVICE","onDestroy: Service Destroyed");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("CUSTOMSERVICE", "onCreate: Custom Service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("CUSTOMSERVICE", "onStartCommand " + startId);
//        int n = (int) (Math.random()*50)+10;
//        try{
//            for(int i = 0; i < n; i++){
//                Thread.sleep(100);
//                Log.i("CUSTOMSERVICE", "onStartCommand: " + startId + " running - " + ((int)((100*i)/(float)n)) + " percent");
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        AsyncTask customServiceTask = new CustomServiceTask()
                .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,startId);
        return Service.START_STICKY;
    }
}
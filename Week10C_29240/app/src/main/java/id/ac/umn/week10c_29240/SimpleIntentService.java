package id.ac.umn.week10c_29240;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class SimpleIntentService extends IntentService {
    public SimpleIntentService() {
        super("SimpleIntentService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("INTENTSERVICE", "onHandleIntent: IntentService started !");
        int n =(int)(Math.random()*50)+10;
        try {
            for(int i = 0; i < n; i++) {
                Thread.sleep(200);
                Log.i("INTENTSERVICE", "onHandleIntent: ongoing "+
                        ((int)((100 * i)/(float) n)) + " percent");
            }
            Log.i("INTENTSERVICE", "onHandleIntent: Finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
